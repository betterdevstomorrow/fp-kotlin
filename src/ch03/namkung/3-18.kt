package ch03.namkung

/*
trampoline 함수를 사용하며 연습문제 3-17의 함수를 다시 작성해보자.
 */

private sealed class Bounce<A>
private data class Done<A>(val result: A): Bounce<A>()
private data class More<A>(val thunk: () -> Bounce<A>): Bounce<A>()

private tailrec fun<A> tramploline(bounce: Bounce<A>): A = when (bounce) {
    is Done -> bounce.result
    is More -> tramploline(bounce.thunk());
}

private fun squareRoot(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { divideTwo(Math.sqrt(n)) }
}

private fun divideTwo(n: Double):  Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { squareRoot(n / 2) }
}


fun main() {
    println(tramploline(squareRoot(5.0)))  // 0.528685631720282
}
