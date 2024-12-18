package com.blinklab.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.blinklab.notesapp.databinding.FragmentReadNoteFramgentBinding

class ReadNoteFragment : Fragment() {
    private val navArg: ReadNoteFragmentArgs by navArgs()
    private lateinit var  binding : FragmentReadNoteFramgentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentReadNoteFramgentBinding.inflate(layoutInflater)

        val note = navArg.myNote
        binding.noteTitle.text= note.noteTitle
        binding.note.text = note.noteText

        binding.backArrowNote.setOnClickListener {
            val intent = Intent (requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return  binding.root
    }



}