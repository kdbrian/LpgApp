package com.example.lpg.android.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.lpg.android.data.model.Address
import kotlinx.coroutines.flow.map

const val dataStoreName = "appSettings"

val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(dataStoreName)

class AppDataStore(private val context: Context) {


    private val _firstTime = booleanPreferencesKey(Key.Location.`val`)
    private val _location = stringPreferencesKey(Key.Location.`val`)
    private val _address = stringPreferencesKey(Key.Address.`val`)
    private val _phoneNumber = stringPreferencesKey(Key.PhoneNumber.`val`)

    suspend fun saveAddress(address: Address){
        context.appDataStore.edit {
            it[_address] = address.addressCode
            it[_location] = address.locationName
            it[_phoneNumber] = address.phoneNumber
        }
    }
    suspend fun clearAddressInfo(){
        context.appDataStore.edit {
            it.remove(_address)
            it.remove(_location)
            it.remove(_phoneNumber)
        }
    }

    suspend fun saveFirstTime(firstTime: Boolean) {
        context.appDataStore.edit {
            it[_firstTime] = firstTime
        }
    }

    val location = context.appDataStore.data.map {
        it[_location] ?: ""
    }
    val address = context.appDataStore.data.map {
        it[_address] ?: ""
    }
    val phoneNumber = context.appDataStore.data.map {
        it[_phoneNumber] ?: ""
    }
    val firstTime = context.appDataStore.data.map {
        it[_firstTime] ?: true
    }

    internal companion object {

        sealed class Key(val `val`: String) {
            data object FirstTime : Key("first_time")
            data object Location : Key("location")
            data object Address : Key("address")
            data object PhoneNumber : Key("phone_number")
        }
    }
}
