package com.anushka.coroutinesdemo1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _tvcount = MutableLiveData<String>()
    val tvcount : LiveData<String>
        get() = _tvcount

    var start = true


    suspend fun start(){

            for (i in 1..200) {
                Log.i("MainActivityViewModel", "for loop $i")
                if (start) {

                    var count = viewModelScope.async(Dispatchers.IO) {
                        return@async displaytext(i)
                    }
                    _tvcount.value = count.await()

                    Log.i("MainActivityViewModel", "${_tvcount.value}")
                    Log.i("MainActivityViewModel", "${tvcount.toString()}")


            }
        }

    }

    fun stop(){
        start = false
    }

    fun reset(){
        _tvcount.value = ""
    }



private suspend fun displaytext(i : Int) : String {
    // tvCount.text = "Downloading user $i in ${Thread.currentThread().name}"
    Log.i("MainAct", "Downloading user $i in ${Thread.currentThread().name}")
    //delay(0)
    return "Downloading user $i in ${Thread.currentThread().name}"
}
}
