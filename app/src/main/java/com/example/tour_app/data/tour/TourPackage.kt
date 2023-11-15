package com.example.tour_app.data.tour

import com.example.tour_app.data.transport.Transport

data class TourPackage(
    val id: Long,
    val name: String,
    val transport: Transport,
    val duration: Int,
    val stars: Double,
    val price: Double,
    val logo: String,
    val destination: Destination
)
