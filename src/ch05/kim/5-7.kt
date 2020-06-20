package ch05.kim

import ch05.kim.FunList.Cons;
import ch05.kim.FunList.Nil;

/**
 * 연습문제 5-7
 *
 * 다음과 같이 동작하는 ``takeWhile`` 함수를 구현하자. 타입 ``T``를 입력받아 ``Boolean``을 반환하는 함수 ``p``를 받는다. 리스트의 앞에서부터 함수
 * ``p``를 만족하는 값들의 리스트를 반환한다.(모든 값이 함수 ``p``를 만족하지 않는다면 원본 ``List``를 반환). 이때 원본 리스트가 바뀌지 않고, 새로운
 * 리스트를 반환할 때 매번 리스트를 생성하지 않아야 한다.
 *
 */


//TODO: 정답지와 문제가 다른것같다: 정답지는 p조건을 만족하기전까지의 리스트를 반환함.

private tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = Nil, p: (T) -> Boolean): FunList<T> = when (this) {
    Nil -> acc
    is Cons -> {
        val nextAcc = if (p(head)) acc.appendTail(head) else acc
        tail.takeWhile(nextAcc, p)
    }

//    Nil -> acc.reverse()
//    is Cons -> when (p(head)) {
//        false -> tail.takeWhile(acc, p)
//        true -> tail.takeWhile(acc.addHead(head), p)
//    }
}

fun main(args: Array<String>) {
    val target = Cons(1, Cons(12, Cons(3, Cons(4, Cons(5, Nil)))))
    require(target.takeWhile { it % 2 == 1 } == funListOf(1, 3, 5))

    //FIXME 모든값이 p를 만족하지않을 때 원본 반환하는건 모르겠다!
    require(target.takeWhile { it % 2 == 4 } == funListOf(1, 12, 3, 4, 5))
}

