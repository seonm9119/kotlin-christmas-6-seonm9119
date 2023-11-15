package christmas

enum class Discount(val message: String, val price: Int) {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 100),
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    SPECIAL("특별 할인", 1000),
    FREE_ITEM("증정 이벤트", 25000);
}


enum class Badge (val grade: String, val price: Int){
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000)
}

enum class UserPrompt(val message: String){
    MENU("\n<주문 메뉴>"),
    TOTAL_COST("\n<할인 전 총주문 금액>"),
    FREE_ITEM("\n<증정 메뉴>"),
    BENEFITS("\n<혜택 내역>"),
    TOTAL_BENEFIT("\n<총혜택 금액>"),
    EXPECTED_PAYMENT("\n<할인 후 예상 결제 금액>"),
    BADGE("\n<12월 이벤트 배지>"),
    NONE("없음"),
    EXPECTED_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    USER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");


    fun printMessage(){
        println(message)
    }


}