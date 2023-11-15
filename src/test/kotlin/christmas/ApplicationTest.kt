package christmas

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Nested

class ApplicationTest : NsTest() {
    @Test
    fun `모든 타이틀 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            )
        }
    }

    @Test
    fun `혜택 내역 없음 출력`() {
        assertSimpleTest {
            run("26", "타파스-1,제로콜라-1")
            assertThat(output()).contains("<혜택 내역>$LINE_SEPARATOR".toString() + "없음")
        }
    }

    @Test
    fun `날짜 예외 테스트`() {
        assertSimpleTest {
            runException("a")
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트`() {
        assertSimpleTest {
            runException("3", "제로콜라-a")
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    override fun runMain() {
        main()
    }

    companion object {
        private val LINE_SEPARATOR = System.lineSeparator()
    }

    //추가 테스트
    @Nested
    inner class `날짜 예외 테스트 추가` {

        @Test
        fun `1 이상 31 이하의 숫자가 아닌 경우`() {
            assertSimpleTest {
                runException("45")
                assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
            }
        }


    }

    @Nested
    inner class `주문 예외 테스트 추가` {


        @Test
        fun `고객이 메뉴판에 없는 메뉴를 입력하는 경우`() {
            assertSimpleTest {
                runException("3", "스프라이트-1")
                assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }

        }

        @Test
        fun `메뉴의 개수는 1 이상이 아닐 경우`() {
            assertSimpleTest {
                runException("3", "티본스테이크-0,바비큐립-0,초코케이크-8")
                assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }
        }

        @Test
        fun `메뉴 형식이 예시와 다른 경우`() {
            assertSimpleTest {
                runException("3", "티본스테이크=1,바비큐립=1,초코케이크=2,제로콜라=1")
                assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }

        }

        @Test
        fun `중복 메뉴를 입력한 경우`() {
            assertSimpleTest {
                runException("3", "시저샐러드-1,시저샐러드-1")
                assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
            }
        }

        @Test
        fun `음료만 주문 할 경우`() {
            assertSimpleTest {
                runException("3", "제로콜라-1,레드와인-3")
                assertThat(output()).contains("[ERROR] 음료만 주문할 수 없습니다.")
            }
        }

        @Test
        fun `주문한 메뉴의 갯수가 21개 이상일 경우`() {
            assertSimpleTest {
                runException("3", "티본스테이크-3,바비큐립-4,제로콜라-103")
                assertThat(output()).contains("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.")
            }
        }


    }

    @Nested
    inner class `이벤트 테스트 추가` {

        @Test
        fun `총주문 금액 10,000원 이하 혜택 없음`() {
            assertSimpleTest {
                run("3", "타파스-1,제로콜라-1")
                assertThat(output()).contains("<혜택 내역>$LINE_SEPARATOR".toString() + "없음")
            }
        }

        @Test
        fun `증정메뉴 있을경우`() {
            assertSimpleTest {
                run("3", "해산물파스타-2,레드와인-1,초코케이크-1")
                assertThat(output()).contains("<증정 메뉴>$LINE_SEPARATOR".toString() + "샴페인 1개")
            }
        }

        @Test
        fun `크리스마스 디데이 할인`() {
            assertSimpleTest {
                run("3", "해산물파스타-2,레드와인-1,초코케이크-1")
                assertThat(output()).contains("크리스마스 디데이 할인: -1,200원")
            }
        }

        @Test
        fun `평일할인`() {
            assertSimpleTest {
                run("7", "해산물파스타-2,레드와인-1,초코케이크-1")
                assertThat(output()).contains("평일 할인: -2,023원")
            }
        }

        @Test
        fun `주말할인`() {
            assertSimpleTest {
                run("15", "해산물파스타-2,레드와인-1,초코케이크-1")
                assertThat(output()).contains("주말 할인: -4,046원")
            }
        }

        @Test
        fun `특별할인`() {
            assertSimpleTest {
                run("25", "해산물파스타-2,레드와인-1,초코케이크-1")
                assertThat(output()).contains("특별 할인: -1,000원")
            }
        }






    }


    @Nested
    inner class `배지 테스트 추가`{

        @Test
        fun `별`() {
            assertSimpleTest {
                run("17", "레드와인-1,초코케이크-1")
                assertThat(output()).contains("<12월 이벤트 배지>$LINE_SEPARATOR".toString() + "별")
            }
        }

        @Test
        fun `트리`() {
            assertSimpleTest {
                run("24", "초코케이크-5")
                assertThat(output()).contains("<12월 이벤트 배지>$LINE_SEPARATOR".toString() + "트리")
            }
        }

        @Test
        fun `산타`() {
            assertSimpleTest {
                run("25", "해산물파스타-2,레드와인-1,초코케이크-1")
                assertThat(output()).contains("<12월 이벤트 배지>$LINE_SEPARATOR".toString() + "산타")
            }
        }



    }
}
