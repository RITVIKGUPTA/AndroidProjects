package com.anushka.coroutinesdemo1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import android.widget.TextView as TextView

class MainAcitivtyAdapter(val database : Albums?) : RecyclerView.Adapter<MainViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
//        Log.i("oncreate", database.toString())
//        Log.i("oncreate", "$database[0]")
//        Log.i("oncreate", "$database[1]")
       Log.i("MainActivity2", "$database[0] ${database?.size}")
          val layoutinflater = LayoutInflater.from(parent.context)
        Log.i("MainActivity2", "layoutinflater.from executed")
        val item_view =  layoutinflater.inflate(R.layout.item_list, parent, false) as TextView
        Log.i("MainActivity2", "assigned item view as text view ${item_view.text.toString()}")
        return MainViewHolder(item_view)

    }

    override fun getItemCount(): Int {
        return database!!.size
    }

   override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
       val textview =  holder.itemView.textView2
       Log.i("MainActivity2", "assigning to text view from holder  $textview")
        textview.text = database?.get(position)?.id.toString().plus(database?.get(position)?.title.toString().plus(database?.get(position)?.userId.toString()))
       Log.i("MainActivity2", "assigned value to text view . ${textview.text} $position")
}

}

class MainViewHolder (val itemview : TextView) : RecyclerView.ViewHolder(itemview){}
