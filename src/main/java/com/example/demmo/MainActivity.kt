package com.example.demmo

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import  android.net.Uri
import kotlinx.android.synthetic.main.activity_main.*
import  android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val requestReceiveSms = 2
    val balance = "*133%23"
    val REQUEST_PHONE_CALL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtSMS.text = "phone number : sms goes here!!!"
        var message: SmsReceiver? = null
        refresh.setOnClickListener {
            var a = ""
            for (i: String in Receiver.message) {
                a += i + "\n"
            }
            txtSMS.text = a
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), requestReceiveSms)
            }


        }
        btnBalance.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
            } else{
                balance()
            }
        }
    }
    @SuppressLint("MissingPermission")
    private fun balance() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = (Uri.parse("tel:" + balance))
        startActivity(callIntent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == REQUEST_PHONE_CALL)balance
    }

    }



