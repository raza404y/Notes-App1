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
import com.blinklab.notesapp.models.AllDataclass

class AllAdapter(private var context: Context,private var arrayList: ArrayList<AllDataclass>) :
    RecyclerView.Adapter<AllAdapter.MyviewHolder>() {
    private var selectedPosition = 0

    inner class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ally = itemView.findViewById<TextView>(R.id.alltext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.all_designfile, parent, false)
        return MyviewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyviewHolder, @SuppressLint("RecyclerView") position: Int) {
        val itemCount = arrayList[position]
        holder.ally.text = itemCount.all

        val drawable =
            ContextCompat.getDrawable(context, R.drawable.all_back)?.mutate() as GradientDrawable
        if (position == selectedPosition) {
            holder.apply {
                ally.setTextColor(Color.WHITE)
            }
            drawable.setColor(Color.BLACK)
        } else {
            holder.apply {
                ally.setTextColor(ContextCompat.getColor(context, R.color.text_color))
            }
            drawable.setColor(Color.WHITE)
        }
        holder.itemView.background = drawable
        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()

        }

    }
}