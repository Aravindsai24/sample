package com.example.sample

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class CproviderActivity : AppCompatActivity() {
    lateinit var lv_cprovider: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cprovider)
        lv_cprovider = findViewById(R.id.lv_cprovider)
        val uriSms: Uri = Uri.parse("content://sms/inbox")
        var fromColumn = arrayOf("body","address")
        var toTextView = intArrayOf(android.R.id.text1,android.R.id.text2)
        var rowLayout = android.R.layout.simple_list_item_2  //C:\Users\Admin\AppData\Local\Android\Sdk\platforms\android-28\data\res\layout
        var dataCursor =
            contentResolver.query(uriSms,null,null,null,null,null)

        var adapter: SimpleCursorAdapter =
            SimpleCursorAdapter(this,rowLayout,dataCursor,fromColumn,toTextView,1)
        lv_cprovider.adapter =adapter
    }

}