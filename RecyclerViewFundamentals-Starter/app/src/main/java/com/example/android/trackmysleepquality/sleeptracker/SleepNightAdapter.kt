package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.android.synthetic.main.item_list_sleep_night.view.*
import com.example.android.trackmysleepquality.convertDurationToFormatted


class SleepNightAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    var data = listOf<SleepNight>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val layoutinflater = LayoutInflater.from(parent.context)
        val itemview = layoutinflater.inflate(R.layout.item_list_sleep_night, parent, false)
        return ItemViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.quality.text= convertNumericQualityToString(item.sleepQuality, res)
        holder.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}

class ItemViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
    val sleepLength = itemview.findViewById<TextView>(R.id.sleep_length)
    val quality: TextView = itemview.findViewById(R.id.quality_string)
    val qualityImage: ImageView = itemview.findViewById(R.id.quality_image)

}