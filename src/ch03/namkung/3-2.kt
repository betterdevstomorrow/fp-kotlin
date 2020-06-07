package ch03.namkung

//  X의 n 승을 구하는 함수를 재귀로 후현해보자.
private fun power(x: Double, n: Int): Double = when(n) {
        0 -> 1.0
        else -> x * power(x, n - 1)
}

fun main() {
    println(power(10.0, 2))
}
