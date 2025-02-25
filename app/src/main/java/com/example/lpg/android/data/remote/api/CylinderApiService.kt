package com.example.lpg.android.data.remote.api

import com.example.lpg.android.data.model.GasItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CylinderApiService {

    @GET("cylinders")
    suspend fun getCylinders(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<GasItem>

}