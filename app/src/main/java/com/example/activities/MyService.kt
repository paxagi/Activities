package com.example.activities

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {
    protected val TAG = "MyService"
    private val myBinder = MyBinder()
    private fun logdAndToast(cycle: String) {
        Log.d(TAG, "RunningService: $cycle")
    }
    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService
    }
    init {
        Log.d(TAG, "Service is running...")
    }

    override fun onCreate() {
        super.onCreate()
        logdAndToast("onCreate")
    }
    override fun onBind(intent: Intent?): IBinder {
        logdAndToast("onBind")
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        logdAndToast("onUnbind")
        return super.onUnbind(intent)
    }
    fun greeting(): String {
        Log.d(TAG, "Hello!")
        return "Hello"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d(TAG, dataString)
        }
        Thread {
            while (true) {}
        }.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        logdAndToast("onDestroy")
    }
}