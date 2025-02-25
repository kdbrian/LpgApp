package com.example.lpg.android.util

import kotlinx.serialization.Serializable


@Serializable
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Nothing<T> : Resource<T>(null, null)

}