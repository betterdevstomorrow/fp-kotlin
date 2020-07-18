package ch05.cha

sealed class FunList<out T> {
    object Nil : FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
}

fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)
fun <T> FunList<T>.appendTail(value: T): FunList<T> = when(this) {
    FunList.Nil -> FunList.Cons(value, FunList.Nil)
    is FunList.Cons -> FunList.Cons(head, tail.appendTail(value))
}

fun main() {
    val intList: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Cons(3, FunList.Cons(4, FunList.Cons(5, FunList.Nil)))))
}