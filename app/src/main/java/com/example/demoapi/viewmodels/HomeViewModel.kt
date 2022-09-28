package com.example.demoapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapi.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository):ViewModel(){

    val homelivedata get() = repository.homeLiveData
    val locallivedata get() = repository.localLiveData

        fun getHomeData(){
            viewModelScope.launch {
                repository.getHomeData()
            }
        }

    fun getLocalData(){
        viewModelScope.launch {
            repository.getZipcodeData()
        }
    }
}