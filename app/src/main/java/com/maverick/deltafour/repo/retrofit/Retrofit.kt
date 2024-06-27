package com.maverick.deltafour.repo.retrofit
import com.maverick.deltafour.utlis.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    val retrofitService: WebService = retrofit.create(WebService::class.java)
}