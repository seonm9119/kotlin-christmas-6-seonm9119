package christmas

enum class MenuValidityRulesAndMessage(
    val rule: (List<Pair<String, Int>>) -> Boolean,
    val message: String
) {
    DUPLICATE_MENUS(
        { userMenus -> userMenus.distinct().size == userMenus.size },
        ErrorMessages.ERROR_MENU.message
    ),
    TOTAL_QUANTITY_EXCEEDS_20(
        { userMenus -> userMenus.sumOf { it.second } < 21 },
        ErrorMessages.TOTAL_QUANTITY_EXCEEDS_20.message
    ),
    NON_ZERO_QUANTITY(
        { userMenus -> userMenus.all { it.second != 0 } },
        ErrorMessages.ERROR_MENU.message
    ),
    VALID_MENU_ITEMS(
        { userMenus -> userMenus.all { it.first in Menus.allMenu.keys } },
        ErrorMessages.ERROR_MENU.message
    ),
    INVALID_DRINK_ITEMS(
        { userMenus -> userMenus.any { it.first !in Menus.DRINK.menu.keys } },
        ErrorMessages.INVALID_DRINK_ITEMS.message
    )
}

enum class ErrorMessages(val message: String){

    ERROR_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DRINK_ITEMS("[ERROR] 음료만 주문할 수 없습니다."),
    TOTAL_QUANTITY_EXCEEDS_20("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    ERROR_DAY("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    fun printMessage(){
        println(message)
    }

}
