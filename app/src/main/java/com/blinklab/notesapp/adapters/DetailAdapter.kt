package com.blinklab.notesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.dataclass.DetailDataclass

class DetailAdapter (val array:ArrayList<DetailDataclass>):
        RecyclerView.Adapter<DetailAdapter.MYViewHolder>(){

            inner class MYViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                val pDetail= itemView.findViewById<TextView>(R.id.detail_text)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_designfile,parent,false)
        return MYViewHolder(view)
    }

    override fun getItemCount(): Int {
       return array.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val itemCount=array[position]
        holder.pDetail.text=itemCount.detail
    }

}