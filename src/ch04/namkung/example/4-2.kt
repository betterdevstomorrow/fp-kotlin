package ch04.namkung.example

// 매개변수 3개를 받는 부분 적용 함수 3개를 직접 구현하라.

private fun <P1, P2, P3, R>((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R {
    return { p2, p3 -> this(p1, p2, p3)}
}
fun main() {
    val func = { a: String, b:String, c: String  -> a + b + c}

    val partiallyAppliedFunc1 = func.partial1("1")
    val result = partiallyAppliedFunc1("2", "3")

    println(result)
}
