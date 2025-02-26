package com.example.lpg.android.data.local.util

import androidx.room.TypeConverter
import com.example.lpg.android.data.model.Address
import com.example.lpg.android.data.model.CartObject
import com.google.gson.Gson

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromCartObjects(cartObjects: List<CartObject>): String {
        return gson.toJson(cartObjects)
    }

    @TypeConverter
    fun toCartObjects(cartObjectsJson: String): List<CartObject> {
        return gson.fromJson(cartObjectsJson, Array<CartObject>::class.java).toList()
    }


    @TypeConverter
    fun fromAddress(address: Address): String {
        return gson.toJson(address)
    }

    @TypeConverter
    fun toAddress(addressJson: String): Address {
        return gson.fromJson(addressJson, Address::class.java)
    }

}