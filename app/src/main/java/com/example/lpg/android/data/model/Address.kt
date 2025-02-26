package com.example.lpg.android.data.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class Address(
    var locationName: String,
    var addressCode: String,
    val phoneNumber: String
) {

    override fun toString(): String {
        return "$locationName, $addressCode\n$phoneNumber"
    }

    companion object {
        val default = Address(
            locationName = "",
            addressCode = "",
            phoneNumber = "254",
        )
    }
}
