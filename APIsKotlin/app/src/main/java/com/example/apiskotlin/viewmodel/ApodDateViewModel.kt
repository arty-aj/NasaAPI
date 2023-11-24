package com.example.apiskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apiskotlin.model.ApodResponse
import com.example.apiskotlin.network.RetrofitInstance
import com.example.apiskotlin.network.RetrofitService
import com.example.apiskotlin.network.RetrofitServiceDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApodDateViewModel : ViewModel() {
    //live data
    var ApodResponseDataList = MutableLiveData<ApodResponse>()

    fun getApiData(date: String){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitServiceDate::class.java)
        retrofitService.getData(date).enqueue(object : Callback<ApodResponse> {
            override fun onResponse(call: Call<ApodResponse>, response: Response<ApodResponse>) {
                //Success
                ApodResponseDataList.value = response.body()
            }

            override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                //Failure
                println("API CALL Failed on ${t.message}")
                t.printStackTrace()
            }

        })
    }
}