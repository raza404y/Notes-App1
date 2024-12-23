package com.blinklab.notesapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.blinklab.notesapp.MainActivity
import com.blinklab.notesapp.R
import com.blinklab.notesapp.adapters.DeleteAdapter
import com.blinklab.notesapp.databinding.FragmentDeleteBinding
import com.blinklab.notesapp.models.DeleteDataClass

class DeleteFragment : Fragment() {
    private lateinit var binding: FragmentDeleteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteBinding.inflate(layoutInflater)

        binding.backArrowLike.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        val delrecyler = binding.deleterecycler
        val deleteArray = ArrayList<DeleteDataClass>()
        for (i in 1..10) {
            deleteArray.add(DeleteDataClass("Products Detail", "1.Review of Previous…"))

        }
        val adap = DeleteAdapter(requireContext(), deleteArray)
        delrecyler.adapter = adap
        val del = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        delrecyler.layoutManager = del
        return binding.root
    }

}