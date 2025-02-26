package com.example.lpg.android.ui.nav

import kotlinx.serialization.Serializable


@Serializable
sealed class Route

@Serializable
data object Home : Route()

//@Serializable
//data class Details(val productId: String) : Route()

@Serializable
data object Cart : Route()

@Serializable
data object CheckOut : Route()

@Serializable
data object CheckOutSuccessScreen : Route()

@Serializable
data object Orders : Route()

@Serializable
data object AddressInfo : Route()