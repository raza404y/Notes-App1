package com.blinklab.notesapp.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.database.entities.Note
import com.blinklab.notesapp.databinding.EditDeleteDialogBinding
import com.blinklab.notesapp.databinding.PriortyDialogBinding
import com.blinklab.notesapp.fragments.HomeFragmentDirections

class NotesAdapter(
    private val context: Context,
    private var noteList: List<Note>,
    val navController: NavController,
) :
    RecyclerView.Adapter<NotesAdapter.MYViewHolder>() {

    val colorsList = arrayListOf("#D9E8FC", "#FFD8F4", "#FDE99D", "#B0E9CA", "#FFEADD", "#FCFAD9")

    inner class MYViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.note_title)
        val text = itemView.findViewById<TextView>(R.id.note_text)
        val priority = itemView.findViewById<TextView>(R.id.priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_design, parent, false)
        return MYViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val noteItem = noteList[position]
        holder.title.text = noteItem.noteTitle
        holder.priority.text=noteItem.priority
        holder.text.text = HtmlCompat.fromHtml(noteItem.noteText, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val drawable =
            ContextCompat.getDrawable(context, R.drawable.detail_back) as GradientDrawable
        drawable.setColor(Color.parseColor(colorsList[position % colorsList.size]))
        holder.itemView.background = drawable

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToReadNoteFramgent(noteItem)
            navController.navigate(action)
        }

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            val dialog  = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            val binding = EditDeleteDialogBinding.inflate(LayoutInflater.from(context))
            dialog.setContentView(binding.root)
            dialog.window?.setLayout(
                (context.resources.displayMetrics.widthPixels * 0.8).toInt(), // 0.8 mean 80% width of screen ok
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog.window?.setBackgroundDrawableResource(R.drawable.round_dialog_bg)
            dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation

            binding.cross.setOnClickListener { dialog.dismiss() }


            dialog.show()
            return@OnLongClickListener true
        })

    }

    fun updateList(newList: List<Note>) {
        noteList = newList
        notifyDataSetChanged()
    }
    private fun priorityDialog() {

    }
}