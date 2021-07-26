package com.example.sample

import android.app.role.RoleManager
import android.content.Intent
import android.os.Build
import android.telecom.Call
import android.telecom.CallScreeningService
import android.widget.Toast
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class phonecallservice: CallScreeningService() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onScreenCall(p0: Call.Details) {
        val detail: String = p0.callerDisplayName
        Toast.makeText(this,"Aravind you got a call",Toast.LENGTH_SHORT).show()
    }
}