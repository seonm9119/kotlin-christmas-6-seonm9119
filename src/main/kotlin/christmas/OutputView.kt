package christmas

import java.time.LocalDate

class OutputView {

    val benefits = mutableMapOf(

        "크리스마스 디데이 할인" to 0,
        "평일 할인" to 0,
        "특별 할인" to 0,
        "증정 이벤트" to 0,

        )

    fun printMenu(userMenus: Map<String, Int>) {
        println("\n<주문 메뉴>")
        userMenus.forEach { (menu, quantity) ->
            println("$menu $quantity 개")
        }
    }

    fun printTotalPrice(userMenus: Map<String, Int>, menus: Map<String, Map<String, Int>>): Int{
        println("\n<할인 전 총주문 금액>")

        var totalCost = 0
        userMenus?.forEach { menu, quantity ->
            val menuCategory = menus.keys.find { menu in menus[it]!! }

            if (menuCategory != null && menus[menuCategory]!!.containsKey(menu)) {
                totalCost += menus[menuCategory]!![menu]!! * quantity.toInt()
            }
        }

        println("${"%,d".format(totalCost)}원")
        return totalCost
    }

    fun printFreeItem(totalCost: Int){

        println("\n<증정 메뉴>")

        if (totalCost >= 120000) {
            benefits["증정 이벤트"] = 25000
            println("샴페인 1개")
        }
        else println("없음")
    }

    fun printBenefits(day: Int, userMenus: Map<String, Int>, menus: Map<String, Map<String, Int>>){
        println("\n<혜택 내역>")

        // 디데이 할인
        if (day < 26){
            benefits["크리스마스 디데이 할인"] = (day-1)*100 + 1000
        }


        val date = LocalDate.of(LocalDate.now().year, 12, day)
        val dayOfWeek = date.dayOfWeek.value

        // 평일
        val isWeekday = dayOfWeek in 4..7

        var weekDiscount = 0
        if (isWeekday){
            userMenus?.forEach { menu, quantity ->
                val menuCategory ="dessert"

                if (menuCategory != null && menus[menuCategory]!!.containsKey(menu)) {
                    weekDiscount += 2023 * quantity
                }
            }
            benefits["평일 할인"] = weekDiscount
        }


        // 주말 할인
        var weekenddiscount = 0
        val isWeekend = dayOfWeek == 5 || dayOfWeek == 6
        if (isWeekend){
            userMenus?.forEach { menu, quantity ->
                val menuCategory ="mainMenu"

                if (menuCategory != null && menus[menuCategory]!!.containsKey(menu)) {
                    weekenddiscount += 2023 * quantity
                }
            }
            benefits["주말 할인"] = weekenddiscount
        }

        // 특별 할인

        val starDay = listOf(3,10,17,24,31)

        if (day in starDay) benefits["특별 할인"] = 1000



        val hasBenefits = benefits.any { it.value != 0 }

        if (!hasBenefits) println("없음")
        else benefits.forEach { key, value -> println("${key}: ${"%,d".format(value * -1)}원")  }
    }

    fun printTotalBenefits(): Int{
        println("\n<총혜택 금액>")

        var totalBenefit = 0

        benefits.forEach { _, value -> totalBenefit+=value }
        println("${"%,d".format(totalBenefit * -1)}원")
        return totalBenefit
    }

    fun printDiscountedTotalPrice(totalCost: Int, totalBenefit: Int){
        println("\n<할인 후 예상 결제 금액>")
        if (benefits["증정 이벤트"] !=0) println("${"%,d".format(totalCost-totalBenefit + 25000)}원")
        else println("${"%,d".format(totalCost-totalBenefit)}원")
    }

    fun printBadge(totalBenefit: Int){

        println("\n<12월 이벤트 배지>")

        if (totalBenefit >=20000) println("산타")
        else if (totalBenefit >= 10000) println("트리")
        else if(totalBenefit >= 5000) println("별")
        else println("없음")
    }

}