package com.example.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener

class ActivityA : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var btnNextActivity: Button
    private lateinit var city: EditText
    private lateinit var street: EditText
    private lateinit var house: EditText
    private lateinit var btnApply: Button
    private lateinit var btnShowToast: Button
    private lateinit var etSurname: EditText
    private lateinit var etBirthday: EditText
    private lateinit var etCountry: EditText
    private lateinit var btnRequestPermissions: Button


    private fun initViews() {
        etName = findViewById<EditText>(R.id.first_name)
        btnNextActivity = findViewById<Button>(R.id.bnt_go_to_activityB)
        city = findViewById(R.id.etCity)
        street = findViewById(R.id.etStreet)
        house = findViewById(R.id.etHouse)
        btnApply = findViewById<Button>(R.id.btn_apply)
        btnShowToast = findViewById<Button>(R.id.btnShowToast)
        etSurname = findViewById<EditText>(R.id.etSurname)
        etBirthday = findViewById<EditText>(R.id.editBirthday)
        etCountry = findViewById<EditText>(R.id.etCountry)
        btnRequestPermissions = findViewById<Button>(R.id.btnRequestPermissions)
    }

    private fun hasReadExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasBackgroundLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasCoarseLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasBluetoothPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.BLUETOOTH
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        val permissionsToRequest = mutableListOf<String>()
        if (!hasReadExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!hasBackgroundLocationPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (!hasCoarseLocationPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasBluetoothPermission()) {
            permissionsToRequest.add(Manifest.permission.BLUETOOTH)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
        } else {
            Log.d("permissions", "all necessary permissions has been granted")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("permissions", "${permissions[i]} granted")
                }
            }
        }
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
            val name = etName.text.toString()
            val surname = etSurname.text.toString()
            val birthday = etBirthday.text.toString()
            val country = etCountry.text.toString()
            Intent(this, ActivityB::class.java).also {
                it.putExtra(EXTRA_PERSON_DATA_NAME, Person(name, surname, birthday, country))
                startActivity(it)
            }
        }

        btnShowToast.setOnClickListener {
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                view = layoutInflater.inflate(R.layout.custom_toast, null)
                show()
            }
        }

        btnRequestPermissions.setOnClickListener {
            requestPermission()
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

const val EXTRA_PERSON_DATA_NAME = "EXTRA_PERSON"