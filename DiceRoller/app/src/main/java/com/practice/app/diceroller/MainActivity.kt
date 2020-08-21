package com.practice.app.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rollbutton: Button =  findViewById<Button>(R.id.roll_id)
        rollbutton.setOnClickListener(){ rolldice() }
    }

    private fun rolldice(){
       // Toast.makeText(this,"button clicked", Toast.LENGTH_SHORT).show()
        var text_display : TextView = findViewById(R.id.text_id)
        text_display.text = "Dice Rolled"
        text_display.text = "${(1..6).random()}"
    }
}