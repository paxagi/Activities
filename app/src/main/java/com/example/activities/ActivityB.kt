package com.example.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
    lateinit var spSelectNextActivity: Spinner

    private fun initViews() {
        editDescription = findViewById<EditText>(R.id.editTextTextB)
        btnNextActivity = findViewById<Button>(R.id.bnt_go_to_activity_C_or_D)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        spSelectNextActivity = findViewById(R.id.targetActivity)
    }

    private fun showToastAndLogD(
        textView: View,
        alternativeMessage: String? = textView.id.toString()
    ) {
        Toast.makeText(this, "$alternativeMessage", Toast.LENGTH_SHORT).show()
        Log.d("invoke", "toastAndLogD: touch a $textView")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: B")
        setContentView(R.layout.activity_b)
        initViews()

        val person = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PERSON_DATA_NAME, Person::class.java)!! as Person
        } else {
            intent.getParcelableExtra<Person>(EXTRA_PERSON_DATA_NAME) as Person
        }
        textView1.also {
            it.text = "${getString(R.string.tvPersonLabel)} ${person.name}"
        }

        textView1.setOnClickListener { showToastAndLogD(it, person.toString()) }
        textView2.setOnClickListener { showToastAndLogD(it) }
        textView3.setOnClickListener { showToastAndLogD(it) }
        textView4.setOnClickListener { showToastAndLogD(it) }
        textView5.setOnClickListener { showToastAndLogD(it) }
        val goToC = getString(R.string.item_goToC)
        val goToD = getString(R.string.item_goToD)


        btnNextActivity.setOnClickListener{
            val selectedItem = spSelectNextActivity.selectedItem
            when (selectedItem){
                goToC ->
                    startActivityForResult(
                        Intent(this, ActivityC::class.java)
                            .putExtra("msg", "open C"),
                        0
                    )
                goToD ->
                    startActivity(
                        Intent(this, ActivityD::class.java)
                    )
            }
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

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean = MenuListener().create(this, menu)
    override fun onOptionsItemSelected(item: MenuItem): Boolean = MenuListener().itemSelected(this, item)
}