fun main(args: Array<String>) {
    println(squareRoot(30.0))
}


private fun squareRoot(n: Double): Double = when {
    n < 1 -> n
    else -> div2(Math.sqrt(n))
}

private fun div2(n: Double): Double = when {
    n < 1 -> n
    else -> squareRoot(n / 2)
}
