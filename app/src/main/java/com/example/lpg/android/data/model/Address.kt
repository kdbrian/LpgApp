package com.example.lpg.android.data.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class Address(
    var locationName: String,
    var addressCode: String,
) {

    override fun toString(): String {
        return "123 $locationName, ($addressCode)"
    }

    companion object {
        val default = Address(
            locationName = LoremIpsum(2).values.joinToString(),
            addressCode = LoremIpsum(6).values.joinToString()
        )
    }
}
