package com.example.tour_app.data.user

data class Purchase(
    val id: Long,
    val userId: Long,
    val packageId: Long,
    val amount: Double,
    val createdDate: String,
){
    override fun toString(): String {
        return super.toString()
    }
}
