package com.abrselmantutorials.day1assignment


import android.content.Intent
import android.os.Bundle

import android.widget.Button

import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.text)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == RESULT_OK) {
            val dataFromOtherActivity = data?.getStringExtra("data")
            textView.text = dataFromOtherActivity
        }
    }
}