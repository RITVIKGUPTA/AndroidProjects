package com.practice.app.android3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.practice.app.android3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.done.setOnClickListener{
            add_name(it)
    }


    }

    private fun add_name(view:View){

        binding.apply{
            txt.text = editTextTextPersonName.text.toString().plus(" ").plus(this.editTextTextPersonName2.text.toString().plus("this is the new version"))
            editTextTextPersonName.visibility = View.GONE
            editTextTextPersonName2.visibility = View.GONE
            view.visibility = View.GONE
        }

    }
}