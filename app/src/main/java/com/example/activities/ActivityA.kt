package com.example.activities

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private lateinit var spDifficultLevels: Spinner
    private lateinit var difficultLevels: List<String>
    private lateinit var btnSavePersonData: Button
    private lateinit var btnLoadPersonData: Button
    private lateinit var btnStartService1: Button
    private lateinit var btnStopService1: Button
    private lateinit var tvServiceStatus1: TextView
    private lateinit var btnStartService2: Button
    private lateinit var btnStopService2: Button
    private lateinit var tvServiceStatus2: TextView
    private lateinit var getServiceData: Button
    private lateinit var etServiceData: EditText
    private lateinit var mService: MyService
    private var mBound: Boolean = false

    private fun initViews() {
        etName = findViewById(R.id.first_name)
        btnNextActivity = findViewById(R.id.bnt_go_to_activityB)
        city = findViewById(R.id.etCity)
        street = findViewById(R.id.etStreet)
        house = findViewById(R.id.etHouse)
        btnApply = findViewById<Button>(R.id.btn_apply)
        btnShowToast = findViewById<Button>(R.id.btnShowToast)
        etSurname = findViewById<EditText>(R.id.etSurname)
        etBirthday = findViewById<EditText>(R.id.editBirthday)
        etCountry = findViewById<EditText>(R.id.etCountry)
        btnRequestPermissions = findViewById<Button>(R.id.btnRequestPermissions)
        spDifficultLevels = findViewById(R.id.spDifficultLevels)
        difficultLevels = resources.getStringArray(R.array.difficultLevels).toList()
        btnSavePersonData = findViewById(R.id.btnSavePersonData)
        btnLoadPersonData = findViewById(R.id.btnLoadPersonData)
        btnStartService1 = findViewById(R.id.btnStartService1)
        btnStopService1 = findViewById(R.id.btnStopService1)
        tvServiceStatus1 = findViewById(R.id.tvServiceStatus1)
        btnStartService2 = findViewById(R.id.btnStartService2)
        btnStopService2 = findViewById(R.id.btnStopService2)
        tvServiceStatus2 = findViewById(R.id.tvServiceStatus2)
        getServiceData = findViewById(R.id.getServiceData)
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyService.MyBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
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

        spDifficultLevels.adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            difficultLevels
        )
        spDifficultLevels.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                btnNextActivity.scaleX = when(position) {
                    0 -> 1F
                    1 -> 0.1F
                    2 -> 0.01F
                    else -> { btnNextActivity.textSize }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        btnSavePersonData.setOnClickListener {
            val name = etName.text.toString()
            val surname = etSurname.text.toString()
            val birthday = etBirthday.text.toString()
            val country = etCountry.text.toString()

            editor.apply {
                putString("name", name)
                putString("surname", surname)
                putString("birthday", birthday)
                putString("country", country)
                apply()
            }
        }
        btnLoadPersonData.setOnClickListener {
            val name = sharedPreferences.getString("name", "")
            val surname = sharedPreferences.getString("surname", "")
            val birthday = sharedPreferences.getString("birthday", "")
            val country = sharedPreferences.getString("country", "India")

            etName.setText(name)
            etSurname.setText(surname)
            etBirthday.setText(birthday)
            etCountry.setText(country)
        }

        btnStartService1.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
            Intent(this, RunningService::class.java).also {
                it.action = RunningService.Action.START.toString()
                startService(it)
                tvServiceStatus1.text = getString(R.string.serviceRunning)
            }
        }
        btnStopService1.setOnClickListener {
            Intent(this, RunningService::class.java).also {
                it.action = RunningService.Action.STOP.toString()
                startService(it)
                tvServiceStatus1.text = getString(R.string.serviceStopped)
            }
        }

        btnStartService2.setOnClickListener {
            Intent(this, MyService::class.java).also {
                bindService(it, connection, Context.BIND_AUTO_CREATE)
            }
        }
        btnStopService2.setOnClickListener {
            if (mBound) (unbindService(connection))
            mBound = false
        }
        getServiceData.setOnClickListener {
            tvServiceStatus2.text = if (mBound) {
                mService.greeting()
            } else {
                "Service not active"
            }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = MenuListener().create(this, menu)
    override fun onOptionsItemSelected(item: MenuItem): Boolean = MenuListener().itemSelected(this, item)

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
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
        val clearPurposePermissionsToRequest = mutableListOf<String>()
        val locationPermissionSToRequest = mutableListOf<String>()
        if (!hasReadExternalStoragePermission()) {
            clearPurposePermissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!hasBackgroundLocationPermission()) {
            locationPermissionSToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (!hasCoarseLocationPermission()) {
            locationPermissionSToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasBluetoothPermission()) {
            clearPurposePermissionsToRequest.add(Manifest.permission.BLUETOOTH)
        }

        if (clearPurposePermissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                clearPurposePermissionsToRequest.toTypedArray(),
                CLEAR_PURPOSE_PERMISSIONS_REQUESTS_CODE
            )
        }
        if (locationPermissionSToRequest.isNotEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.LocationRequestMessageTitle)
                .setMessage(R.string.explicationOfLocationRequest)
                .setIcon(R.drawable.ic_location_request)
                .setPositiveButton("Allow") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        locationPermissionSToRequest.toTypedArray(),
                        LOCATION_PERMISSIONS_REQUESTS_CODE
                    )
                }
                .create()
                .also { it.show() }
        }
        if (clearPurposePermissionsToRequest.isEmpty() && locationPermissionSToRequest.isEmpty()) {
            Log.d("permissions", "all necessary permissions has been granted")
        }
    }
}

const val EXTRA_PERSON_DATA_NAME = "EXTRA_PERSON"
const val CLEAR_PURPOSE_PERMISSIONS_REQUESTS_CODE = 0
const val LOCATION_PERMISSIONS_REQUESTS_CODE = 1