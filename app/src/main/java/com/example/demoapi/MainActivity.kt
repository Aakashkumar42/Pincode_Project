package com.example.demoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.demoapi.Ulits.NetworkUtils
import com.example.demoapi.databinding.ActivityMainBinding
import com.example.demoapi.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: ZipCodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         adapter= ZipCodeAdapter()

         binding.recyclerview.adapter=adapter


        if (NetworkUtils.isInternetAvailable(applicationContext)){
            homeViewModel.getHomeData()
            homeViewModel.homelivedata.observe(this, Observer {
                when(it){
                    is NetworkResult.Error -> TODO()
                    is NetworkResult.Loading -> {
                        Log.d("Loading state",it.data.toString())
                    }
                    is NetworkResult.Success -> {
                        adapter.setAdapter(it.data?.data!!.list)
                        Log.d("Response",it.data.toString())
                    }
                }

            })
        }else{
            Toast.makeText(applicationContext,"Internet not connected Offline Mode ",Toast.LENGTH_LONG).show()
            homeViewModel.getLocalData()
            homeViewModel.locallivedata.observe(this, Observer {
                adapter.setAdapter(it)
            })
        }

    }
}