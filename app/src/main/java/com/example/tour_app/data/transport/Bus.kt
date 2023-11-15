package com.example.tour_app.data.transport

class Bus(): Transport() {

    override fun calculateCommision(tourPackagePrice : Double): Double {
        println("Su monto se aplica un 2% de comision ")
        return tourPackagePrice * 0.02
    }
}