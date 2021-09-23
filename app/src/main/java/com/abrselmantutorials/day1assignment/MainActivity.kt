package com.abrselmantutorials.day1assignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var callButton: Button
    lateinit var editText: EditText
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        editText = findViewById(R.id.name)
        textView = findViewById(R.id.text)
        callButton = findViewById(R.id.call)
        button.setOnClickListener {
            goToHomeActivity()
        }

        callButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:9741202116")
            this@MainActivity.startActivity(callIntent)
        }


    }

    private fun goToHomeActivity() {
        val nameFormEdit: String = editText.text.toString()
        if (nameFormEdit.isNullOrBlank()) {
            Toast.makeText(this@MainActivity, "please enter name", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            intent.putExtra("name", nameFormEdit)
            this@MainActivity.startActivity(intent)
        }
    }
}