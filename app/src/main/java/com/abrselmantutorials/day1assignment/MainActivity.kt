package com.abrselmantutorials.day1assignment

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {
    var myVariable = 10
    private lateinit var btnDoAsync: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDoAsync = findViewById(R.id.btnDoAsync)
        progressBar = findViewById(R.id.progressBar)
        textView=findViewById(R.id.textView)
        btnDoAsync.setOnClickListener {
            val task = MyAsyncTask(this)
            task.execute(10)
        }
    }

    companion object {
        class MyAsyncTask internal constructor(context: MainActivity) :
            AsyncTask<Int, String, String?>() {

            private var resp: String? = null
            private val activityReference: WeakReference<MainActivity> = WeakReference(context)

            override fun onPreExecute() {
                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                activity.progressBar.visibility = View.VISIBLE
            }

            override fun doInBackground(vararg params: Int?): String? {
                publishProgress("Sleeping Started") // Calls onProgressUpdate()
                try {
                    val time = params[0]?.times(1000)
                    time?.toLong()?.let { Thread.sleep(it / 2) }
                    publishProgress("Half Time") // Calls onProgressUpdate()
                    time?.toLong()?.let { Thread.sleep(it / 2) }
                    publishProgress("Sleeping Over") // Calls onProgressUpdate()
                    resp = "Android was sleeping for " + params[0] + " seconds"
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    resp = e.message
                } catch (e: Exception) {
                    e.printStackTrace()
                    resp = e.message
                }

                return resp
            }


            override fun onPostExecute(result: String?) {

                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return
                activity.progressBar.visibility = View.GONE
                activity.textView.text = result.let { it }
                activity.myVariable = 100
            }

            override fun onProgressUpdate(vararg text: String?) {

                val activity = activityReference.get()
                if (activity == null || activity.isFinishing) return

                Toast.makeText(activity, text.firstOrNull(), Toast.LENGTH_SHORT).show()

            }
        }
    }
}