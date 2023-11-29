package com.example.tour_app.data.transport

class Ferry: Transport() {
    override fun calculateCommission(tourPackagePrice: Double): Double {
        return tourPackagePrice
    }

    override fun toString(): String {
        return "Ferry"
    }
}