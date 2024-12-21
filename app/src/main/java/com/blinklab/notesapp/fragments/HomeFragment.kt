package com.blinklab.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import com.blinklab.notesapp.models.DayModel
import com.blinklab.notesapp.models.NotesModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var dateArrayList: ArrayList<DateDataclass>
    private lateinit var dateAdapters: DateAdapters

    private lateinit var allArrayList: ArrayList<AllDataclass>
    private lateinit var adapter: AllAdapter

    private lateinit var detailArrayList: ArrayList<NotesModel>
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
            navController.navigate(R.id.action_homeFragment_to_addNewNote)
        }

        /*val daterecycler= view?.findViewById<RecyclerView>(R.id.daterecyclerView)*/
        val daterecycler = binding.daterecyclerView
        val dayList = gettingLastDaysOfWeeks()


        val adapter = DateAdapters(requireContext(), dayList)
        daterecycler.adapter = adapter
        val datee = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        daterecycler.layoutManager = datee

        /* val Allrecycler = view?.findViewById<RecyclerView>(R.id.allrecyclerview)*/
        val Allrecycler = binding.allrecyclerview
        allArrayList = ArrayList()
        allArrayList.add(AllDataclass("All"))
        allArrayList.add(AllDataclass("Important"))
        allArrayList.add(AllDataclass("Lecture Notes"))
        allArrayList.add(AllDataclass("Shopping"))
        allArrayList.add(AllDataclass("Grocery"))

        /*
        * let say user note a lecture a or something about shopping or important
        * when user will save how hhe wil decide what type of this note iis */
        // how to handle it ?
        // aik tu es trh k jab add k button py click krain tu pehly wo
        // category pochy  us k baad kuch likh k us mn save kr dy
        //secon kuch bhi likhny k baad jab save krny lgy tab different categories k option
        // ain aur jis py click krain us mn save ho
        // es k leye custom dialog alert design kro then jab user note k baad click kry ga save per
        // tu dialog show user will select it is a important lecutre etc..
        // ek new file bnao priority_dialog
        // 4 text views lo
        // first mien important second mien lecuture then shopping then grocery sai
        //aur aik first text bhi lgy ga k text select krain? han
        //size kasy lena es ka
        // text size? g ek bari simple he rkho 14sp then adust krty hain okay

        val adappte = AllAdapter(requireContext(), allArrayList)
        Allrecycler.adapter = adappte
        val allly = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        Allrecycler.layoutManager = (allly)


        val detailRecycle = binding.detailRecycler
        detailArrayList = ArrayList()

        for (i in 1..15) {
            detailArrayList.add(
                NotesModel(
                    "Customer Insights Discussion",
                    ContextCompat.getString(requireContext(), R.string.blank_text)
                )
            )
        }
        val adapt = NotesAdapter(requireContext(), detailArrayList, navController)
        detailRecycle.adapter = adapt
        detailRecycle.layoutManager = GridLayoutManager(requireContext(), 2)


        return binding.root
    }

    private fun gettingLastDaysOfWeeks(): MutableList<DateDataclass> {
        val calender = Calendar.getInstance()
        val day = SimpleDateFormat("EEE", Locale.getDefault())
        val month = SimpleDateFormat("MMM", Locale.getDefault())
        val date = SimpleDateFormat("dd", Locale.getDefault())

        val list = mutableListOf<DateDataclass>()
        for (i in 0..30) {
            list.add(
                DateDataclass(
                    dayA = day.format(calender.time),
                    monthA = month.format(calender.time),
                    dateA = date.format(calender.time)
                )
            )
            calender.add(Calendar.DAY_OF_MONTH, -1)
        }

        return list
    }
}