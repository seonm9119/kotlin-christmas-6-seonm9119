package christmas
fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    val inputView = InputView()
    val day = inputView.readDate()
    val userMenus = inputView.readMenu()

    println("12월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")

    val outputView = OutputView()
    outputView.printMenu(userMenus)

    val discountCalculator = ChristmasDiscountCalculator(day, userMenus)

    val totalCost = discountCalculator.getTotalPrice()
    outputView.printTotalPrice(totalCost)

    val isFree = discountCalculator.isFreeItem()
    outputView.printFreeItem(isFree)

    val benefits = discountCalculator.benefits
    outputView.printBenefits(benefits)


    val totalBenefit = discountCalculator.getTotalBenefit()
    outputView.printTotalBenefits(totalBenefit)

    outputView.printDiscountedTotalPrice(benefits, totalCost, totalBenefit)
    outputView.printBadge(totalBenefit)

}
