package com.example.tour_app.data.transport

import java.time.DayOfWeek
import java.time.LocalDate

class Train: Transport() {
    override fun calculateCommission(tourPackagePrice: Double): Double {
        return if (LocalDate.now().dayOfWeek == DayOfWeek.SATURDAY || LocalDate.now().dayOfWeek == DayOfWeek.SUNDAY) {
            println("\\u001B[35mSu monto aplica un %3 de comision \\u001B[0m")
            tourPackagePrice * 0.03
        } else {
            println("\\u001B[35mSu monto aplica un %0.75 de comision \\u001B[0m")
            tourPackagePrice * 0.0075
        }
    }

    override fun toString(): String {
        return "Tren"
    }
}