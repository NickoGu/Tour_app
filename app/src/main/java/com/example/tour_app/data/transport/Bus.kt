package com.example.tour_app.data.transport

class Bus(): Transport() {

    companion object{
        private const val BUS_FEE =  0.02
    }

    override fun calculateCommission(tourPackagePrice : Double): Double {
        println("Su monto se aplica un 2% de comision ")
        return tourPackagePrice * BUS_FEE
    }

    override fun toString(): String {
        return "Bus"
    }
}