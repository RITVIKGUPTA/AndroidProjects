package com.anushka.coroutinesdemo1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Albums")
data class AlbumsDaCla(@PrimaryKey @ColumnInfo (name = "id") val id : Int,
                       @ColumnInfo (name = "userId") val userId : Int,
                       @ColumnInfo (name = "title") val title : String)







