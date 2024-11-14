package com.blinklab.notesapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.models.DateDataclass

class DateAdapters(private var context: Context,private var array: ArrayList<DateDataclass>) :
    RecyclerView.Adapter<DateAdapters.MyViewHolder>() {

        private var selectedItemPosition = 0

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dday = itemView.findViewById<TextView>(R.id.day)
        val ddate = itemView.findViewById<TextView>(R.id.date)
        val mmonth = itemView.findViewById<TextView>(R.id.month)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_designfile, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val date = array[position]
        holder.dday.text = date.dayA
        holder.ddate.text = date.dateA
        holder.mmonth.text = date.monthA

        val drawable = ContextCompat.getDrawable(context,R.drawable.date_back) as GradientDrawable
        if (holder.adapterPosition == selectedItemPosition){
            holder.apply {
                dday.setTextColor(Color.WHITE)
                ddate.setTextColor(Color.WHITE)
                mmonth.setTextColor(Color.WHITE)
            }
            drawable.setColor(Color.BLACK)
            holder.itemView.background = drawable
        }else{
            holder.apply {
                dday.setTextColor(ContextCompat.getColor(context,R.color.text_color))
                ddate.setTextColor(ContextCompat.getColor(context,R.color.text_color))
                mmonth
                    .setTextColor(ContextCompat.getColor(context,R.color.text_color))
            }
            drawable.setColor(Color.WHITE)
            holder.itemView.background = drawable
        }
        holder.itemView.setOnClickListener {
                       selectedItemPosition = position
            notifyDataSetChanged()
        }
    }
}