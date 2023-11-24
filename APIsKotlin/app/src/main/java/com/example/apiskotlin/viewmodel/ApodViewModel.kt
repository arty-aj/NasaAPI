package com.example.apiskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apiskotlin.model.ApodResponse
import com.example.apiskotlin.network.RetrofitInstance
import com.example.apiskotlin.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Act as link of model and view, transforms data from model, provides data to the view,
// uses callbacks to update the view, ask data from model
class ApodViewModel: ViewModel() {
    //live data
    var ApodResponseDataList = MutableLiveData<ApodResponse>()

    fun getApiData(){
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        retrofitService.getData().enqueue(object : Callback<ApodResponse>{
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