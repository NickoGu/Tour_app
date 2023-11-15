package com.example.tour_app.data.transport

class Ferry: Transport() {
    override fun calculateCommision(tourPackagePrice: Double): Double {
        return tourPackagePrice
    }
}