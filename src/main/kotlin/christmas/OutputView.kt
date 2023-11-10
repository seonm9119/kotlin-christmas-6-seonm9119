package christmas

class OutputView(private val userMenus: Map<String, Int>, private val discountCalculator: ChristmasDiscountCalculator) {

    private val totalCost = discountCalculator.getTotalPrice()
    private val benefits = discountCalculator.getBenefits()
    private val totalBenefit = discountCalculator.getTotalBenefit()

    private fun printMenu() {
        println("\n<주문 메뉴>")
        userMenus.forEach { (menu, quantity) -> println("$menu $quantity 개") }
    }

    private fun printTotalPrice() {
        println("\n<할인 전 총주문 금액>")
        println("${"%,d".format(totalCost)}원")
    }

    private fun printFreeItem() {
        println("\n<증정 메뉴>")
        println(if (benefits[Discount.FREE_ITEM] != 0) "샴페인 1개" else "없음")
    }

    private fun printBenefits() {
        println("\n<혜택 내역>")
        if (benefits.values.all { it == 0 }) println("없음")
        else benefits.filterValues { it != 0 }.forEach { (key, value) ->
            println("${key}: ${"%,d".format(value * -1)}원")
        }
    }

    private fun printTotalBenefits(): Int {
        println("\n<총혜택 금액>")
        println("${"%,d".format(totalBenefit * -1)}원")
        return totalBenefit
    }

    private fun printDiscountedTotalPrice() {
        println("\n<할인 후 예상 결제 금액>")
        val discountedTotal = totalCost - totalBenefit
        val freeItemDiscount = if (benefits[Discount.FREE_ITEM] != 0) 25000 else 0
        println("${"%,d".format(discountedTotal + freeItemDiscount)}원")
    }

    private fun printBadge() {
        println("\n<12월 이벤트 배지>")

        when {
            totalBenefit >= Badge.SANTA.price -> println(Badge.SANTA.grade)
            totalBenefit >= Badge.TREE.price -> println(Badge.TREE.grade)
            totalBenefit >= Badge.STAR.price -> println(Badge.STAR.grade)
            else -> println("없음")
        }
    }

    fun showFullPrompt() {
        printMenu()
        printTotalPrice()
        printFreeItem()
        printBenefits()
        printTotalBenefits()
        printDiscountedTotalPrice()
        printBadge()
    }
}
