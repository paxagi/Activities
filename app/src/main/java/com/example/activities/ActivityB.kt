package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: B")
        setContentView(R.layout.activity_b)

        startActivityForResult(
            Intent(this, ActivityC::class.java)
                .putExtra("msg", "open C"),
            0
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = intent.getStringExtra("back msg")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart: B")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume: B")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause: B")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop: B")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart: B")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy: B")
    }
}