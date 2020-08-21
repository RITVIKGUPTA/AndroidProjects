package com.practice.app.android4_navigate

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.practice.app.android4_navigate.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @Bindable
    val count_text = MutableLiveData<String>()


    var count : Int? = count_text.value?.toInt()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        val bind = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        bind.activity = this
            Log.i("MainActivity", "before change ${count_text.value} $count")

        var sharedpref = getSharedPreferences("com.android.app.MainActivity.SHARED_PREFERRENCES", Activity.MODE_PRIVATE)
            val highnumber = sharedpref.getInt("HighestNumber", 0)
            bind.HighScore.text = highnumber.toString()
        count_text.observe(this, Observer { it ->
            if(it == ""){
                count = null
            }
            else{
                count = it.toInt()
                if (it.toInt() > sharedpref.getInt("HighestNumber", highnumber)){with(sharedpref.edit()){
                    putInt("HighestNumber", it.toInt())
                    bind.HighScore.text = it
                    commit()
                }

                }
            }

        })
            Log.i("MainActivity", "after ${count_text.value} $count")
        //var bind : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //bind.ActivityNavigate.setOnClickListener {startActivity()}
        Log.i("MainActivity", "inside oncreate")

        //val cont = bottomNavigation.context

        //Log.i("MainActivity", "${cont.toString()} $cont")

    }

    override fun onStart(){
        super.onStart()
        Log.i("MainActivity", "inside Onstart")
    }
}