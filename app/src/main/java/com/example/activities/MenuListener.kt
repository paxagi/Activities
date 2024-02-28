package com.example.activities

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class MenuListener() {
    fun create(activity: AppCompatActivity, menu: Menu?): Boolean {
        activity.menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    fun itemSelected(activity: AppCompatActivity, item: MenuItem): Boolean {
        if (item.itemId == R.id.miClose) {
            activity.finish()
            return true
        }
        Toast.makeText(
            activity,
            "You clicked on ${item.title}",
            Toast.LENGTH_SHORT).
        show()
        return true
    }
}