package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;

/**
 *
 * 연습문제 5-6
 *
 * 리스트의 앞에서부터 n개의 값을 가진 리스트를 반환하는 take 함수를 구현하자. 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할때 매번 리스트를 생성하지
 * 않아야 한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

private tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = Nil): FunList<T> = when {
    n < 1 -> acc
    this is FunList.Cons -> tail.take(n - 1, acc.appendTail(head))
    else -> throw NoSuchElementException()
}



fun main(args: Array<String>) {
    val target = Cons(1, Cons(12, Cons(3,
            Cons(4, FunList.Cons(5, FunList.Nil)))))
    val result = target.take(3); // 1, 12, 3
    println(result)

    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    require(intList.take(0) == Nil)
    require(intList.take(1) == funListOf(1))
    require(intList.take(2) == funListOf(1, 2))
    require(intList.take(3) == funListOf(1, 2, 3))
//    require(intList.take(4) == funListOf(1, 2, 3))
}