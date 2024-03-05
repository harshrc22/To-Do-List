package com.example.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.Adapter.NoteAdapter
import com.example.notesapp.Data.Notesdata
import com.example.notesapp.Database.Notedatabase
import com.example.notesapp.Viewmodel.Noteviewmodel
import com.example.notesapp.Viewmodel.Viewmodelfactory
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.repository.Noterepository

class MainActivity : AppCompatActivity(), NoteAdapter.Noteclicklistner,
    PopupMenu.OnMenuItemClickListener {
    private lateinit var noteviewmodel: Noteviewmodel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var notedata: Notesdata
    private val updatenoted =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val note = it.data!!.getSerializableExtra("note") as Notesdata
                if (note != null) {
                    noteviewmodel.updatenote(note)
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        val dao = Notedatabase.getinstance(this).daodata()
        val repository = Noterepository(dao)
        noteviewmodel =
            ViewModelProvider(this, Viewmodelfactory(repository)).get(Noteviewmodel::class.java)


        noteviewmodel.notesdata.observe(this, Observer {
            it?.let {
                noteAdapter.updatelist(it)
            }

        })


    }

    private fun initUI() {
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        noteAdapter = NoteAdapter(this, this)
        binding.rv.adapter = noteAdapter
        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val note = result.data!!.getSerializableExtra("note") as Notesdata
                    if (note != null) {
                        noteviewmodel.insert(note)
                    }
                }

            }
        binding.fb.setOnClickListener {
            val intent = Intent(this, Addnotes::class.java)
            getContent.launch(intent)
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(new: String?): Boolean {
                noteAdapter.filter(new!!)

                return true
            }
        })


    }

    override fun onItemclicked(note: Notesdata) {
        val intent = Intent(this@MainActivity, Addnotes::class.java)
        intent.putExtra("currentnote", note)
        updatenoted.launch(intent)
    }

    override fun onLongItemclicked(note: Notesdata, cardview: CardView) {
        notedata = note
        popdisplay(cardview)
    }

    private fun popdisplay(cardview: CardView) {
        val popmenu = PopupMenu(this, cardview)
        popmenu.setOnMenuItemClickListener(this@MainActivity)
        popmenu.inflate(R.menu.pop_up)
        popmenu.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.delete) {
            noteviewmodel.delete(notedata)
        }
        return true
    }


}



