package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class ActivityB : AppCompatActivity() {

    private lateinit var editDescription: EditText
    private lateinit var textViews:Array<TextView>
    private fun toastAndLogD(textView: View) {
        Toast.makeText(this, "${textView.id}", Toast.LENGTH_SHORT).show()
        Log.d("invoke", "toastAndLogD: touch a $textView")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: B")
        setContentView(R.layout.activity_b)
        editDescription = findViewById<EditText>(R.id.editTextTextB)
        textViews = Array<TextView>(5) {
            i -> findViewById<ConstraintLayout>(R.id.textViews).getChildAt(i) as TextView
        }
        textViews.forEach {
            it.setOnClickListener {
                toastAndLogD(it)
            }
        }

        startActivityForResult(
            Intent(this, ActivityC::class.java)
                .putExtra("msg", "open C"),
            0
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.getStringExtra("back msg").toString()
            .also {
                editDescription.setText(it)
            }
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