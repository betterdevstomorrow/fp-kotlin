fun main(args: Array<String>) {
    println(power(2.0, 5))
}

private fun power(x: Double, n: Int): Double = when {
    n <= 0 -> 1.0
    n == 1 -> x
    else -> x * power(x, n - 1)
}