package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;

/**
 *
 * 연습문제 5-9
 *
 * maximum 함수를 foldLeft 함수를 사용해서 재작성 해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *      리스트의 모든 값은 0보다 크고, size 는 1보다 크다.
 */

fun main() {
    val list = funListOf(1, 2, 3, 4, 5)
    require(list.maximumByFoldLeft() == 5)
}


private fun FunList<Int>.maximumByFoldLeft(): Int = this.foldLeft(0, { acc: Int, x -> Math.max(acc, x) })