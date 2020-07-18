package ch04.cha

private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput))}
}

fun main() {
    val max = { i: List<Int> -> i.max()!! } // !!널 값이 안들어온다는 것을 보증해주는 연산자
    val power = { i: Int -> i * i }

    // TODO
    val composed = power compose max
    println(composed(listOf(1, 2, 3)))
}
