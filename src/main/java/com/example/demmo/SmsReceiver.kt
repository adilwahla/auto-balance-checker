package com.example.demmo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import  android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import android.provider.Telephony


class SmsReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            val extras = intent.extras

            if (extras != null) {
                val sms = extras.get("pdus") as Array<Any>
                sms.let {
                    for (i in sms.indices) {
                        val format = extras.getString("format")

                        var smsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            SmsMessage.createFromPdu(sms[i] as ByteArray, format)
                        } else {
                            SmsMessage.createFromPdu(sms[i] as ByteArray)
                        }

                        val phoneNumber = smsMessage.originatingAddress
                        val messageText = smsMessage.messageBody.toString()

                        Toast.makeText(context, "From: $phoneNumber Message: $messageText", Toast.LENGTH_LONG).show()
                        Receiver.message.add("$phoneNumber: $messageText ")
                    }

                }
            }
        }
    }}