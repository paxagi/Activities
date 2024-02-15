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
    private lateinit var etName: EditText
    private lateinit var btnNextActivity: Button
    private lateinit var city: EditText
    private lateinit var street: EditText
    private lateinit var house: EditText
    private lateinit var btnApply: Button

    private fun initViews() {
        etName = findViewById<EditText>(R.id.first_name)
        btnNextActivity = findViewById<Button>(R.id.bnt_go_to_activityB)
        city = findViewById(R.id.etCity)
        street = findViewById(R.id.etStreet)
        house = findViewById(R.id.etHouse)
        btnApply = findViewById<Button>(R.id.btn_apply)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: A")

        setContentView(R.layout.activity_main)
        initViews()

        btnApply.setOnClickListener {
            Toast.makeText(
                this,
                etName.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
            Log.d("listener", "name: $etName.text.toString()")
        }

        fun etLogD(text: Editable?) = Log.d("etListen", "text changed: $text")
        city.addTextChangedListener { etLogD(it) }
        street.addTextChangedListener { etLogD(it) }
        house.addTextChangedListener { etLogD(it) }

        btnNextActivity.setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
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