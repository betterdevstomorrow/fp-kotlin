package ch03.kwon.example

fun main() {
    require(1 == factorial(1))
    require(24 == factorial(4))
    require(5040 == factorial(7))
    require(3628800 == factorial(10))
}

private fun factorial(n: Int): Int = when(n) {
    0 -> 1
    else -> n * factorial(n-1)
}
