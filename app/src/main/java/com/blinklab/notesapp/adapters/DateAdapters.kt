package com.blinklab.notesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.dataclass.DateDataclass

class DateAdapters ( var array:ArrayList<DateDataclass>):
        RecyclerView.Adapter<DateAdapters.MyViewHolder>(){

            inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                val dday = itemView.findViewById<TextView>(R.id.day)
                val ddate = itemView.findViewById<TextView>(R.id.date)
                val mmonth = itemView.findViewById<TextView>(R.id.month)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.date_designfile,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
       return array.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
              val date=array[position]
        holder.dday.text=date.dayA
        holder.ddate.text=date.dateA
        holder.mmonth.text=date.monthA
    }
}