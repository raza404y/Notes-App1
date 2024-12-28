package com.blinklab.notesapp.fragments

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import com.blinklab.notesapp.MainActivity
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
        binding.note.text = HtmlCompat.fromHtml(note.noteText, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.note.movementMethod = LinkMovementMethod.getInstance()

        binding.backArrowNote.setOnClickListener {
            val intent = Intent (requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        return  binding.root
    }



}