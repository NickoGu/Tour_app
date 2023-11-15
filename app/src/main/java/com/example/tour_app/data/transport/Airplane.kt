package com.example.tour_app.data.transport

import java.time.LocalTime

class Airplane: Transport() {

    override fun calculateCommision(tourPackagePrice: Double): Double {
        val las3 = LocalTime.parse("14:59")
        val horaActual = LocalTime.now()
        val las10yMedia = LocalTime.parse("22:31")

        return if (horaActual.isAfter(las3) && horaActual.isBefore(las10yMedia)) {
            print("Su monto se aplica un 1% de comision ")
            tourPackagePrice * 0.01
        } else {
            print("Su monto se aplica un 3% de comision ")
            tourPackagePrice * 0.03
        }
    }
}