package christmas

import camp.nextstep.edu.missionutils.Console.readLine
class InputView {

    private fun checkValidityMenu(userMenus: List<Pair<String, Int>> ) =
        MenuValidityRulesAndMessage.entries.forEach { require(it.rule(userMenus)) { it.message } }


    private fun processInputs(): Map<String, Int> {
        val userMenus = readLine()?.split(',')?.map {
            val (menu, quantity) = it.split('-')
            menu to quantity.toInt()
        } ?: throw NumberFormatException()

        checkValidityMenu(userMenus)
        return userMenus.toMap()
    }


    fun readDate(): Int {
        while (true) {
            UserPrompt.EXPECTED_DAY.printMessage()

            try {
                return readLine().toInt().takeIf { it in 1..31 } ?: throw IllegalArgumentException()
            } catch (e: NumberFormatException) {
                ErrorMessages.ERROR_DAY.printMessage()
            }catch (e: IllegalArgumentException){
                ErrorMessages.ERROR_DAY.printMessage()
            }
        }
    }


    fun readMenu(): Map<String, Int> {
        while (true) {
            UserPrompt.USER_MENU.printMessage()
            try {
                return processInputs()
            } catch (e: NumberFormatException) {
                ErrorMessages.ERROR_MENU.printMessage()
            } catch (e: IndexOutOfBoundsException) {
                ErrorMessages.ERROR_MENU.printMessage()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }





}