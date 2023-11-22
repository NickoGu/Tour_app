package repositories

import com.example.tour_app.data.user.Purchase

object PurchaseRepository {

    private val purchases = mutableListOf<Purchase>()

    init {
        purchases.add(Purchase(1L, 4, 1L, 350.50, "2023/01/01"))
        purchases.add(Purchase(2L, 4, 4L, 100.75, "2023/01/01"))
        purchases.add(Purchase(3L, 4, 7L, 250.50, "2023/01/01"))
        purchases.add(Purchase(4L, 0, 10L, 50.00, "2023/01/01"))
    }

    fun add(purchase: Purchase) {
        purchases.add(purchase)
    }

    fun get() : List<Purchase> {
        return purchases
    }
    
}