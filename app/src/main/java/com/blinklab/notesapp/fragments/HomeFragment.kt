package com.blinklab.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.R
import com.blinklab.notesapp.adapters.DateAdapters
import com.blinklab.notesapp.databinding.FragmentHomeBinding
import com.blinklab.notesapp.dataclass.DateDataclass


class HomeFragment : Fragment() {
   private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(layoutInflater)
        val navController = findNavController()
        binding.likeButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_likeFragment)
        }

       return binding.root
    }

}