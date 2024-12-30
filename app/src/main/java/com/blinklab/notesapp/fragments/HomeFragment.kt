package com.blinklab.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blinklab.notesapp.R
import com.blinklab.notesapp.adapters.AllAdapter
import com.blinklab.notesapp.adapters.DateAdapters
import com.blinklab.notesapp.adapters.NotesAdapter
import com.blinklab.notesapp.database.dao.NoteDAO
import com.blinklab.notesapp.database.db.NoteDatabase
import com.blinklab.notesapp.database.entities.Note
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

    private lateinit var noteDao: NoteDAO
    private lateinit var notesAdapter: NotesAdapter

    private lateinit var allArrayList: ArrayList<AllDataclass>
    private lateinit var adapter: AllAdapter

    private lateinit var detailArrayList: ArrayList<Note>
    private lateinit var adapterr: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        noteDao = NoteDatabase.createDatabase(requireContext()).getNoteDao()

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


        val adapter = DateAdapters(requireContext(), dayList) { selectedDate ->
            noteDao.getNotesByDate(selectedDate).observe(viewLifecycleOwner) { noteList ->
                if (noteList.isNotEmpty()) {
                    notesAdapter.updateList(noteList)
                } else {
                    notesAdapter.updateList(emptyList())
                    Toast.makeText(requireContext(), "No Note Available", Toast.LENGTH_SHORT).show()
                }

            }
        }
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


        val adappte = AllAdapter(requireContext(), allArrayList) { selectedPriority ->
            if (selectedPriority == "All") {
                noteDao.getAllNotes().observe(viewLifecycleOwner) { notesList ->
                    if (notesList.isNotEmpty()) {
                        notesAdapter.updateList(notesList)
                    } else {
                        notesAdapter.updateList(emptyList())
                        Toast.makeText(requireContext(), "No Note Available", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                noteDao.getPriorityNotes(selectedPriority)
                    .observe(viewLifecycleOwner) { notesList ->
                        if (notesList.isNotEmpty()) {
                            notesAdapter.updateList(notesList)
                        } else {
                            notesAdapter.updateList(emptyList())
                            Toast.makeText(
                                requireContext(),
                                "No Note Available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

        }



        Allrecycler.adapter = adappte
        val allly = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        Allrecycler.layoutManager = (allly)


        val detailRecycle = binding.detailRecycler
        detailArrayList = ArrayList()


        notesAdapter = NotesAdapter(requireContext(), detailArrayList, navController){selectedNote->
            noteDao.deleteNote(selectedNote)
        }
        detailRecycle.adapter = notesAdapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.detailRecycler.layoutManager = staggeredGridLayoutManager

        noteDao.getAllNotes().observe(viewLifecycleOwner) { note ->
            detailArrayList.clear()
            detailArrayList.addAll(note)
            notesAdapter.notifyDataSetChanged()
            binding.detailRecycler.post {
                binding.detailRecycler.smoothScrollToPosition(0)
            }
        }


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               val result = noteDao.getNotesBySearch(query!!)
                notesAdapter.updateList(result)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val result = noteDao.getNotesBySearch(newText!!)
                notesAdapter.updateList(result)
                return true
            }
        })

        return binding.root
    }

    private fun gettingLastDaysOfWeeks(): MutableList<DateDataclass> {
        val calender = Calendar.getInstance()
        val day = SimpleDateFormat("EEE", Locale.getDefault())
        val month = SimpleDateFormat("MMM", Locale.getDefault())
        val date = SimpleDateFormat("dd", Locale.getDefault())

        val list = mutableListOf<DateDataclass>()
        for (i in 0..6) {
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