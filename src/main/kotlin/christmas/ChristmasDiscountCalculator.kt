package christmas
import java.time.LocalDate

class ChristmasDiscountCalculator() {

    private val starDay = listOf(3,10,17,24,31)
    private val benefits = Discount.entries.associate { it to 0 }.toMutableMap()

    private fun calculateFreeItem(totalPrice: Int) =
        if (totalPrice >= 120000) Discount.FREE_ITEM.price else 0

    private fun calculateSpecialDiscount(day: Int) =
        if (day in starDay) Discount.SPECIAL.price else 0

    private fun calculateDdayDiscount(day: Int) =
        if (day < 26) (day - 1) * Discount.CHRISTMAS_D_DAY.price + 1000 else 0

    private fun calculateWeekOrWeekendDiscount(discount: Discount, category: Menus, userMenus: Map<String, Int>) =
        userMenus.filter { category.menu.keys.contains(it.key) }.values.sum() * discount.price

    fun calculateTotalPrice(userMenus: Map<String, Int>): Int =
        userMenus.entries.sumOf { (menu, quantity) -> Menus.allMenu[menu]!! * quantity }


    fun calculateTotalBenefits(totalPrice: Int, day: Int, userMenus: Map<String, Int>) : Map<Discount, Int>{
        benefits[Discount.FREE_ITEM] = calculateFreeItem(totalPrice)
        benefits[Discount.SPECIAL] = calculateSpecialDiscount(day)
        benefits[Discount.CHRISTMAS_D_DAY] = calculateDdayDiscount(day)


        val dayOfWeek = LocalDate.of(LocalDate.now().year, 12, day).dayOfWeek.value
        when (dayOfWeek) {
            5, 6 -> benefits[Discount.WEEKEND] = calculateWeekOrWeekendDiscount(Discount.WEEKEND, Menus.MAIN_MENU, userMenus)
            else -> benefits[Discount.WEEKDAY] = calculateWeekOrWeekendDiscount(Discount.WEEKDAY, Menus.DESSERT, userMenus)
        }

        return benefits
    }

    fun calculateTotalBenefitPrice() = benefits.values.sum()
}
