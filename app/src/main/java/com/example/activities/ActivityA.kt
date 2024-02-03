package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: A")
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, ActivityB::class.java))

        findViewById<Button>(R.id.btn_apply).setOnClickListener {
            val name = findViewById<EditText>(R.id.first_name).text.toString()
            Toast.makeText(
                this,
                name,
                Toast.LENGTH_SHORT
            ).show()
            Log.d("listener", "name: $name")
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart: A")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume: A")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause: A")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop: A")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart: A")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy: A")
    }
}