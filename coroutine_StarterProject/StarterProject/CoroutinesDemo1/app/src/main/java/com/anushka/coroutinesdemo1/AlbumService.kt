package com.anushka.coroutinesdemo1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    public suspend fun getalbums() : Response<Albums>

    @GET("/albums")
    public suspend fun getalbumsuserid(@Query("userId")userId : Int) : Response<Albums>

    @GET("/albums/{id}")
    public suspend fun getalbumsalbumid(@Path("id")albumId : Int) : Response<AlbumsItem>
}