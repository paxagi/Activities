package com.example.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class ActivityD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean = MenuListener().create(this, menu)
    override fun onOptionsItemSelected(item: MenuItem): Boolean = MenuListener().itemSelected(this, item)
}