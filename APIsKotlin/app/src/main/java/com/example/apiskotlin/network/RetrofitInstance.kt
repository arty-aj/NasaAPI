package com.example.apiskotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.nasa.gov"
    //val API_KEY = "?api_key=mKzaZiqp4dGWuoFwTXTDeXncsRWaDwcXPPja7c31"

    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}