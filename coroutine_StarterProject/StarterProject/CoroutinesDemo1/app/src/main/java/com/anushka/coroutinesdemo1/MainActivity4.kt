package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.anushka.coroutinesdemo1.MainActivity2 as mn2

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val dao = AlbumsDatabase.getInstance(applicationContext).albumsdao
       CoroutineScope(Dispatchers.Main).launch {
           var all_results = CoroutineScope(Dispatchers.IO).async {
               val all_results = dao.getalbums_all()
               return@async all_results
           }.await()

           val i = all_results[1]

           albumsdata.text = "returned from IO thread ${all_results.size}  ${all_results[0]} ${all_results[9]} ${i.id.toString().plus(i.userId.toString()).plus(i.title.toString())}"
           var result: String = ""
           if (all_results.isNotEmpty()) {
               for (i in all_results) {
                   Log.i("MainActivity4", "${i.id} ${i.userId} ${i.title}")
                  result = result.plus(i.id.toString()).plus(i.userId.toString()).plus(i.title.toString())
               }
               albumsdata.text = result
           }
           else{
               albumsdata.text = "Database is empty"
           }
       }
    }
}