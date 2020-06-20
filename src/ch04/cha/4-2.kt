package ch04.cha

private fun <P1, P2, R> ((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

private fun <P1, P2, R> ((P1, P2) -> R).partial2(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1, p2: P2): (P3) -> R {
    return { p3 -> this(p1, p2, p3) }
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p1: P1, p3: P3): (P2) -> R {
    return { p2 -> this(p1, p2, p3) }
}

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p2: P2, p3: P3): (P1) -> R {
    return { p1 -> this(p1, p2, p3) }
}

// 4-18
fun main() {
    val func = { a: String, b: String -> a + b }

    val partialAppliedFunc1 = func.partial1("Hello")
    val result1 = partialAppliedFunc1("World")

    println(result1)

    val partialAppliedFunc2 = func.partial2("Hello")
    val result2 = partialAppliedFunc2("World")

    println(result2)

    val func1 = { a: Int, b: Int, c: Int -> a + b + c }

    val paf1 = func1.partial1(1,2)
    val r1 = paf1(3)
    println(r1)

    val paf2 = func1.partial2(1,2)
    val r2 = paf2(2)
    println(r2)

    val paf3 = func1.partial3(2,2)
    val r3 = paf3(3)
    println(r3)
}