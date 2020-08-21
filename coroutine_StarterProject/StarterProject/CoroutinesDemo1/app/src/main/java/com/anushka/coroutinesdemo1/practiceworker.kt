package com.anushka.coroutinesdemo1

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class practiceworker(context : Context, params : WorkerParameters) : Worker(context, params) {
    companion object{
        const val KEY_MESSAGE = "key output message"
    }
    override fun doWork(): Result {
        val data = inputData.getInt(MainActivity3.KEY_UNTIL_COUNT, 0)
        try{
            for (i in 1 until data){
                Log.i("Worker", "${Thread.currentThread()} $i")
            }
            val outdata = Data.Builder().putString(KEY_MESSAGE, "The computation is done").build()

            return Result.success(outdata)
        }
        catch(e : Exception){
            Log.i("WorkerFail", "Failure in ${Thread.currentThread()}")
            return Result.failure()
        }
    }
}