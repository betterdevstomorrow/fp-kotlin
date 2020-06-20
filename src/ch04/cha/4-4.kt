package ch04.cha

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = {
    p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3)}}
}

private fun <P1, P2, P3, R> ((P1) -> (P2) -> (P3) -> R).uncurried(): (P1, P2, P3) -> R = {
    p1: P1, p2: P2, p3: P3 -> this(p1)(p2)(p3)
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = {
    p1: P1 -> { p2: P2 -> this(p1, p2)}
}

fun main() {
    val func = { a: Int, b: Int -> if (a < b) a else b }
    val cf = func.curried()
    println(cf(2)(3))
    println(cf(3)(1))
}