package com.anushka.coroutinesdemo1

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumsDaCla::class] , version = 1, exportSchema = false)
abstract class AlbumsDatabase : RoomDatabase() {

    abstract val albumsdao : AlbumsDao

    companion object{
        @Volatile
        var INSTANCE : AlbumsDatabase? = null
        fun getInstance(context: Context) : AlbumsDatabase{
            synchronized(this){
                var instance = INSTANCE


                if (instance == null){
                   instance =  Room.databaseBuilder(context.applicationContext,
                       AlbumsDatabase::class.java,
                       "albums_database").build()
                    INSTANCE = instance
                }
                return instance

            }

        }
    }



}