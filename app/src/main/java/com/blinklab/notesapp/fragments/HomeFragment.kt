package com.blinklab.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blinklab.notesapp.R
import com.blinklab.notesapp.adapters.AllAdapter
import com.blinklab.notesapp.adapters.DateAdapters
import com.blinklab.notesapp.adapters.NotesAdapter
import com.blinklab.notesapp.databinding.FragmentHomeBinding
import com.blinklab.notesapp.models.AllDataclass
import com.blinklab.notesapp.models.DateDataclass
import com.blinklab.notesapp.models.DetailDataclass


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var dateArrayList: ArrayList<DateDataclass>
    private lateinit var dateAdapters: DateAdapters

    private lateinit var allArrayList: ArrayList<AllDataclass>
    private lateinit var adapter: AllAdapter

    private lateinit var detailArrayList: ArrayList<DetailDataclass>
    private lateinit var adapterr: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val navController = findNavController()
        binding.likeButton.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_likeFragment)
        }

        binding.addNewBtn.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

        /*val daterecycler= view?.findViewById<RecyclerView>(R.id.daterecyclerView)*/
        val daterecycler = binding.daterecyclerView
        dateArrayList = ArrayList()
        dateArrayList.add(DateDataclass("Wed", "23", "Oct"))
        dateArrayList.add(DateDataclass("Thu", "24", "Oct"))
        dateArrayList.add(DateDataclass("Fri", "25", "Oct"))
        dateArrayList.add(DateDataclass("Sat", "26", "Oct"))
        dateArrayList.add(DateDataclass("Sun", "27", "Oct"))
        dateArrayList.add(DateDataclass("Mon", "28", "Oct"))
        dateArrayList.add(DateDataclass("Tue", "29", "Oct"))

        val adapter = DateAdapters(requireContext(),dateArrayList)
        daterecycler.adapter = adapter
        val datee = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        daterecycler.layoutManager = (datee)

        /* val Allrecycler = view?.findViewById<RecyclerView>(R.id.allrecyclerview)*/
        val Allrecycler = binding.allrecyclerview
        allArrayList = ArrayList()
        allArrayList.add(AllDataclass("All"))
        allArrayList.add(AllDataclass("Important"))
        allArrayList.add(AllDataclass("Lecture Notes"))
        allArrayList.add(AllDataclass("To-do lists"))
        allArrayList.add(AllDataclass("Shopping"))
        allArrayList.add(AllDataclass("Grocery"))
        allArrayList.add(AllDataclass("Expenditures"))
        allArrayList.add(AllDataclass("Dues"))

        val adappte = AllAdapter(requireContext(),allArrayList)
        Allrecycler.adapter = adappte
        val allly = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        Allrecycler.layoutManager = (allly)

        /*val detailRecycle =view?.findViewById<RecyclerView>(R.id.detailRecycler)*/
        val detailRecycle = binding.detailRecycler
        detailArrayList = ArrayList()
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))
        detailArrayList.add(DetailDataclass("1.Review of Previous\n    Action Items\n2.Product Development\n    Update\n3.User Feedback and\n    Customer Insights\n4.Competitive Analysis\n5.Roadmap Discussion"))

        val adapt = NotesAdapter(requireContext(), detailArrayList)
        detailRecycle.adapter = adapt
        detailRecycle.layoutManager = GridLayoutManager(requireContext(), 2)


        return binding.root
    }

}