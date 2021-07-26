package com.example.sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class phoneCallReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val state: String? = p1?.getStringExtra(TelephonyManager.EXTRA_STATE)
        if(state == TelephonyManager.EXTRA_STATE_RINGING) {
            Toast.makeText(p0,"Aravind you got a call", Toast.LENGTH_SHORT).show()
        }

    }
}