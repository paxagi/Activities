package com.example.activities

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BluetoothChangeReceiver : BroadcastReceiver() {
    val TAG = "receiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        val bluetoothState = intent?.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) ?: return
        if (bluetoothState == BluetoothAdapter.STATE_ON) {
            context?.let { logAndToast(context, it.getString(R.string.bluetooth_enabled_label)) }
        } else if (bluetoothState == BluetoothAdapter.STATE_OFF) {
            context?.let { logAndToast(context, it.getString(R.string.bluetooth_disabled_label)) }
        }
    }

    private fun logAndToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)
    }
}