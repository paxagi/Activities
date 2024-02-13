package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class ActivityA : AppCompatActivity() {

    private val city: EditText by lazy { findViewById(R.id.etCity) }
    private val street: EditText by lazy { findViewById(R.id.etStreet) }
    private val house: EditText by lazy { findViewById(R.id.etHouse) }

private val name: String
        get() = findViewById<EditText>(R.id.first_name).text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: A")

        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_apply).setOnClickListener {
            Toast.makeText(
                this,
                name,
                Toast.LENGTH_SHORT
            ).show()
            Log.d("listener", "name: $name")
        }
        fun etLogD(text: Editable?) = Log.d("etListen", "text changed: $text")
        city.addTextChangedListener { etLogD(it) }
        street.addTextChangedListener { etLogD(it) }
        house.addTextChangedListener { etLogD(it) }

        startActivity(Intent(this, ActivityB::class.java))
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