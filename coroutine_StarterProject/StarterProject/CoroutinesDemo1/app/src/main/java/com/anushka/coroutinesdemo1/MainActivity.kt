package com.anushka.coroutinesdemo1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    val id = "channel_id"
    var notificationmanager : NotificationManager? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity2::class.java)
        // Log.i("MainActivity", "inside main activity")

        setContentView(R.layout.activity_main)
        val viewmodel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        tvCount.text = viewmodel.tvcount.toString()

        viewmodel.tvcount.observe(this, Observer { tvcount -> this.tvCount.text = tvcount.toString() })

        stop.setOnClickListener{
            viewmodel.stop()
//            viewmodel.start = true
        }

        button.setOnClickListener {
            Log.i("insidebutton", "inside button")
            textView.text = count++.toString()
        }

        btnCount.setOnClickListener {
//           CoroutineScope(Dispatchers.Main).launch{
//            for (i in 1..2000000){
//           val tvcount = withContext(Dispatchers.IO) {
//                async {
//                     displaytext(i)
//                }
//            }
//
//
//                tvCount.text = tvcount.await()
//            //downloadUserData()
//        }
//        }
            CoroutineScope(Dispatchers.Main).launch {


                viewmodel.start()
            }
        }

        btnDownloadUserData.setOnClickListener{
            Log.i("MainActivity", "clicked button")
            startActivity(intent)

        }

        send_notification.setOnClickListener{
            displaynotification()
        }



      notificationmanager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      val notificationchannel : NotificationChannel
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationchannel =  NotificationChannel(id, "NotificationChannel", NotificationManager.IMPORTANCE_HIGH)
            notificationchannel.description = "notification channel for coroutines app notification"
           notificationmanager?.createNotificationChannel(notificationchannel)
       }
        else{
           displaynotification()
       }

    }
public fun displaynotification(){
    val intent2 = Intent(this, MainActivity3::class.java)
    val pendingintent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    val pendingintent2 = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
    val action : NotificationCompat.Action = NotificationCompat.Action.Builder(android.R.drawable.ic_menu_view, "Get", pendingintent2).build()
    val notificationid = 1
    val notification = NotificationCompat.Builder(this, id).setContentTitle("notification demo").
    setContentText("This is a notification demo").setSmallIcon(android.R.drawable.ic_dialog_info).setAutoCancel(true).
            setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingintent).addAction(action).build()
    notificationmanager?.notify(notificationid, notification)
}


//    private fun downloadUserData() {
//        for (i in 1..400000) {
//            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
//        }
//    }

//    private suspend fun displaytext(i : Int) : String {
////
////
////
////
//////
////               // tvCount.text = "Downloading user $i in ${Thread.currentThread().name}"
////                Log.i("MainAct", "Downloading user $i in ${Thread.currentThread().name}")
////                //delay(0)
////           return "Downloading user $i in ${Thread.currentThread().name}"
////    }


}

