package ch03.kwon.example

fun main() {
    // require(0.528685631720282 == squareRoot(5.0))
}

/*private fun squareRoot(n: Double): Double = when {
    // Math.sqrt(n)
}*/

private fun divideTwo(n: Double): Double = when {
    n < 1 -> n
    else -> n/2
}
