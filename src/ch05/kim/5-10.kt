package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;

/**
 *
 * 연습문제 5-10
 *
 * filter 함수를 foldLeft 함수를 사용해서 재작성 해보자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    val list = funListOf(1, 2, 3, 4, 5)
    require(list.filterByFoldLeft { it % 2 == 0 } == funListOf(2, 4))
}

fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> =
        this.foldLeft(Nil,
                { acc: FunList<T>, x ->
                    if (p(x)) {
                        acc.appendTail(x)
                    } else {
                        acc
                    }
                })

