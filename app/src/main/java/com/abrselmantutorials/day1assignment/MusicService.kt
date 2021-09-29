package com.abrselmantutorials.day1assignment

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class MusicService : Service() {
    private var data by Delegates.notNull<Int>()
    private val TAG = this::class.java.simpleName


    // Binder given to clients
    private val binder = LocalBinder()


    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Music download started", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        data = intent?.getIntExtra(Constants.DATA_COUNTER, 0)!!
        Log.d(TAG, "Music 1 is downloading")
        Thread.sleep(500)
        for (i in 1 until data) {
            Log.d(TAG, "Music $i downloaded")
            Thread.sleep(500)
            Log.d(TAG, "Music ${i + 1} is downloading")
        }
        Log.d(TAG, "Music $data is downloaded")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Toast.makeText(this, "Music download ended", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MusicService = this@MusicService
    }

    fun add(a: Int, b: Int) = a + b
}