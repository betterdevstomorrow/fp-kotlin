package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;


/**
 * 연습문제 5-8
 *
 * 앞서 작성한 map 함수에서 고차함수가 값들의 순서값(인덱스)도 같이 받아 올수 있는 indexedMap 함수를 만들자.
 */
fun main() {
    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    require(intList.indexedMap { index, elm -> index * elm } == funListOf(0, 2, 6))
}

private tailrec fun <T, R> FunList<T>.indexedMap(index: Int = 0, acc: FunList<R> = Nil, f: (Int, T) -> R): FunList<R> = when (this) {
    Nil -> acc
    is Cons -> tail.indexedMap(index + 1, acc.appendTail(f(index, head)), f)
}
