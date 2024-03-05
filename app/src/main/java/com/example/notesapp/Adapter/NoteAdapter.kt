package com.example.notesapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Data.Notesdata
import com.example.notesapp.R
import com.example.notesapp.databinding.NotesBinding
import kotlin.random.Random

class NoteAdapter(val context: Context,val listner:Noteclicklistner):RecyclerView.Adapter<NoteAdapter.Viewholder> (){
    var list=ArrayList<Notesdata>()
    var fullist=ArrayList<Notesdata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
       val binding=NotesBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.notes.text=list.get(position).notes
        holder.binding.title.text=list.get(position).title
        holder.binding.title.isSelected=true
        holder.binding.date.text=list.get(position).date
        holder.binding.date.isSelected=true
        holder.binding.listCard.isSelected=true
        holder.binding.listCard.setCardBackgroundColor(holder.itemView.resources.getColor(randomcolor(),null))
        holder.binding.listCard.setOnClickListener {
            listner.onItemclicked(list[holder.adapterPosition])

        }
        holder.binding.listCard.setOnLongClickListener{
            listner.onLongItemclicked(list[holder.adapterPosition],holder.binding.listCard)
            true

        }





    }
    fun updatelist(mlist:List<Notesdata>){
        fullist.clear()
        fullist.addAll(mlist)
        list.clear()
        list.addAll(fullist)
        notifyDataSetChanged()
    }

    private fun randomcolor(): Int {
     var list=ArrayList<Int>()
         list.add(R.color.list2)
         list.add(R.color.list3)
         list.add(R.color.list4)
         list.add(R.color.list5)
         list.add(R.color.list6)
        list.add(R.color.list7)
        list.add(R.color.list8)
        list.add(R.color.list9)


        var time=System.currentTimeMillis().toInt()
         var randomindex= Random(time).nextInt(list.size)
         return list[randomindex]


    }
    fun filter(new:String){
        list.clear()
        for(t in fullist){
            if(t.title?.lowercase()?.contains(new.lowercase())==true|| t.notes?.lowercase()!!.contains(new.lowercase())){
                list.add(t)
                notifyDataSetChanged()

            }
        }
    }

    interface Noteclicklistner{
        fun onItemclicked(note:Notesdata)
        fun onLongItemclicked(note:Notesdata,cardview:CardView)

    }
    class Viewholder(val binding: NotesBinding):RecyclerView.ViewHolder(binding.root){

    }

}