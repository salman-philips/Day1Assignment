package com.abrselmantutorials.day1assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var countryList = ArrayList<String>()
    private lateinit var countryAdapter: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        populateData()
        countryAdapter = CountryAdapter(countryList)
        recyclerView.adapter = countryAdapter
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.hasFixedSize()
    }

    private fun populateData() {
        for (locale in Locale.getAvailableLocales()) {
            val country: String = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countryList.contains(country)) {
                countryList.add(country)
            }
        }
        countryList.sort()
        for (country in countryList) {
            println(country)
        }
    }
}