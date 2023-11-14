package christmas

import camp.nextstep.edu.missionutils.Console.readLine
class InputView {
    fun readDate(): Int {
        while (true) {
            UserPrompt.EXPECTED_DAY.printMessage()

            try {
                return readLine().toInt().takeIf { it in 1..31 } ?:
                throw IllegalArgumentException()

            } catch (e: NumberFormatException) {
                UserPrompt.ERROR.printMessage()
            }catch (e: IllegalArgumentException){
                UserPrompt.ERROR.printMessage()
            }
        }
    }


    fun readMenu(): Map<String, Int> {

        while (true) {
            UserPrompt.USER_MENU.printMessage()
            try {
                val userInput = readLine().split(',')
                    .map {
                        val (menu, quantity) = it.split('-')
                        menu to quantity.toInt()
                    }

                val duplicateMenus = userInput.groupingBy { it.first }
                    .eachCount()
                    .filter { it.value > 1 }

                if (duplicateMenus.isNotEmpty()) throw IllegalArgumentException()
                if (userInput.sumOf { it.second } > 21) throw IllegalArgumentException()
                if (userInput.any { it.second == 0 }) throw IllegalArgumentException()

                return userInput.toMap()

            } catch (e: NumberFormatException){
                UserPrompt.ERROR.printMessage()
            } catch (e: IndexOutOfBoundsException){
                UserPrompt.ERROR.printMessage()
            } catch (e: IllegalArgumentException) {
                UserPrompt.ERROR.printMessage()
            }
        }
    }


}