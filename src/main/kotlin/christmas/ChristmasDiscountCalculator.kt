package christmas
import java.time.LocalDate
import java.time.Month

class ChristmasDiscountCalculator(private val userInputs: UserInputs) {

    private val starDay = listOf(3,10,17,24,25,31)
    private val benefits = Discount.entries.associateWith { 0 }.toMutableMap()

    init {

        val december31Date = LocalDate.of(2023, Month.DECEMBER, 31)
        if (userInputs.totalPrice >= 10000 && LocalDate.now().isBefore(december31Date))
            calculateBenefits()
    }

    private fun calculateFreeItem() =
        if (userInputs.totalPrice >= 120000) Discount.FREE_ITEM.price else 0

    private fun calculateSpecialDiscount() =
        if (userInputs.visitDay in starDay) Discount.SPECIAL.price else 0

    private fun calculateDdayDiscount() =
        if (userInputs.visitDay < 26) (userInputs.visitDay - 1) * Discount.CHRISTMAS_D_DAY.price + 1000 else 0

    private fun calculateWeekOrWeekendDiscount(discount: Discount, category: Menus) =
        userInputs.menus.filter { category.menu.keys.contains(it.key) }.values.sum() * discount.price

    private fun selectWeekOrWeekend(dayOfWeek: Int){
        when (dayOfWeek) {
            5, 6 -> benefits[Discount.WEEKEND] = calculateWeekOrWeekendDiscount(Discount.WEEKEND, Menus.MAIN_MENU)
            else -> benefits[Discount.WEEKDAY] = calculateWeekOrWeekendDiscount(Discount.WEEKDAY, Menus.DESSERT)
        }
    }
    private fun calculateBenefits(){

        benefits[Discount.FREE_ITEM] = calculateFreeItem()
        benefits[Discount.SPECIAL] = calculateSpecialDiscount()
        benefits[Discount.CHRISTMAS_D_DAY] = calculateDdayDiscount()

        val dayOfWeek = LocalDate.of(LocalDate.now().year, 12, userInputs.visitDay).dayOfWeek.value
        selectWeekOrWeekend(dayOfWeek)

    }

    fun getBenefits() = benefits


}
