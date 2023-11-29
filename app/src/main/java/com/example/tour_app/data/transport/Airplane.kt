package com.example.tour_app.data.transport

import java.time.LocalTime

class Airplane: Transport() {

    companion object{
        private const val AIRPLANE_FEE_AFTER_3PM_BEFORE_10PM =  0.01
        private const val Airplane_FEE_BEFORE3PM_AFTER_10PM=  0.03
        private val TIME_BEFORE_3PM = LocalTime.parse("14:59")
        private val ACTUAL_TIME = LocalTime.now()
        private val TIME_AFTER_10_30PM = LocalTime.parse("22:31")
    }

    override fun calculateCommission(tourPackagePrice: Double): Double {
        return if (ACTUAL_TIME.isAfter(TIME_BEFORE_3PM) && ACTUAL_TIME.isBefore(TIME_AFTER_10_30PM)) {
            tourPackagePrice * AIRPLANE_FEE_AFTER_3PM_BEFORE_10PM
        } else {
            tourPackagePrice * Airplane_FEE_BEFORE3PM_AFTER_10PM
        }
    }

    override fun toString(): String {
        return "Airplane"
    }
}