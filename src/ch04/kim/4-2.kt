package ch04.kim


//private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R {
//    return { p2, p3 -> this(p1, p2, p3) }
//}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R {
    return { p1, p3 -> this(p1, p2, p3) }
}

fun main(args: Array<String>) {
    val func = { a: String, b: String, c: Int -> a + " " + c + " " + b }
    val partiallyAppliedFunc1 = func.partial2("finished hw");

    val result1 = partiallyAppliedFunc1("maisy", 11)

    println(result1)
}