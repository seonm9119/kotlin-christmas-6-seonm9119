package christmas
import java.time.LocalDate

class ChristmasDiscountCalculator(private val day: Int, private val userMenus: Map<String, Int>) {

    private val date = LocalDate.of(LocalDate.now().year, 12, day)
    private val dayOfWeek = date.dayOfWeek.value
    private val starDay = listOf(3,10,17,24,31)

    private var totalPrice = 0
    private var totalBenefit = 0
    private val benefits = Discount.entries.associate { it to 0 }.toMutableMap()

    init {
        calculateTotalPrice()
        calculateTotalBenefits()
    }

    private fun calculateTotalPrice() =
        userMenus.forEach { (menu, quantity) -> totalPrice += Menus.allMenu[menu]!! * quantity }

    private fun calculateFreeItem() =
        if (totalPrice >= 120000) Discount.FREE_ITEM.price else 0

    private fun calculateSpecialDiscount() =
        if (day in starDay) Discount.SPECIAL.price else 0

    private fun calculateDdayDiscount() =
        if (day < 26) (day - 1) * Discount.CHRISTMAS_D_DAY.price + 1000 else 0

    private fun calculateWeekOrWeekendDiscount(discount: Discount, category: Menus) =
        userMenus.filter { category.menu.keys.contains(it.key) }.values.sum() * discount.price

    private fun calculateTotalBenefits() {
        benefits[Discount.FREE_ITEM] = calculateFreeItem()
        benefits[Discount.SPECIAL] = calculateSpecialDiscount()
        benefits[Discount.CHRISTMAS_D_DAY] = calculateDdayDiscount()

        when (dayOfWeek) {
            5, 6 -> benefits[Discount.WEEKEND] = calculateWeekOrWeekendDiscount(Discount.WEEKEND, Menus.MAIN_MENU)
            else -> benefits[Discount.WEEKDAY] = calculateWeekOrWeekendDiscount(Discount.WEEKDAY, Menus.DESSERT)
        }

        totalBenefit += benefits.values.sum()
    }

    fun getTotalPrice() = totalPrice
    fun getBenefits() = benefits
    fun getTotalBenefit() = totalBenefit
}
