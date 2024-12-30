package com.blinklab.notesapp.fragments

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.blinklab.notesapp.MainActivity
import com.blinklab.notesapp.R
import com.blinklab.notesapp.database.dao.NoteDAO
import com.blinklab.notesapp.database.db.NoteDatabase
import com.blinklab.notesapp.database.entities.Note
import com.blinklab.notesapp.databinding.FragmentUpdateNoteBinding
import com.blinklab.notesapp.databinding.PriortyDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class Update_Note : Fragment() {

    private lateinit var binding: FragmentUpdateNoteBinding
    private var id = -1
    private lateinit var noteTitle: String
    private lateinit var noteText: String
    private lateinit var priority : String
    private lateinit var noteDAO: NoteDAO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater)

        noteDAO = NoteDatabase.createDatabase(requireContext()).getNoteDao()

        textFormatingOptions()
        id = arguments?.getInt("id", -1)!!
        noteTitle = arguments?.getString("title").toString()
        noteText = arguments?.getString("note").toString()

        binding.noteTitle.setText(noteTitle)
        binding.editor.html = noteText

        binding.saveBtn.setOnClickListener {
            priorityDialog()
        }

        return binding.root
    }
    private fun priorityDialog() {
        val dialog  = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        val binding = PriortyDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.8).toInt(), // 0.8 mean 80% width of screen ok
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(R.drawable.round_dialog_bg)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        binding.important.setOnClickListener {
            priority = "Important"
            dialog.dismiss()
        }
        binding.lecture.setOnClickListener {
            priority= "Lecture Notes"
            dialog.dismiss()
        }
        binding.shopping.setOnClickListener {
            priority="Shopping"
            dialog.dismiss()
        }
        binding.grocery.setOnClickListener {
            priority="Grocery"
            dialog.dismiss()
        }
        dialog.setOnDismissListener {
            saveNoteInDatabase()
        }


        dialog.show()
    }

    private fun saveNoteInDatabase() {
        val noteTitle = binding.noteTitle.text.toString().trim()
        val noteText = binding.editor.html.toString().trim()
        if (noteTitle.isEmpty()) {
            showToast("Enter Title")
        }else if (noteText.isEmpty()) {
            showToast("Enter Text")
        }else{
            val calender = Calendar.getInstance()
            val date = SimpleDateFormat("dd", Locale.getDefault()).format(calender.time)
            val note = Note(id,noteTitle,noteText,priority,date)
            noteDAO.updateNote(note)
            showToast("Note Updated Successfully")
            binding.noteTitle.setText("")
            binding.editor.html=""
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }

    private fun textFormatingOptions() {
        binding.apply {
            boldBtn.setOnClickListener { editor.setBold() }
            italicBtn.setOnClickListener { editor.setItalic() }
            underlineBtn.setOnClickListener { editor.setUnderline() }
            alignCenterBtn.setOnClickListener { editor.setAlignCenter() }
            alignLeftBtn.setOnClickListener { editor.setAlignLeft() }
            alignRightBtn.setOnClickListener { editor.setAlignRight() }
            textBtn.setOnClickListener { editor.setTextBackgroundColor(Color.YELLOW) }
            pointsBtn.setOnClickListener { editor.setBullets() }
        }
    }
    private fun showToast(msg:String){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}