package ch05.kim

import ch05.kim.FunList.Cons

private tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when {
    n < 1 -> this
    this is FunList.Cons -> tail.drop(n - 1)
    else -> FunList.Nil
}

fun main(args: Array<String>) {
    val target = Cons(1, Cons(2, Cons(3,
            Cons(4, FunList.Cons(5, FunList.Nil)))))
    val result = target.drop(4); // 5, Nil
    println(result)
}