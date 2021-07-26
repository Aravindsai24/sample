package com.example.sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class smsReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0,"Aravind you got an sms",Toast.LENGTH_SHORT).show()
    }

}