package com.abrselmantutorials.day1assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var intentForService: Intent
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.service_text)
        intentForService = Intent(this@MainActivity, MusicService::class.java)
    }

    fun serviceHandler(view: View) {

        when (view.id) {
            R.id.start_service_button -> {
                val number = editText.text.toString()
                number.let {
                    intentForService.putExtra(Constants.DATA_COUNTER, it.toInt())
                    startService(intentForService)
                }
            }
            R.id.stop_service_button -> {

                stopService(intentForService)
            }
        }
    }
}