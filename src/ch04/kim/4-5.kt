package ch04.kim

import java.lang.Math.pow

private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput)) }
}

fun main(args: Array<String>) {

    val pow = { i: Double -> pow(i, 2.0) }
    val getMax = { i: List<Int> -> i.max()!!.toDouble() }

    val composed = pow compose getMax
    val result2 = composed(listOf(3, -1, 4, 10, -8));
    println(result2)
}
