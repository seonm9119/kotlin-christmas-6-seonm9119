package christmas

import java.time.LocalDate

val appetizer = mapOf(
    "양송이수프" to 6000,
    "타파스" to 5500,
    "시저샐러드" to 8000
)

val mainMenu = mapOf(

    "티본스테이크" to 55000,
    "바비큐립" to 54000,
    "해산물파스타" to 35000,
    "크리스마스파스타" to 25000

)

val dessert = mapOf(

    "초코케이크" to 15000,
    "아이스크림" to 5000

)

val drinks = mapOf(

    "제로콜라" to 3000,
    "레드와인" to 60000,
    "샴페인" to 25000

)

val menus = mapOf(
    "appetizer" to appetizer,
    "mainMenu" to mainMenu,
    "dessert" to dessert,
    "drinks" to drinks)

class ChristmasDiscountCalculator(day: Int, userMenus: Map<String, Int>) {

    val day = day
    val userMenus = userMenus
    val date = LocalDate.of(LocalDate.now().year, 12, day)
    val dayOfWeek = date.dayOfWeek.value
    private var totalPrice = 0


    private var totalBenefit = 0




    val benefits = mutableMapOf(

        "크리스마스 디데이 할인" to 0,
        "평일 할인" to 0,
        "특별 할인" to 0,
        "증정 이벤트" to 0,

        )

    init {
        calculateTotalPrice()
        calculateFreeItem()
        calculateDdayDiscount()
        calculateWeekDiscount()
        calculateWeekendDiscount()
        calculateSpecialDiscount()
        calculateTotalBenefits()
    }

    fun calculateTotalPrice(){

        userMenus.forEach { menu, quantity ->
            val menuCategory = menus.keys.find { menu in menus[it]!! }

            if (menuCategory != null && menus[menuCategory]!!.containsKey(menu)) {
                totalPrice += menus[menuCategory]!![menu]!! * quantity.toInt()
            }
        }

    }

    fun getTotalPrice()=totalPrice

    fun calculateFreeItem(){

        if (totalPrice >= 120000) benefits["증정 이벤트"] = 25000

    }

    fun isFreeItem() = if (benefits["증정 이벤트"]!=0) true else false
    fun calculateDdayDiscount(){

        if (day < 26){
            benefits["크리스마스 디데이 할인"] = (day-1)*100 + 1000
        }

    }

    fun calculateWeekDiscount(){

        val isWeekday = dayOfWeek in 4..7

        var weekDiscount = 0
        if (isWeekday){
            userMenus.forEach { menu, quantity ->
                val menuCategory ="dessert"

                if (menus[menuCategory]!!.containsKey(menu)) {
                    weekDiscount += 2023 * quantity
                }
            }
            benefits["평일 할인"] = weekDiscount
        }
    }

    fun calculateWeekendDiscount(){

        var weekenddiscount = 0
        val isWeekend = dayOfWeek == 5 || dayOfWeek == 6
        if (isWeekend){
            userMenus.forEach { menu, quantity ->
                val menuCategory ="mainMenu"

                if (menus[menuCategory]!!.containsKey(menu)) {
                    weekenddiscount += 2023 * quantity
                }
            }
            benefits["주말 할인"] = weekenddiscount
        }

    }

    fun calculateSpecialDiscount(){

        val starDay = listOf(3,10,17,24,31)

        if (day in starDay) benefits["특별 할인"] = 1000


    }

    fun calculateTotalBenefits() = benefits.forEach { _, value -> totalBenefit+=value }

    fun getTotalBenefit() = totalBenefit

}