package ch04.cha

private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput))}
}

fun main() {
    val max = { i: List<Int> -> i.max() }
    val power = { i: Int -> i * i }

    // TODO
    val composed = power compose max
    println(power(max(listOf(1, 2, 3))))
}
