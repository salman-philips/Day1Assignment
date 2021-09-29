package com.abrselmantutorials.day1assignment

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var sharedPref: SharedPreferences
    lateinit var fName: EditText
    lateinit var lName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        initializePreferences()
    }

    private fun initializePreferences() {
        sharedPref = getSharedPreferences("PhilipsPreferences", Context.MODE_PRIVATE)
    }

    private fun initializeViews() {
        fName = findViewById(R.id.fName)
        lName = findViewById(R.id.lName)
    }

    override fun onResume() {
        super.onResume()
        populateData()

    }

    private fun populateData() {
        fName.setText(sharedPref.getString("fName", "defaultFName"))
        lName.setText(sharedPref.getString("lName", "defaultLName"))
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
    }

    private fun putValues(key: String, value: String) {
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }
}