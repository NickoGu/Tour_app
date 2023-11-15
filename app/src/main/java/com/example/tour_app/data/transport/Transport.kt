package com.example.tour_app.data.transport

abstract class Transport {

    abstract fun calculateCommision(tourPackagePrice: Double): Double
}