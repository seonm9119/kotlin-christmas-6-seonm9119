package christmas


class Controller {

    private var visitDay: Int = 0
    private var userMenu: Map<String, Int> = emptyMap()
    private var totalCost: Int = 0
    private var totalBenefitPrice: Int = 0
    private var benefits: Map<Discount, Int> = emptyMap()


    private val inputView: InputView
    private val outputView: OutputView
    private val discountCalculator: ChristmasDiscountCalculator

    init {

        inputView = InputView()
        outputView = OutputView()
        discountCalculator = ChristmasDiscountCalculator()

    }


    fun processUserInfo() {

        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
        this.visitDay = inputView.readDate()
        this.userMenu = inputView.readMenu()

        outputView.printMenu(userMenu)

        this.totalCost = discountCalculator.calculateTotalPrice(userMenu)
        outputView.printTotalPrice(totalCost)



    }

    fun processBenefits(){

        println("12월 ${this.visitDay}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        this.benefits = discountCalculator.calculateTotalBenefits(totalCost, visitDay, userMenu)
        outputView.printBenefits(benefits)

        this.totalBenefitPrice = discountCalculator.calculateTotalBenefitPrice()
        outputView.printDiscountedTotalPrice(totalCost, totalBenefitPrice, benefits)

        outputView.printBadge(totalBenefitPrice)

    }




}

