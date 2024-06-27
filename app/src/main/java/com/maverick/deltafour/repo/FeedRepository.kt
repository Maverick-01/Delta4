package com.maverick.deltafour.repo

import com.maverick.deltafour.repo.retrofit.Retrofit
import com.maverick.deltafour.utlis.URLs

class FeedRepository {

    private val api = Retrofit().retrofitService
    //fetching api through retrofit.
    suspend fun fetchShinobuImage() = api.fetchShinobuImage(URLs.GET_SHINOBU_IMAGE)
    suspend fun fetchNekoImage() = api.fetchNekoImage(URLs.GET_NEKO_IMAGE)
    suspend fun fetchWaifuImage() = api.fetchWaifuImage(URLs.GET_WAIFU_IMAGE)
}