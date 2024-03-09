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
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityB : AppCompatActivity() {

    private lateinit var editDescription: EditText
    private lateinit var btnNextActivity: Button

    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var rvInterests: RecyclerView

    private fun initViews() {
        editDescription = findViewById<EditText>(R.id.editTextTextB)
        btnNextActivity = findViewById<Button>(R.id.bnt_go_to_activityC)
        textView1 = findViewById(R.id.textView1)
        textView2 = findViewById(R.id.textView2)
        textView3 = findViewById(R.id.textView3)
        textView4 = findViewById(R.id.textView4)
        textView5 = findViewById(R.id.textView5)
        rvInterests = findViewById(R.id.rvInterests)
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

        val person: Person? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_PERSON_DATA_NAME, Person::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_PERSON_DATA_NAME)
        }

        textView1.also {
            if (person != null) {
                it.text = "${getString(R.string.tvPersonLabel)} ${person.name}"
            }
        }

        textView1.setOnClickListener { showToastAndLogD(it, person.toString()) }
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

        var interestsList = mutableListOf(
            Interest(R.drawable.avatar, "Android"),
            Interest(R.drawable.avatar, "Kotlin"),
            Interest(R.drawable.avatar, "Poker"),
            Interest(R.drawable.avatar, "Sugar"),
            Interest(R.drawable.avatar, "Milk"),
            Interest(R.drawable.avatar, "Dart"),
            Interest(R.drawable.avatar, "Football"),
            Interest(R.drawable.avatar, "Volleyball"),
            Interest(R.drawable.avatar, "Video"),
            Interest(R.drawable.avatar, "Photo"),
            Interest(R.drawable.avatar, "Walking"),
            Interest(R.drawable.avatar, "Games"),
            Interest(R.drawable.avatar, "Movies"),
            Interest(R.drawable.avatar, "Moves"),
            Interest(R.drawable.avatar, "JS"),
            Interest(R.drawable.avatar, "Phone"),
            Interest(R.drawable.avatar, "Sleeping"),
            Interest(R.drawable.avatar, "Literature"),
            Interest(R.drawable.avatar, "Math"),
            Interest(R.drawable.avatar, "Tricks"),
            Interest(R.drawable.avatar, "Hints"),
        )

        val adapter = InterestsAdapter(interestsList)
        rvInterests.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@ActivityB)
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