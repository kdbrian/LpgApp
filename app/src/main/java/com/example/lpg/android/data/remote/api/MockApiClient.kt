package com.example.lpg.android.data.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockApiClient {

    private val mockApiInterceptor = MockApiInterceptor()

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mockApiInterceptor)
        .build()

    private val retrofit = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(MockDataProvider.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cylinderApiService: CylinderApiService by lazy { retrofit.create(CylinderApiService::class.java) }

}