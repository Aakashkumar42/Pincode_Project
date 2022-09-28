package com.example.demoapi

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoapi.DataModel.Data
import com.example.demoapi.DataModel.ResponseData
import com.example.demoapi.DataModel.Zipcode
import com.example.demoapi.Network.Api
import com.example.demoapi.Ulits.NetworkUtils
import com.example.demoapi.db.ZipCodeDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: Api,
    private val database: ZipCodeDatabase
) {

    private var _homeLiveData=MutableLiveData<NetworkResult<ResponseData>>()
    val homeLiveData:LiveData<NetworkResult<ResponseData>>
    get() = _homeLiveData

    private var _localLiveData=MutableLiveData<List<Zipcode>>()
    val localLiveData:LiveData<List<Zipcode>>
    get() = _localLiveData

    suspend fun getHomeData() {

            _homeLiveData.postValue(NetworkResult.Loading())
            val response = api.getHomeData()

            if (!(!response.isSuccessful || response.body() == null)) {
                _homeLiveData.postValue(NetworkResult.Success(response.body()!!))
                database.zipcodeDao().insertZipCode(response.body()!!.data.list)
            } else if (response.body() != null) {
                val errObject = JSONObject(response.errorBody()!!.charStream().readText())
                _homeLiveData.postValue(NetworkResult.Error(errObject.getString("message")))
            } else {
                _homeLiveData.postValue(NetworkResult.Error("Something went wrong"))
            }
        }

    suspend fun getZipcodeData(){
        val zipcode=database.zipcodeDao().getzipcode()
        _localLiveData.postValue(zipcode)
         }
    }

