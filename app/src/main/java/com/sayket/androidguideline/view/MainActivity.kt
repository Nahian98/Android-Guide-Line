package com.sayket.androidguideline.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.gson.GsonBuilder
import com.sayket.androidguideline.R
import com.sayket.androidguideline.base.extention.ViewExtensions.loadImage
import com.sayket.androidguideline.model.MyAddress
import com.sayket.androidguideline.utils.CONS_ADDRESS
import com.sayket.androidguideline.utils.CONS_ADDRESS_DATA


class MainActivity : AppCompatActivity() {

    private val TAG = "main"
    private var textViewShow: TextView? = null
    private lateinit var btnNext: Button
    private lateinit var btnConstrain: Button
    private lateinit var ivProfilePhoto: ImageView
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
        textViewShow = findViewById(R.id.tvShow)
        btnNext = findViewById(R.id.btnNext)
        btnConstrain = findViewById(R.id.btnConstrain)
        ivProfilePhoto = findViewById(R.id.ivProfilePhoto)
        address = "Mirpur DHOS"

        setImage()
    }

    private fun setImage() {
        val imageUrl = "https://images.unsplash.com/photo-1531297484001-80022131f5a1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8dGVjaG5vbG9neXxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80"
        ivProfilePhoto.loadImage(imageUrl,R.drawable.ic_user_avatar)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initListener() {
        btnNext.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "mrsaykatm4@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "New Email")
                putExtra(Intent.EXTRA_TEXT, "Hello!")
            }

            if (email.resolveActivity(packageManager) != null) {
                startActivity(email)
            }
        }

        btnConstrain.setOnClickListener {
            val dataStr = GsonBuilder().create().toJson(data).toString()
            startActivity(Intent(this@MainActivity, ActivityConstrainLayout::class.java).apply {
                putExtra(CONS_ADDRESS, address)
                putExtra(CONS_ADDRESS_DATA, dataStr)
            })
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
