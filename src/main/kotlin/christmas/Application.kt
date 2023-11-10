package christmas
import camp.nextstep.edu.missionutils.Console.readLine
import java.time.DayOfWeek
import java.time.LocalDate
fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    val inputView = InputView()
    val day = inputView.readDate()
    val userMenus = inputView.readMenu()

    println("12월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")

    println("\n<주문 메뉴>")
    userMenus.forEach { (menu, quantity) ->
        println("$menu $quantity 개")
    }


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

    println("\n<할인 전 총주문 금액>")

    var totalCost = 0
    userMenus?.forEach { menu, quantity ->
        val menuCategory = menus.keys.find { menu in menus[it]!! }

        if (menuCategory != null && menus[menuCategory]!!.containsKey(menu)) {
            totalCost += menus[menuCategory]!![menu]!! * quantity.toInt()
        }
    }

    println("${"%,d".format(totalCost)}원")

    val benefits = mutableMapOf(

        "크리스마스 디데이 할인" to 0,
        "평일 할인" to 0,
        "특별 할인" to 0,
        "증정 이벤트" to 0,

        )

    println("\n<증정 메뉴>")

    if (totalCost >= 120000) {
        benefits["증정 이벤트"] = 25000
        println("샴페인 1개")
    }
    else println("없음")

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


    println("\n<총혜택 금액>")

    var totalBenefit = 0

    benefits.forEach { _, value -> totalBenefit+=value }
    println("${"%,d".format(totalBenefit * -1)}원")

    println("\n<할인 후 예상 결제 금액>")
    if (benefits["증정 이벤트"] !=0) println("${"%,d".format(totalCost-totalBenefit + 25000)}원")
    else println("${"%,d".format(totalCost-totalBenefit)}원")

    println("\n<12월 이벤트 배지>")

    if (totalBenefit >=20000) println("산타")
    else if (totalBenefit >= 10000) println("트리")
    else if(totalBenefit >= 5000) println("별")
    else println("없음")








}
