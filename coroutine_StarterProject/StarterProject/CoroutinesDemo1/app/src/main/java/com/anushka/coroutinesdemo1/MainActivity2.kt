package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {
 lateinit var dao : AlbumsDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


//    val data = ArrayList<Int>()
//    for (i in 1..100){
//        data.add(i)
//    }
//    Log.i("MainActivity2", "${data[1]}  ${data.size}")
//
//

        Log.i("MainActivity2", "2nd activity created")
        dao = AlbumsDatabase.getInstance(application).albumsdao

        val BASE_URL = "https://jsonplaceholder.typicode.com"
        val interceptor = HttpLoggingInterceptor().apply{
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retro =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client)
                .build()
        val albumservice = retro.create(AlbumService::class.java)
        Log.i("MainActivity2", "before assigning dao")






        var recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
//        Log.i("MainActivity2", "assigned recycler view")
        recyclerview.layoutManager = LinearLayoutManager(this)
//        Log.i("MainActivity2", "created layout manager")
        CoroutineScope(Dispatchers.Main).launch {
//            val res = async(IO) {            alternate way of calling async within the main coroutine block
//                getalbum(albumservice)
//            }
            //           val response = res.await()
            Log.i("MainActivity2", "before call to retrofit api")
             val response = getalbum(albumservice)
            Log.i("MainActivity2", "after call to retrofit api")

            recyclerview.adapter = MainAcitivtyAdapter(response.body())
//        Log.i("MainActivity2", "created adapter")
        }


    }

    suspend fun getalbum(albumservice: AlbumService): Response<Albums> {
        Log.i("MainActivity2", "entered get album")

        val response = CoroutineScope(Dispatchers.IO).async {

            Log.i("MainActivity2", "entered Io thread")

            Log.i("MainActivity2", "declared dao")
            val response = albumservice.getalbumsuserid(3)
            Log.i("MainActivity2", "before inserting values into database")
            Log.i("MainActivity2dao", "Count = ${dao.count()}")
            dao.deleteall()
            Log.i("MainActivity2dao", "Count = ${dao.count()}")
            for (i in response.body()!!){
                var row = AlbumsDaCla(i.id, i.userId, i.title)
                dao.insert_album(row)
            }
            Log.i("MainActivity2dao", "Count = ${dao.count()}")
            Log.i("MainActivity2", "inserted values into database")
            val res_userid = albumservice.getalbumsuserid(3)
            val res_albumid = albumservice.getalbumsalbumid(3)

            Log.i("MainActivity2", "${res_userid.body()?.size} ${res_userid.body()}  ${res_userid.body()!!::class.java}")
            Log.i("MainActivity2",  "${res_albumid.body()}  ${res_albumid.body()!!::class.java}")
            Log.i("MainActivity2", "${response.body()?.size} ${response.body()} ${response.body()!!::class.java}")



            return@async response
        }

        return response.await()
    }

}

