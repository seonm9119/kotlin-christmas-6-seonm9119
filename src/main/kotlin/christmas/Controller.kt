package christmas

data class UserInputs(val visitDay: Int, val menus: Map<String, Int>, val totalPrice: Int)
data class UserCalculations(val benefits: Map<Discount, Int>, val totalBenefitPrice: Int, val discountedPrice: Int)



class Controller {

    private val outputView = OutputView()
    private val inputView = InputView()
    private fun getTotalPrice(userMenus: Map<String, Int>): Int =
        userMenus.entries.sumOf { (menu, quantity) -> Menus.allMenu[menu]!! * quantity }
    fun getUserInputs(): UserInputs{

        val visitDay = inputView.readDate()
        val userMenu = inputView.readMenu()
        return UserInputs(visitDay, userMenu, getTotalPrice(userMenu))

    }

    fun getUserEventCalculations(userInputs: UserInputs): UserCalculations{

        val benefits = ChristmasDiscountCalculator(userInputs).getBenefits()
        val totalBenefitPrice = benefits.values.sum()
        val discountedPrice = userInputs.totalPrice - totalBenefitPrice

        return UserCalculations(benefits, totalBenefitPrice, discountedPrice)

    }

    fun runEventPlanner() {

        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
        val userInputs = getUserInputs()
        outputView.printMenu(userInputs.menus)
        outputView.printTotalPrice(userInputs.totalPrice)

        println("12월 ${userInputs.visitDay}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        val userCalculations = getUserEventCalculations(userInputs)
        outputView.printBenefits(userCalculations.benefits)
        outputView.printDiscountedTotalPrice(userCalculations)
        outputView.printBadge(userCalculations.totalBenefitPrice)

    }


}

