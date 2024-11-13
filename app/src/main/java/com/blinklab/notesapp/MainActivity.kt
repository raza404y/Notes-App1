package com.blinklab.notesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blinklab.notesapp.adapters.AllAdapter
import com.blinklab.notesapp.adapters.DateAdapters
import com.blinklab.notesapp.adapters.DetailAdapter
import com.blinklab.notesapp.dataclass.AllDataclass
import com.blinklab.notesapp.dataclass.DateDataclass
import com.blinklab.notesapp.dataclass.DetailDataclass

class MainActivity : AppCompatActivity() {

    private lateinit var dateArrayList: ArrayList<DateDataclass>
    private lateinit var dateAdapters: DateAdapters

    private lateinit var allArrayList: ArrayList<AllDataclass>
    private lateinit var adapter: AllAdapter

    private lateinit var detailArrayList: ArrayList<DetailDataclass>
    private lateinit var adapterr: DetailAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val daterecycler= findViewById<RecyclerView>(R.id.daterecyclerView)
        dateArrayList=ArrayList()
        dateArrayList.add(DateDataclass("Wed","23","Oct"))
        dateArrayList.add(DateDataclass("Thu","24","Oct"))
        dateArrayList.add(DateDataclass("Fri","25","Oct"))
        dateArrayList.add(DateDataclass("Sat","26","Oct"))
        dateArrayList.add(DateDataclass("Sun","27","Oct"))
        dateArrayList.add(DateDataclass("Mon","28","Oct"))
        dateArrayList.add(DateDataclass("Tue","29","Oct"))

        val adapter = DateAdapters(dateArrayList)
        daterecycler.adapter=adapter
        val datee = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        daterecycler.layoutManager=(datee)

        val Allrecycler = findViewById<RecyclerView>(R.id.allrecyclerview)
        allArrayList=ArrayList()
        allArrayList.add(AllDataclass("All"))
        allArrayList.add(AllDataclass("Important"))
        allArrayList.add(AllDataclass("Lecture Notes"))
        allArrayList.add(AllDataclass("To-do lists"))
        allArrayList.add(AllDataclass("Shopping"))
        allArrayList.add(AllDataclass("Grocery"))
        allArrayList.add(AllDataclass("Expenditures"))
        allArrayList.add(AllDataclass("Dues"))

        val adappte =AllAdapter(allArrayList)
        Allrecycler.adapter=adappte
        val allly = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        Allrecycler.layoutManager=(allly)

        val detailRecycle =findViewById<RecyclerView>(R.id.detailRecycler)
        detailArrayList=ArrayList()
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

        val adapt = DetailAdapter(detailArrayList)
        detailRecycle.adapter=adapt
        detailRecycle.layoutManager=GridLayoutManager(this,2)

    }
}