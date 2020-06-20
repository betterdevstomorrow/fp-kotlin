package ch05.kim

import ch05.kim.FunStream.Cons;
import ch05.kim.FunStream.Nil;


/**
 *
 * 연습문제 5-17
 *
 * FunList에서 작성했던 sum 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    sequenceOf(1, 2, 3).sum()
    require(funStreamOf(1, 2, 3, 4, 5).sum() == 1 + 2 + 3 + 4 + 5)
//    require((1..10000).toFunStream().sum() == 50005000)
}

private fun FunStream<Int>.sum(): Int =  foldLeft(0) { acc, x -> acc + x }

