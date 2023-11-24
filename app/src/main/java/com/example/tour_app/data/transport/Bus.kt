package com.example.tour_app.data.transport

class Bus(): Transport() {

    override fun calculateCommission(tourPackagePrice : Double): Double {
        println("Su monto se aplica un 2% de comision ")
        return tourPackagePrice * 0.02
    }

    override fun toString(): String {
        return "Bus"
    }
}