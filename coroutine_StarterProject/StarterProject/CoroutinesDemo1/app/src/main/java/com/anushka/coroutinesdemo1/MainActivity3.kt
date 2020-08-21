package com.anushka.coroutinesdemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    companion object {
        const val KEY_UNTIL_COUNT = "key count value"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        veiwDatabase.setOnClickListener{
         val intent = Intent(this, MainActivity4::class.java)
          startActivity(intent)
        }

        getWorkStatus.setOnClickListener{createworkerrequest()}
    }

    fun createworkerrequest(){
        val data = Data.Builder().putInt(KEY_UNTIL_COUNT, 60000).build()
        val charge_constraint : Constraints = Constraints.Builder().setRequiresCharging(true).build()
        val workmanager = WorkManager.getInstance(applicationContext)
        val workrequest = OneTimeWorkRequest.Builder(practiceworker::class.java).setConstraints(charge_constraint).setInputData(data).build()
        workmanager.enqueue(workrequest)
        workmanager.getWorkInfoByIdLiveData(workrequest.id).observe(this, Observer {
            WorkStatus.text = it.state.name
            if (it.state.isFinished) {
                val message = it.outputData.getString(practiceworker.KEY_MESSAGE)
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}