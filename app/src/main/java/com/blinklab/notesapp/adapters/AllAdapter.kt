package com.blinklab.notesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.dataclass.AllDataclass

class AllAdapter(var arrayList: ArrayList<AllDataclass>):
        RecyclerView.Adapter<AllAdapter.MyviewHolder>(){

            inner class MyviewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
                val ally=itemView.findViewById<TextView>(R.id.alltext)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_designfile,parent,false)
        return MyviewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val itemCount=arrayList[position]
        holder.ally.text=itemCount.all
    }
}