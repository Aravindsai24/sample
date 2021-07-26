package com.example.sample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sample.modal.Word

class MainActivity : AppCompatActivity() {
    var TAG = MyService::class.simpleName
    lateinit var etOne: EditText
    lateinit var roomDb: WordRoomDB
    lateinit var wordDao: WordDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etOne = findViewById(R.id.etOne)
        roomDb = WordRoomDB.getDatabase(this)
        wordDao = roomDb.wordDao()
        val CHANNEL_ID = "sample_app_notif"
        createNotificationChannel(CHANNEL_ID)
        val notif_button = findViewById<Button>(R.id.buttonotif)
        notif_button.setOnClickListener {
            var builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("title")
                .setContentText("content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(this)) {
                notify(123, builder.build())
            }
        }
    }

    fun dbHandler(view: View) {
        insertWordAsynchronously()
    }

    private fun insertWordAsynchronously() {
        var data = etOne.text.toString()
        var word = Word(data)
        var insertTask = InsertTask(wordDao,word)
        insertTask.execute()
    }

    override fun onPause() {
        super.onPause()
        var data = etOne.text.toString()
        var sharedPreferences = getSharedPreferences("mtapfile", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        editor.putString("name",data)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        var sharedPreferences = getSharedPreferences("mtapfile", MODE_PRIVATE)
        var data = sharedPreferences.getString("name","")
        etOne.setText(data)

    }

    fun createNotificationChannel(CHANNEL_ID: String) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    fun handleService(view: View) {
        when(view.id) {
            R.id.btn_other_app -> {
                var intent = Intent("mycalculator")
                startActivity(intent)
            }
            R.id.btnStart -> {
                var serviceIntent = Intent(this,MyService::class.java)
                startService(serviceIntent)
            }
            R.id.btnEnd ->{
                var serviceIntent = Intent(this,MyService::class.java)
                stopService(serviceIntent)
            }
            R.id.btnBind -> {
                bindToaService()
            }

        }
    }


    var serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, binderPipe: IBinder?) { //4
            var localBinder = binderPipe as LocalBinder//5
            //var localService = LocalService()
            var localService = localBinder.getLocalService()
            var sum = localService.add(10,20)
            Log.i(TAG,"sum = "+sum)

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }

    }
    private fun bindToaService() {
        var intent = Intent(this,LocalService::class.java)
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE)          //1. Localservice -- onbind

    }

}