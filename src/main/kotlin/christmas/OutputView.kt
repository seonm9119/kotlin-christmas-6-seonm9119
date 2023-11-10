package christmas

import java.time.LocalDate

class OutputView() {

    fun printMenu(userMenus: Map<String, Int>) {
        println("\n<주문 메뉴>")
        userMenus.forEach { (menu, quantity) ->
            println("$menu $quantity 개")
        }
    }

    fun printTotalPrice(totalCost: Int){

        println("\n<할인 전 총주문 금액>")
        println("${"%,d".format(totalCost)}원")
    }

    fun printFreeItem(isFree: Boolean){

        println("\n<증정 메뉴>")
        if (isFree) println("샴페인 1개")
        else println("없음")
    }

    fun printBenefits(benefits: Map<String, Int>){
        println("\n<혜택 내역>")


        val hasBenefits = benefits.any { it.value != 0 }

        if (!hasBenefits) println("없음")
        else benefits.forEach { key, value -> println("${key}: ${"%,d".format(value * -1)}원")  }

    }

    fun printTotalBenefits(totalBenefit: Int): Int{
        println("\n<총혜택 금액>")
        println("${"%,d".format(totalBenefit * -1)}원")
        return totalBenefit
    }

    fun printDiscountedTotalPrice(benefits: Map<String, Int>, totalCost: Int, totalBenefit: Int){
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
