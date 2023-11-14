package christmas

class OutputView(private val userMenus: Map<String, Int>, private val discountCalculator: ChristmasDiscountCalculator) {

    private val totalCost = discountCalculator.getTotalPrice()
    private val benefits = discountCalculator.getBenefits()
    private val totalBenefit = discountCalculator.getTotalBenefit()

    private fun printMenu() {
        UserPrompt.MENU.printMessage()
        userMenus.forEach { (menu, quantity) -> println("$menu $quantity 개") }
    }

    private fun printTotalPrice() {
        UserPrompt.TOTAL_COST.printMessage()
        println("${"%,d".format(totalCost)}원")
    }

    private fun printFreeItem() {
        UserPrompt.FREE_ITEM.printMessage()
        println(if (benefits[Discount.FREE_ITEM] != 0) "샴페인 1개" else UserPrompt.NONE)
    }

    private fun printBenefits() {
        UserPrompt.BENEFITS.printMessage()
        if (benefits.values.all { it == 0 }) println("없음")
        else benefits.filterValues { it != 0 }.forEach { (key, value) ->
            println("${key}: ${"%,d".format(value * -1)}원")
        }
    }

    private fun printTotalBenefits(): Int {
        UserPrompt.TOTAL_BENEFIT.printMessage()
        println("${"%,d".format(totalBenefit * -1)}원")
        return totalBenefit
    }

    private fun printDiscountedTotalPrice() {
        UserPrompt.EXPECTED_PAYMENT.printMessage()
        val discountedTotal = totalCost - totalBenefit
        val freeItemDiscount = if (benefits[Discount.FREE_ITEM] != 0) 25000 else 0
        println("${"%,d".format(discountedTotal + freeItemDiscount)}원")
    }

    private fun printBadge() {
        UserPrompt.BADGE.printMessage()

        when {
            totalBenefit >= Badge.SANTA.price -> println(Badge.SANTA.grade)
            totalBenefit >= Badge.TREE.price -> println(Badge.TREE.grade)
            totalBenefit >= Badge.STAR.price -> println(Badge.STAR.grade)
            else -> UserPrompt.NONE.printMessage()
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
