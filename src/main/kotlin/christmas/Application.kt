package christmas
fun main() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    val inputView = InputView()
    val day = inputView.readDate()
    val userMenus = inputView.readMenu()

    println("12월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")

    val outputView = OutputView()
    outputView.printMenu(userMenus)


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


    val totalCost = outputView.printTotalPrice(userMenus, menus)

    outputView.printFreeItem(totalCost)
    outputView.printBenefits(day, userMenus, menus)
    val totalBenefit = outputView.printTotalBenefits()
    outputView.printDiscountedTotalPrice(totalCost, totalBenefit)
    outputView.printBadge(totalBenefit)

}
