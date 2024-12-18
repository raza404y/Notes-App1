package com.blinklab.notesapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.blinklab.notesapp.MainActivity
import com.blinklab.notesapp.R

class LikeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_like, container, false)

        val create = view.findViewById<ImageView>(R.id.back_arrow_like)
        create.setOnClickListener {
           val intent = Intent (requireContext(), MainActivity::class.java)
           startActivity(intent)
        }
        return view
    }

}