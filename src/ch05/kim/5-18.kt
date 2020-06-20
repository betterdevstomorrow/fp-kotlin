package ch05.kim

import ch05.kim.FunStream.Cons;
import ch05.kim.FunStream.Nil;


/**
 *
 * 연습문제 5-18
 *
 * FunList에서 작성했던 product 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    require(funStreamOf(1, 2, 3, 4, 5).product() == 1 * 2 * 3 * 4 * 5)
}

private fun FunStream<Int>.product(): Int = foldLeft(1, { acc, x -> acc * x })

