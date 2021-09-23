package com.abrselmantutorials.day1assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var editText: EditText
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.name)
        textView = findViewById(R.id.text)
        button.setOnClickListener {
            val nameFormEdit: String = editText.text.toString()
            if (nameFormEdit.isNullOrBlank()) {
                Toast.makeText(this@MainActivity, "please enter name", Toast.LENGTH_SHORT).show()
            } else {
              val intent=Intent(this@MainActivity,HomeActivity::class.java)
                intent.putExtra("name",nameFormEdit)
                this@MainActivity.startActivity(intent)
            }
        }
    }
}