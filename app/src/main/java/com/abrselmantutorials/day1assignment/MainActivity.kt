package com.abrselmantutorials.day1assignment

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),SwipeListener {
    lateinit var sharedPref: SharedPreferences
    lateinit var fName: EditText
    lateinit var lName: EditText
    lateinit var lastEntry: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var feedReaderDbHelper: FeedReaderDbHelper
    lateinit var dataList: ArrayList<Data>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        initializePreferences()
        feedReaderDbHelper = FeedReaderDbHelper(this)
        dataList = feedReaderDbHelper.getCursorValues()
    }

    private fun initializePreferences() {
        sharedPref = getSharedPreferences("PhilipsPreferences", Context.MODE_PRIVATE)
    }

    private fun initializeViews() {
        fName = findViewById(R.id.fName)
        lName = findViewById(R.id.lName)
        lastEntry = findViewById(R.id.lastEntry)
        recyclerView = findViewById(R.id.recyclerView)
    }

    override fun onResume() {
        super.onResume()
        populateData()
    }

    private fun populateData() {
        fName.setText(sharedPref.getString("fName", "defaultFName"))
        lName.setText(sharedPref.getString("lName", "defaultLName"))
        val data = feedReaderDbHelper.getValues()
        lastEntry.setText("${data.fName} \n ${data.lName}")
        val colNames = arrayOf(
            FeedReaderContract.FeedEntry.COLUMN_F_NAME,
            FeedReaderContract.FeedEntry.COLUMN_L_NAME
        )
        val toArray = intArrayOf(android.R.id.text1, android.R.id.text2)
        //get the adapter
        recyclerView.adapter = RecyAdapter(dataList,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    override fun onPause() {
        super.onPause()
        storeData()
    }

    private fun storeData() {
        val fNameText = fName.text
        val lNameText = lName.text
        if (!fNameText.isNullOrBlank()) putValues("fName", fNameText.toString())
        if (!lNameText.isNullOrBlank()) putValues("lName", lNameText.toString())
        storeInDb(fNameText.toString(), lNameText.toString())
    }

    private fun storeInDb(fNameText: String, lNameText: String) {
        feedReaderDbHelper.putValues(Data(fNameText, lNameText))
    }

    private fun putValues(key: String, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun onClick(position: Int) {
        Toast.makeText(this, "itemClicked on position is $position", Toast.LENGTH_SHORT).show()
    }

    override fun onLongClick(position: Int) {
        Toast.makeText(this, "itemLongClicked on position is $position", Toast.LENGTH_SHORT).show()
    }
}