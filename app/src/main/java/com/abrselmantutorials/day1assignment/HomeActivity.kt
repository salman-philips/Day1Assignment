package com.abrselmantutorials.day1assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        editText = findViewById(R.id.home_text_view)
    }

    fun handleFinish(view: View) {
        val data = editText.text.toString()
        val intent=Intent()
        intent.putExtra("data",data)
        setResult(RESULT_OK,intent)
        finish()
    }
}