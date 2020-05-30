fun main(args: Array<String>) {
    println(power(2.0, 5))
}

private tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when {
    n <= 0 -> acc
    else -> power(x, n - 1, acc * x)

}