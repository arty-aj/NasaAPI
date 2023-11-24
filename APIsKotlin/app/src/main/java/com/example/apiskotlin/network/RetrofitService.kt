package com.example.apiskotlin.network

//import com.example.apiskotlin.model.Response
import com.example.apiskotlin.model.ApodResponse
import retrofit2.http.GET
import retrofit2.Call


//Functions
interface RetrofitService {
    //API endpoint we are getting
    @GET("/planetary/apod?api_key=mKzaZiqp4dGWuoFwTXTDeXncsRWaDwcXPPja7c31")
    fun getData(): Call<ApodResponse>
}