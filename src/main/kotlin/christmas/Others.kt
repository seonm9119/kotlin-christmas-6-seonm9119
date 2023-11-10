package christmas

enum class DiscountMessage(val message: String){
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    FREE_ITEM("증정 이벤트");

}

enum class Badge (val grade: String, val price: Int){
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000)
}