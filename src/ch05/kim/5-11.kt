package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;

/**
 *
 * 연습문제 5-11
 *
 * reverse 함수를 foldLeft 함수를 사용해서 재작성 해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    val list = funListOf(1, 2, 3, 4, 5)
    require(list.reverseByFoldRight() == funListOf(5, 4, 3, 2, 1))
}

fun <T> FunList<T>.reverseByFoldRight(): FunList<T> = this.foldRight(Nil, { x, acc: FunList<T> ->
    acc.appendTail(x)
})