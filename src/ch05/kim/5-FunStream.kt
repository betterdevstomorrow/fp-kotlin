package ch05.kim

import ch05.kim.FunStream.Nil
import ch05.kim.FunStream.Cons

sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>() {
        override fun equals(other: Any?): Boolean =
                if (other is Cons<*>) {
                    if (head() == other.head()) {
                        tail() == other.tail()
                    } else {
                        false
                    }
                } else {
                    false
                }

        override fun hashCode(): Int {
            var result = head.hashCode()
            result = 31 * result + tail.hashCode()
            return result
        }

    }
}

fun <T> FunStream<T>.getHead(): T = when (this) {
    Nil -> throw NoSuchElementException()
    is Cons -> head()
}

fun <T> FunStream<T>.getTail(): FunStream<T> = when (this) {
    Nil -> throw NoSuchElementException()
    is Cons -> tail()
}

fun <T> FunStream<T>.addHead(value: T): FunStream<T> = when (this) {
    Nil -> Cons({ value }, { Nil })
    is Cons -> Cons(head, { tail().addHead(value) })
}

//tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = Nil): FunList<T> = when (this) {
//    Nil -> Cons(value, acc).reverse();
//    is Cons -> tail.appendTail(value, acc.addHead(head))
//}

tailrec fun <T> FunStream<T>.reverse(acc: FunStream<T> = Nil): FunStream<T> = when (this) {
    Nil -> acc //TODO ?
    is Cons -> Cons(head, { tail().reverse(acc.addHead(head())) })
}


tailrec fun <T, R> FunStream<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    Nil -> acc
    is Cons -> tail().foldLeft(f(acc, head()), f)
}

//fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
//    Nil -> acc
//    is Cons -> f(head, tail.foldRight(acc, f))
//}
//


fun <T> generateFunStream(seed: T, generate: (T) -> T): FunStream<T> =
        Cons({ seed }, { generateFunStream(generate(seed), generate) })


// functions for validatio
fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

fun <T> Array<out T>.toFunStream(): FunStream<T> = when {
    this.isEmpty() -> FunStream.Nil
    else -> FunStream.Cons({ this[0] }, { this.copyOfRange(1, this.size).toFunStream() })
}
