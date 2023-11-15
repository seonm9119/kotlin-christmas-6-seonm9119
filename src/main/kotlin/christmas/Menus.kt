package christmas

enum class Menus(val menu: Map<String, Int>) {

    APPETIZER(mapOf(
        "양송이 수프" to 6000,
        "타파스" to 5500,
        "시저샐러드" to 8000)
    ),

    MAIN_MENU(mapOf(
        "티본스테이크" to 55000,
        "바비큐립" to 54000,
        "해산물파스타" to 35000,
        "크리스마스파스타" to 25000)
    ),

    DESSERT(mapOf(
        "초코케이크" to 15000,
        "아이스크림" to 5000)
    ),

    DRINK(mapOf(
        "제로콜라" to 3000,
        "레드와인" to 60000,
        "샴페인" to 25000)
    );

    companion object {
        val allMenu: Map<String, Int> by lazy {
            entries.flatMap { it.menu.entries }.associate { it.toPair() }
        }
    }

}
