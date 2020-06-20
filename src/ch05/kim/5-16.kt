package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;


/**
 *
 * 연습문제 5-16
 *
 * 앞에서의 예제와 반대로 이번에는 큰 수에서 감소하는 값들을 가진 리스트를 입력으로 할 때를 비교해 보자. 세 가지 함수의 성능을 비교하고 테스트 결과를 분석해 보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    val bigIntList = (10000000 downTo 1).toList()

    var start = System.currentTimeMillis()
    imperativeWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 0 ms

    start = System.currentTimeMillis()
    functionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 394 ms

    start = System.currentTimeMillis()
    realFunctionalWay(bigIntList)
    println("${System.currentTimeMillis() - start} ms")    // 6 ms
}

private fun imperativeWay(intList: List<Int>): Int {
    for (value in intList) {
        val doubleValue = value * value
        if (doubleValue < 10) {
            return doubleValue
        }
    }
    throw NoSuchElementException("there is no value")
}

private fun functionalWay(intList: List<Int>): Int = intList.map { it * it }.filter { it < 10 }.first()

private fun realFunctionalWay(intList: List<Int>): Int = intList.asSequence().map { it * it }.filter { it < 10 }.first()