package com.example.lpg.android.data.remote.api

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody


class MockApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(200)
            .request(chain.request())
            .body(MockDataProvider.producedData.toResponseBody("application/json".toMediaTypeOrNull()))
            .protocol(Protocol.HTTP_1_1)
            .addHeader("content-type", "application/json")
            .message(MockDataProvider.producedData)
            .build()
    }
}