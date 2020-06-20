package ch05.kim

import ch05.kim.FunStream.Cons;
import ch05.kim.FunStream.Nil;


/**
 *
 * 연습문제 5-19
 *
 * FunList에서 작성했던 appendTail 함수를 FunStream에도 추가하자.
 *
 * 힌트: 함수의 선언 타입은 아래와 같다.
 *
 */

fun main() {
    println(funStreamOf(1).addHead(2) == funStreamOf(1, 2))
    println(funStreamOf(1, 2, 3, 4, 5).reverse() == funStreamOf(5, 4, 3, 2, 1))
    require(funStreamOf(1, 2, 3, 4, 5).appendTail(6) == funStreamOf(1, 2, 3, 4, 5, 6))
}

fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
    Nil -> Cons({ value }, { Nil })
    is Cons -> Cons(head, { tail().appendTail(value) })
}

//fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
//    FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
//    is FunStream.Cons -> FunStream.Cons(head, { tail().appendTail(value) })
//}
