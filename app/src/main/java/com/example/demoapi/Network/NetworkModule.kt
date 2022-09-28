package com.example.demoapi.Network

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

   @Singleton
   @Provides
   fun providesRetrofitBuilder(): Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl("http://edflow.cladev.com")
            .addConverterFactory(GsonConverterFactory.create())
   }

    @Singleton
    @Provides
    fun okhttpInterceptor(authInterceptor: AuthInterceptor):OkHttpClient{
            return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideFoodApi(builder:Retrofit.Builder,okHttpClient: OkHttpClient):Api{
        return  builder.client(okHttpClient).build().create(Api::class.java)
     }

}