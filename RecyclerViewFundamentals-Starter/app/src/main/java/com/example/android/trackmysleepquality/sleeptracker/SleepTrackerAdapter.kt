package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight



class SleepTrackerAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<SleepNight>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(textItemViewHolder: TextItemViewHolder, position : Int){
         val item = data[position]
        textItemViewHolder.textview.text = item.sleepQuality.toString()
        if (item.sleepQuality <= 1) {
            textItemViewHolder.textview.setTextColor(Color.RED) // red
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }


}




