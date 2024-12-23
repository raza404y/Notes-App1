package com.blinklab.notesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.models.DeleteDataClass

class DeleteAdapter(private val context: Context, private val array: ArrayList<DeleteDataClass>):
        RecyclerView.Adapter<DeleteAdapter.MyViewHolder>(){
            inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                val tit = itemView.findViewById<TextView>(R.id.note_title_delete)
                val detailss = itemView.findViewById<TextView>(R.id.detail_del)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.delete_notes,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return array.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val countItem =array[position]
        holder.tit.text= countItem.notTitle
        holder.detailss.text=countItem.noteDetail
    }
}

