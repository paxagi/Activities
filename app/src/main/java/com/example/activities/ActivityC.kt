package com.example.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationBarView


class ActivityC : AppCompatActivity() {
    private lateinit var editDescription: EditText
    private lateinit var ivPhoto: ImageView
    private lateinit var btnTakePhotoOrPDF: Button
    private lateinit var statusOfSelectedResource: TextView
    private lateinit var bottomNavigationView: NavigationBarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate: C")
        setContentView(R.layout.activity_c)

        val message = intent.getStringExtra("msg")
        editDescription = findViewById<EditText>(R.id.edit_description)
        ivPhoto = findViewById<ImageView>(R.id.ivPhotoOrPDF)
        btnTakePhotoOrPDF = findViewById<Button>(R.id.btnTakePhotoOrPDF)
        statusOfSelectedResource = findViewById<TextView>(R.id.statusOfSelectedResource)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        editDescription.setText(message)
        setResult(RESULT_OK, intent.putExtra("back msg", "back to C"))

        btnTakePhotoOrPDF.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.addCategory(Intent.CATEGORY_OPENABLE)
                val mimeTypes = arrayOf("image/*", "application/pdf")
                it.setType("*/*")
                it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                startActivityForResult(it, 0)
            }
        }

        val images = arrayListOf<Int>(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
        )

        changeFragment({ HomeFragment() }, "home")
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.miHome -> changeFragment({ HomeFragment() }, "home")
                R.id.miMessages -> changeFragment({ MessagesFragment() }, "message")
                R.id.miProfile -> changeFragment(
                    { ProfileFragment.newInstance(images) },
                    "profile"
                )
            }
            return@setOnItemSelectedListener true
        }
        bottomNavigationView.getOrCreateBadge(R.id.miMessages).apply {
            number = 0
            isVisible = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 0) {
            val file = data?.data
            val type = file?.let { this.contentResolver.getType(it) }
            if (type.isNullOrEmpty()) {
                statusOfSelectedResource.text = getString(R.string.unknownTypeOfFile)
            } else {
                if (type.contains("image/")) {
                    ivPhoto.setImageURI(file)
                    statusOfSelectedResource.text = getString(R.string.empty_text)
                }
                if (type.contains("application/pdf")) {
                    statusOfSelectedResource.text = getString(R.string.PDF_Selected)
                    ivPhoto.setImageURI(null)
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart: C")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume: C")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause: C")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop: C")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("lifecycle", "onRestart: C")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy: C")
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean = MenuListener().create(this, menu)
    override fun onOptionsItemSelected(item: MenuItem): Boolean = MenuListener().itemSelected(this, item)

    private fun changeFragment(fragmentInit: (()->Fragment?), tagFragmentName: String) {
        var currentFragment: Fragment?
        var fragmentTemp: Fragment?
        supportFragmentManager.apply {
            currentFragment = primaryNavigationFragment
            fragmentTemp = findFragmentByTag(tagFragmentName)
        }.beginTransaction().apply {
            currentFragment?.let { hide(it) }
            if (fragmentTemp == null) {
                fragmentTemp = fragmentInit()
                add(R.id.flFragment, fragmentTemp!!, tagFragmentName)
            } else {
                show(fragmentTemp!!)
            }

            setPrimaryNavigationFragment(fragmentTemp)
            setReorderingAllowed(true)
            commitNowAllowingStateLoss()
        }
    }
}