package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;

/**
 *
 * 연습문제 5-12
 *
 * filter 함수를 foldRight 함수를 사용해서 재작성 해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    val list = funListOf(1, 2, 3, 4, 5)
    require(list.filterByFoldRight { it % 2 == 0 } == funListOf(2, 4))
    require(list.filterByFoldRight { it < 1 } == FunList.Nil)
    require(list.filterByFoldRight { it < 6 } == funListOf(1, 2, 3, 4, 5))
}

private fun <T> FunList<T>.filterByFoldRight(p: (T) -> Boolean): FunList<T> = this.foldRight(Nil, { x, acc: FunList<T> ->
    if (p(x)) {
        acc.addHead(x)
    } else {
        acc
    }
})