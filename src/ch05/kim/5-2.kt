package ch05.kim


/**
 *
 * 연습문제 5-2
 *
 * 구현한 List를 사용해 (1.0, 2.0, 3.0, 4.0, 5.0)를 갖는 doubleList를 생성하자.
 *
 */

import ch05.kim.FunList.Nil
import ch05.kim.FunList.Cons


fun main(args: Array<String>) {
    val doubleList =
            Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Cons(5.0, Nil)))))

    println(doubleList)
}