package com.blinklab.notesapp.adapters

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
import com.blinklab.notesapp.models.DetailDataclass

class DetailAdapter(private val context: Context, private val array: ArrayList<DetailDataclass>) :
    RecyclerView.Adapter<DetailAdapter.MYViewHolder>() {

    val colorsList = arrayListOf("#D9E8FC", "#FFD8F4", "#FDE99D", "#B0E9CA", "#FFEADD", "#FCFAD9")

    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteText = itemView.findViewById<TextView>(R.id.detail_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.detail_designfile, parent, false)
        return MYViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val itemCount = array[position]
        holder.noteText.text = itemCount.detail

        val drawable =
            ContextCompat.getDrawable(context, R.drawable.detail_back) as GradientDrawable
        drawable.setColor(Color.parseColor(colorsList[position % colorsList.size]))
        holder.noteText.background = drawable


    }

}