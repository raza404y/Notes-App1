package com.blinklab.notesapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blinklab.notesapp.MainActivity
import com.blinklab.notesapp.databinding.FragmentAddNewBinding

class AddNewFragment : Fragment() {
    private lateinit var binding: FragmentAddNewBinding


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


        binding.apply {
            editor.setEditorHeight(300)
            editor.setFontSize(20)
            editor.setPlaceholder("Write something here...")
            editor.setPadding(16,10,16,16)
        }
        textFormatingOptions()

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
}