package com.abrselmantutorials.day1assignment

import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    lateinit var sharedPref: SharedPreferences
    lateinit var fName: EditText
    lateinit var lName: EditText
    lateinit var lastEntry: TextView
    lateinit var listView: ListView
    lateinit var feedReaderDbHelper: FeedReaderDbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        initializePreferences()
        feedReaderDbHelper = FeedReaderDbHelper(this)
    }

    private fun initializePreferences() {
        sharedPref = getSharedPreferences("PhilipsPreferences", Context.MODE_PRIVATE)
    }

    private fun initializeViews() {
        fName = findViewById(R.id.fName)
        lName = findViewById(R.id.lName)
        lastEntry = findViewById(R.id.lastEntry)
        listView = findViewById(R.id.listView)
        listView.setOnItemClickListener(this)
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
        val simpleCursorAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_expandable_list_item_2,
            feedReaderDbHelper.getCursorValues(), colNames, toArray
        )
        listView.adapter = simpleCursorAdapter
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val itemClicked = parent?.getItemAtPosition(position) as Cursor
        val titleIndex: Int =
            itemClicked.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_F_NAME)
        val fName: String = itemClicked.getString(titleIndex)


        Toast.makeText(this, fName, Toast.LENGTH_SHORT).show()
    }
}