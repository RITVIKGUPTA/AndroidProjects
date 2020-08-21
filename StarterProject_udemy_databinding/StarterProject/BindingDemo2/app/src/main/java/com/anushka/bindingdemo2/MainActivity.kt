package com.anushka.bindingdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.anushka.bindingdemo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_main)


        button = binding.controlButton
        button.setOnClickListener {
            startOrStopProgressBar()
        }
    }

    private fun startOrStopProgressBar() {

        binding.apply{
            if(progressBar.visibility == View.GONE){
                progressBar.visibility = View.VISIBLE
            button.text = "Stop"
            }
            else{
                progressBar.visibility = View.GONE
                button.text = "Start"
            }
        }
    }
}

