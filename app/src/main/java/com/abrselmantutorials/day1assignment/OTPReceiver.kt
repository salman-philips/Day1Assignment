package com.abrselmantutorials.day1assignment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.EditText

class OTPReceiver:BroadcastReceiver() {
    private var editText: EditText? = null
    fun setEditText(editText: EditText) {
        this.editText = editText
    }
    override fun onReceive(context: Context?, intent: Intent?) {
       val smsMessages: Array<out SmsMessage>? = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        if (smsMessages != null) {
            for (sms in smsMessages) {
                val msg = sms.messageBody
                val otp = msg.split(":".toRegex())[1]
                editText!!.setText(otp)
            }
        }
    }
}