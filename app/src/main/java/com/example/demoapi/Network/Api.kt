package com.example.demoapi.Network

import com.example.demoapi.DataModel.Data
import com.example.demoapi.DataModel.ResponseData
import com.example.demoapi.DataModel.Zipcode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("/api/users/zipcodes/")
    suspend fun getHomeData(): Response<ResponseData>

}