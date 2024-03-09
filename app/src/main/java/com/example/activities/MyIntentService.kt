package com.example.activities

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.getString

class MyIntentService: IntentService("MyIntentService") {
    init {
        instance = this
    }
    companion object {
        private var onStopCB: (()->Unit)? = null
        private lateinit var instance: MyIntentService
        var isRunning = false
            private set
        fun stopService() {
            instance.stopSelf()
        }
        fun onStop( cb: (()->Unit)? ) { onStopCB = cb }
    }
    override fun onHandleIntent(intent: Intent?) {
        try {
            isRunning = true

                Log.d("MyIntentService", "Service is running...")
                Thread.sleep(3000)

        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        onStopCB?.let { it() }
        Log.d("MyIntentService", "Service is stopping...")
    }
}