package com.sayket.androidguideline.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.gson.GsonBuilder
import com.sayket.androidguideline.R
import com.sayket.androidguideline.model.MyAddress
import com.sayket.androidguideline.services.RunningService
import com.sayket.androidguideline.utils.CONS_ADDRESS
import com.sayket.androidguideline.utils.CONS_ADDRESS_DATA


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SIGN_IN = 100

    // val driveServiceHelper: DriveS
    private val TAG = "lifeCyActivity_"
    private var textViewShow: TextView? = null
    private lateinit var buttonStart: Button
    private lateinit var buttonStop: Button
    private var address: String = ""
    private var data: MyAddress? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity" + ": onCreate")

        initComponent()
        initService()
        initListener()
        initObserver()

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initService() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
            0
        )
    }

    private fun initComponent() {
        textViewShow = findViewById<TextView>(R.id.tvShow)
        buttonStart = findViewById<Button>(R.id.btnStart)
        buttonStop = findViewById<Button>(R.id.btnStop)
        address = "Mirpur DHOS"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initListener() {
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            val dataStr = GsonBuilder().create().toJson(data).toString()
            startActivity(Intent(this@MainActivity, ActivitySecond::class.java).apply {
                putExtra(CONS_ADDRESS, address)
                putExtra(CONS_ADDRESS_DATA, dataStr)
            })

        }



        textViewShow?.setOnClickListener {
//            Log.d(TAG,"onClick"+ "${GsonBuilder().create().toJson(data)}")
//
//            callBackClick?.invoke(address)
//            startActivity(Intent(this@MainActivity, ActivitySecond::class.java).apply {
//                putExtra(CONS_ADDRESS, "${GsonBuilder().create().toJson(data)}")
//            })

//            val email = Intent(Intent.ACTION_SEND)
//            email.putExtra(Intent.EXTRA_EMAIL, "mrsaykatm4@gmail.com")
//            email.putExtra(Intent.EXTRA_SUBJECT, "Hi")
//            email.putExtra(Intent.EXTRA_TEXT, message)
//
//            startActivity(Intent.createChooser(email, "Choose an Email client:"))

            val email = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "kanon@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "New Email")
                putExtra(Intent.EXTRA_TEXT, "Hello!")
            }

            if (email.resolveActivity(packageManager) != null) {
                startActivity(email)
            }
        }

        // Foreground service active button
        buttonStart.setOnClickListener {
            Intent(applicationContext, RunningService::class.java).also {
                it.action = RunningService.Actions.START.toString()
                startService(it)
            }
        }

        // Foreground service destroy button
        buttonStop.setOnClickListener {
            Intent(applicationContext, RunningService::class.java).also {
                it.action = RunningService.Actions.STOP.toString()
                startService(it)
            }
        }
    }

    private fun initObserver() {
        data = MyAddress(roadNo = null, area = "Mirpur DHOS")
        Log.i(TAG, "$data")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MainActivity" + ": onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity: onResume $data")
        data?.roadNo = 9
        Log.i(TAG, "$data")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MainActivity" + ": onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MainActivity" + ": onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "MainActivity" + ": onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity" + ": onDestroy")
    }
}
