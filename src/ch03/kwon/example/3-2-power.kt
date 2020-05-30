package ch03.kwon.example

fun main() {
    require(25.0 == power(5.0, 2))
    require(1024.0 == power(2.0, 10))
}

private fun power(x: Double, n: Int): Double = when {
    n == 0 -> 1.0
    else -> x * power(x, n-1)
}
