package com.example.demoapi.Network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor():Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request=chain.request().newBuilder()
        request.addHeader("token","100f8bcd4626d373cade4e832633b5f7")
        request.addHeader("source","ANDROID")
        return  chain.proceed(request.build())
    }
}