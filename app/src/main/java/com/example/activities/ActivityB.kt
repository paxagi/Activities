package com.example.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ActivityB : AppCompatActivity() {

    private lateinit var editDescription: EditText
    private lateinit var btnNextActivity: Button

    lateinit var textView1: TextView
    lateinit var textView2: TextView
    lateinit var textView3: TextView
    lateinit var textView4: TextView
    lateinit var textView5: TextView

    private fun initViews() {
        editDescription = findViewById<EditText>(R.id.editTextTextB)
        btnNextActivity = findViewById<Button>(R.id.bnt_go_to_activityC)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)

    }

    private fun showToastAndLogD(textView: View) {
        Toast.makeText(this, "${textView.id}", Toast.LENGTH_SHORT).show()
        Log.d("invoke", "toastAndLogD: touch a $textView")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: B")
        setContentView(R.layout.activity_b)

        initViews()
        textView1.setOnClickListener { showToastAndLogD(it) }
        textView2.setOnClickListener { showToastAndLogD(it) }
        textView3.setOnClickListener { showToastAndLogD(it) }
        textView4.setOnClickListener { showToastAndLogD(it) }
        textView5.setOnClickListener { showToastAndLogD(it) }


        btnNextActivity.setOnClickListener{
            startActivityForResult(
                Intent(this, ActivityC::class.java)
                    .putExtra("msg", "open C"),
                0
            )
        }

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