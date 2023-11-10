package christmas

import camp.nextstep.edu.missionutils.Console.readLine
class InputView {
    fun readDate(): Int {
        while (true) {
            println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")

            try {
                val inputs = readLine().toInt()
                return if (inputs in 1..31) inputs else throw IllegalArgumentException()

            } catch (e: NumberFormatException) {
                println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
            } catch (e: IllegalArgumentException){
                println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
            }
        }
    }


    fun readMenu(): Map<String, Int> {

        while (true) {
            println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1)")
            try {
                val userInput = readLine().split(',')
                    .map {
                        val (menu, quantity) = it.split('-')
                        menu to quantity.toInt()
                    }

                val duplicateMenus = userInput.groupingBy { it.first }
                    .eachCount()
                    .filter { it.value > 1 }

                if (duplicateMenus.isNotEmpty()) throw IllegalArgumentException("[ERROR-4]")
                if (userInput.sumOf { it.second } > 21) throw IllegalArgumentException("[ERROR-6]")
                if (userInput.any { it.second == 0 }) throw IllegalArgumentException("[ERROR-2]")

                return userInput.toMap()

            } catch (e: NumberFormatException){
                println("[ERROR-3] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            } catch (e: IndexOutOfBoundsException){
                println("[ERROR-3] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            } catch (e: IllegalArgumentException) {
                println("${e.message} 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }
        }
    }


}