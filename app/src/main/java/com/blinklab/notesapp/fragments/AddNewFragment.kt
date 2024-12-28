package com.blinklab.notesapp.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.blinklab.notesapp.MainActivity
import com.blinklab.notesapp.R
import com.blinklab.notesapp.database.dao.NoteDAO
import com.blinklab.notesapp.database.db.NoteDatabase
import com.blinklab.notesapp.database.entities.Note
import com.blinklab.notesapp.databinding.FragmentAddNewBinding
import com.blinklab.notesapp.databinding.PriortyDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddNewFragment : Fragment() {
    private lateinit var binding: FragmentAddNewBinding
    private lateinit var priority : String
    private lateinit var noteDAO: NoteDAO


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding = FragmentAddNewBinding.inflate(layoutInflater)

        binding.backArrowNote.setOnClickListener {
            val intent = Intent (requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val objDatabase = NoteDatabase.createDatabase(requireContext())
        noteDAO = objDatabase.getNoteDao()


        binding.apply {
            editor.setEditorHeight(300)
            editor.setFontSize(20)
            editor.setPlaceholder("Write something here...")
            editor.setPadding(16,10,16,16)
        }
        textFormatingOptions()

        binding.saveBtn.setOnClickListener {

            priorityDialog()

        }

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
            val date = SimpleDateFormat("dd",Locale.getDefault()).format(calender.time)
            val note = Note(null,noteTitle,noteText,priority,date)
            noteDAO.insertNote(note)
            showToast("Note Saved Successfully")
            binding.noteTitle.setText("")
            binding.editor.html=""
           startActivity(Intent(requireContext(),MainActivity::class.java))
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