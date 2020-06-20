package ch05.kim

import ch05.kim.FunList.Cons

/**
 * 연습문제 5-5
 *
 * 다음과 같이 동작하는 ``dropWhile`` 함수를 구현하자. 타입 ``T``를 입력받아 ``Boolean``을 반환하는 함수 ``p``를 입력받는다. 리스트의 앞에서부터
 * 함수 ``p``를 만족하기 전까지 ``drop``을 하고, 나머지 값들의 리스트를 반환한다. 이때 원본 리스트가 바뀌지 않고, 새로운 리스트를 반환할 때 매번 리스트를 생성하지 않아야 한다.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 */

private tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> this
    is Cons -> if (p(head)) {
        tail
    } else {
        tail.dropWhile(p)
    }
}

fun main(args: Array<String>) {
    val target = Cons(1, Cons(12, Cons(3,
            Cons(4, FunList.Cons(5, FunList.Nil)))))
    val result = target.dropWhile {it == 3}; // 4, 5, Nil
    println(result)
}