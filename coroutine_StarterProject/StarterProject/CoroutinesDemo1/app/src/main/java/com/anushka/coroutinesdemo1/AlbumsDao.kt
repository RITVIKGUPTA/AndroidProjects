package com.anushka.coroutinesdemo1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumsDao {


    @Insert
    suspend fun insert_album(album : AlbumsDaCla)

    @Query("Delete from Albums")
    suspend fun clearall()

    @Delete
    suspend fun clear_user(album : AlbumsDaCla)

    @Query("Select Count(*) from Albums")
    suspend fun count() : Int

    @Query("Delete FROM Albums")
    suspend fun deleteall()

    @Query("Select * from Albums where id = :id ")
    suspend fun getalbum_id(id : Int) : AlbumsDaCla

    @Query("Select * from Albums")
    suspend  fun getalbums_all() : List<AlbumsDaCla>
}