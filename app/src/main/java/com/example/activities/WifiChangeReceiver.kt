package com.example.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast

class WifiChangeReceiver : BroadcastReceiver() {
    val TAG = "receiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        val wifiState = intent?.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1) ?: return
        if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
            context?.let { logAndToast(context, it.getString(R.string.wifi_enabled_label)) }
        } else if (wifiState == WifiManager.WIFI_STATE_DISABLING) {
            context?.let { logAndToast(context, it.getString(R.string.wifi_disabled_label)) }
        }
    }

    private fun logAndToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)
    }
}