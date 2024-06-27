package com.maverick.deltafour.repo.retrofit

import com.maverick.deltafour.data.ImageURL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WebService {
    //end points to fetch different types of images. url is passed within function parameter and it is annotated by @Url
    @GET
    suspend fun fetchWaifuImage(
        @Url url: String
    ): Response<ImageURL>

    @GET
    suspend fun fetchNekoImage(
        @Url url: String
    ): Response<ImageURL>

    @GET
    suspend fun fetchShinobuImage(
        @Url url: String
    ): Response<ImageURL>
}