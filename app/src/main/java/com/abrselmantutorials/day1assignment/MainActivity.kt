package com.abrselmantutorials.day1assignment

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data: Uri? = intent?.data
        data?.let { it
            Toast.makeText(this@MainActivity, "Hi there", Toast.LENGTH_SHORT).show()}
    }
}