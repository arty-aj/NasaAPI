package com.example.apiskotlin.network

import com.example.apiskotlin.model.ApodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceDate {
    //API endpoint we are getting
    @GET("/planetary/apod?api_key=mKzaZiqp4dGWuoFwTXTDeXncsRWaDwcXPPja7c31")
    fun getData(@Query("date") date: String): Call<ApodResponse>
}