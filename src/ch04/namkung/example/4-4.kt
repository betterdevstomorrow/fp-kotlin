package ch04.namkung.example

// 두 개의 매개변수를 받아서 작은 값을 반환하는 main 함수 curried 함수를 사용해서 작성하라
private fun min(i: Int, j: Int) = { if (i > j) j else i }
private val min2 = { i: Int, j: Int -> if (i > j) j else i }
private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 -> { p2: P2 -> this(p1, p2) } }

fun main() {
    val curriedMin = min2.curried()
    println(curriedMin(10)(30))
}
