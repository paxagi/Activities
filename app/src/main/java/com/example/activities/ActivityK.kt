package com.example.activities

import android.app.Application
import android.content.ClipDescription
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityK : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_k)


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flTable, DNDFragment.newInstance())
            commit()
        }
    }


}
val dragListener = View.OnDragListener { view, event ->
    when(event.action) {
        DragEvent.ACTION_DRAG_STARTED -> {
            event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
        }
        DragEvent.ACTION_DRAG_ENTERED -> {
            view.invalidate()
            true
        }
        DragEvent.ACTION_DRAG_LOCATION -> true
        DragEvent.ACTION_DRAG_EXITED -> {
            view.invalidate()
            true
        }
        DragEvent.ACTION_DROP -> {
            val item = event.clipData.getItemAt(0)
            val dragData = item.text
            Log.d("DND", "$dragData")

            view.invalidate()

            val v = event.localState as View
            val owner = v.parent as ViewGroup
            owner.removeView(v)
            val destination = view as LinearLayout
            destination.addView(v)
            v.visibility = View.VISIBLE
            true
        }
        DragEvent.ACTION_DRAG_ENDED -> {
            view.invalidate()
            true
        }
        else -> false
    }
}