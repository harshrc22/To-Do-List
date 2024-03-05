package com.example.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.Data.Notesdata
import com.example.notesapp.databinding.ActivityAddnotesBinding
import java.text.SimpleDateFormat
import java.util.Date

class Addnotes : AppCompatActivity() {
    private lateinit var binding: ActivityAddnotesBinding
    private lateinit var notes:Notesdata
    private lateinit var old_note:Notesdata
    var isUpdate=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddnotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            old_note=intent.getSerializableExtra("currentnote") as Notesdata
            binding.title.setText(old_note.title)
            binding.notes.setText(old_note.notes)
            isUpdate=true
        }
        catch(e:Exception){
            e.printStackTrace()
        }
        binding.check.setOnClickListener {
            val title=binding.title.text.toString()
            val note=binding.notes.text.toString()
            if(title.isNotEmpty()||note.isNotEmpty()){
                val formater=SimpleDateFormat("EEE ,d,MMM,yyyy HH:mm a")
                if(isUpdate){
                    notes=Notesdata(old_note.id,title,note,formater.format(Date()))


                }
                else
                {
                    notes=Notesdata(null,title,note,formater.format(Date()))
                }
                val intent= Intent()
                intent.putExtra("note",notes)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
            else
            {
                Toast.makeText(this@Addnotes,"Please enter some data",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }
        binding.back.setOnClickListener {
            onBackPressed()
        }


    }
}
