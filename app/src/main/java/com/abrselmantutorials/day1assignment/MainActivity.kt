package com.abrselmantutorials.day1assignment

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.CallLog
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        populateWithCallLogs()
    }

    private fun populateWithCallLogs() {
        val allCalls: Uri = Uri.parse("content://call_log/calls")
        val cursor: Cursor = managedQuery(allCalls, null, null, null, null)

        var colNames = arrayOf(CallLog.Calls.NUMBER, CallLog.Calls.DURATION)
        var toArray = intArrayOf(android.R.id.text1, android.R.id.text2)

        var adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2, //row layout
            cursor, //data
            colNames,
            toArray) // array of textviews in each row

        listView.adapter = adapter
    }
}