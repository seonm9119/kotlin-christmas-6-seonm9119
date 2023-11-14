package christmas

class OutputView() {

    fun printMenu(userMenus: Map<String, Int>) {
        UserPrompt.MENU.printMessage()
        userMenus.forEach { (menu, quantity) -> println("$menu $quantity 개") }
    }

    fun printTotalPrice(totalCost: Int) {
        UserPrompt.TOTAL_COST.printMessage()
        println("${"%,d".format(totalCost)}원")
    }


    fun printBenefits(benefits: Map<Discount, Int>) {

        UserPrompt.FREE_ITEM.printMessage()
        println(if (benefits[Discount.FREE_ITEM] != 0) "샴페인 1개" else UserPrompt.NONE)

        UserPrompt.BENEFITS.printMessage()
        if (benefits.values.all { it == 0 }) UserPrompt.NONE.printMessage()
        else benefits.filterValues { it != 0 }.forEach { (key, value) ->
            println("${key}: ${"%,d".format(value * -1)}원")
        }
    }


    fun printDiscountedTotalPrice(totalCost: Int, totalBenefitPrice: Int, benefits: Map<Discount, Int>) {

        UserPrompt.TOTAL_BENEFIT.printMessage()
        println("${"%,d".format(totalBenefitPrice * -1)}원")

        UserPrompt.EXPECTED_PAYMENT.printMessage()
        val discountedTotal = totalCost - totalBenefitPrice
        val freeItemDiscount = if (benefits[Discount.FREE_ITEM] != 0) 25000 else 0
        println("${"%,d".format(discountedTotal + freeItemDiscount)}원")
    }

    fun printBadge(totalBenefitPrice: Int) {
        UserPrompt.BADGE.printMessage()

        when {
            totalBenefitPrice >= Badge.SANTA.price -> println(Badge.SANTA.grade)
            totalBenefitPrice >= Badge.TREE.price -> println(Badge.TREE.grade)
            totalBenefitPrice >= Badge.STAR.price -> println(Badge.STAR.grade)
            else -> UserPrompt.NONE.printMessage()
        }
    }

}
