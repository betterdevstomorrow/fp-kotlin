package ch03.namkung

import ch03.Bounce
import ch03.Done
import ch03.More
import java.math.BigDecimal

/*
trampoline 함수를 사용하여 연습문제 3-12의 factorial 함수를 다시 작성해보자.
100000! 값은 얼마인가?
 */

private fun factorial(input: BigDecimal, acc: BigDecimal): Bounce<BigDecimal> = when(input) {
    BigDecimal(0) -> Done(acc)
    else -> More { factorial(input.dec(), input * acc) }
}

fun main() {
    println(ch03.tramploline(factorial(BigDecimal(10), BigDecimal(1))))  // 3628800
    println(ch03.tramploline(factorial(BigDecimal(100000), BigDecimal(1)))) // Very big number
}
