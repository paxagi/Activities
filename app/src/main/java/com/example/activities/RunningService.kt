package com.example.activities

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class RunningService: Service() {
    private val TAG = "RunningService"
    private fun logdAndToast(cycle: String) {
        Log.d(TAG, "RunningService: $cycle")
    }

    override fun onCreate() {
        super.onCreate()
        logdAndToast("onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logdAndToast("onStartCommand")
        when(intent?.action) {
            Action.START.toString() -> start()
            Action.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        logdAndToast("onDestroy")
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText("Foreground action")
            .build()
        startForeground(1, notification)
    }

    enum class Action {
        START, STOP
    }
}