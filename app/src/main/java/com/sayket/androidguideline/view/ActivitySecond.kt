package com.sayket.androidguideline.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.sayket.androidguideline.R
import com.sayket.androidguideline.model.MyAddress
import com.sayket.androidguideline.utils.CONS_ADDRESS
import com.sayket.androidguideline.utils.CONS_ADDRESS_DATA


class ActivitySecond : AppCompatActivity() {
    private val TAG = "Activity_Second"
    private var address = ""
    private var addressAsData: MyAddress? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")
        getExtra()
        initComponent()
    }

    private fun getExtra() {

        intent.getStringExtra(CONS_ADDRESS) ?: ""

        address = intent.getStringExtra(CONS_ADDRESS) ?: ""
        val addressData = intent.getStringExtra(CONS_ADDRESS_DATA) ?: ""

        if (!TextUtils.isEmpty(addressData)) {
            addressAsData = Gson().fromJson(addressData, MyAddress::class.java)
        }
        Log.d(TAG, "getExtra: $address $addressAsData")
    }

    private fun initComponent() {
        if (!TextUtils.isEmpty(address)) {
            findViewById<TextView>(R.id.tvShow).text = address
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
