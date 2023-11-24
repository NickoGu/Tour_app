package com.example.tour_app.data.transport

abstract class Transport {

    abstract fun calculateCommission(tourPackagePrice: Double): Double

}