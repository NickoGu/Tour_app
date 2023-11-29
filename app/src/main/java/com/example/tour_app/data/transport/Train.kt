package com.example.tour_app.data.transport

import java.time.DayOfWeek
import java.time.LocalDate

class Train: Transport() {

    companion object{
        private const val TRAIN_FEE_ON_WEEKEND =  0.03
        private const val TRAIN_FEE_ON_WEEK =  0.0075
    }

    override fun calculateCommission(tourPackagePrice: Double): Double {
        return if (LocalDate.now().dayOfWeek == DayOfWeek.SATURDAY || LocalDate.now().dayOfWeek == DayOfWeek.SUNDAY) {
            tourPackagePrice * TRAIN_FEE_ON_WEEKEND
        } else {
            tourPackagePrice * TRAIN_FEE_ON_WEEK
        }
    }

    override fun toString(): String {
        return "Train"
    }
}