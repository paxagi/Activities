package com.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText

class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: C")
        setContentView(R.layout.activity_c)

        val message = intent.getStringExtra("msg")
        findViewById<EditText>(R.id.editTextText4).setText(message)
        setResult(RESULT_OK, intent.putExtra("back msg", "back to C"))
    }


    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart: C")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume: C")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause: C")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop: C")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart: C")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy: C")
    }

}