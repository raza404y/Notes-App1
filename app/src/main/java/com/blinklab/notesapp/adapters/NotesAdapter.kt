package com.blinklab.notesapp.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.fragments.HomeFragment
import com.blinklab.notesapp.fragments.HomeFragmentDirections
import com.blinklab.notesapp.models.NotesModel

class NotesAdapter(private val context: Context, private val array: ArrayList<NotesModel>,val navController: NavController) :
    RecyclerView.Adapter<NotesAdapter.MYViewHolder>() {

    val colorsList = arrayListOf("#D9E8FC", "#FFD8F4", "#FDE99D", "#B0E9CA", "#FFEADD", "#FCFAD9")

    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.note_title)
        val text = itemView.findViewById<TextView>(R.id.note_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_design, parent, false)
        return MYViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val noteItem = array[position]
        holder.title.text = noteItem.noteTitle
        holder.text.text = noteItem.noteText

        val drawable =
            ContextCompat.getDrawable(context, R.drawable.detail_back) as GradientDrawable
        drawable.setColor(Color.parseColor(colorsList[position % colorsList.size]))
        holder.itemView.background = drawable

        holder.itemView.setOnClickListener {
           val action = HomeFragmentDirections.actionHomeFragmentToReadNoteFramgent(noteItem)
            navController.navigate(action)
        }

    }

}