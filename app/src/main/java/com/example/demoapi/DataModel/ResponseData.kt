package com.example.demoapi.DataModel

data class ResponseData(
    val `data`: Data,
    val error: Int,
    val result:List<Zipcode>,
    val msg: String
)