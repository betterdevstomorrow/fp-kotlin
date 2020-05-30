import kotlin.math.sqrt

fun main(args: Array<String>) {
    println(trampoline(squareRoot(30.0)))
}


private fun squareRoot(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { div2(sqrt(n)) }
}

private fun div2(n: Double): Bounce<Double> = when {
    n < 1 -> Done(n)
    else -> More { squareRoot(n / 2) }
}
