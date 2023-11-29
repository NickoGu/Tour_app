import kotlin.random.Random

class User (
    var createdDate: String,
    var nickName: String = "",
    var password: String = "",
    var name: String = "",
    var surname: String = "",
    var money: Int = Random.nextInt(100000,500000 + 1),
    val id: Long = userCount++
) {
    companion object{
        private var userCount:Long = 0
    }

    fun discountMoney(userMoney: Int, finalPrice: Double) {
        money = userMoney.minus(finalPrice).toInt()
    }

}