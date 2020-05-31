import java.math.BigDecimal

fun main(args: Array<String>) {
//    println(trampoline(factorial4(BigDecimal(100000))))
    println(trampoline(factorial4(BigDecimal(5))))
}

private fun factorial4(n: BigDecimal, value: BigDecimal = BigDecimal.ONE): Bounce<BigDecimal> = when (n) {
    BigDecimal.ONE -> Done(value)
    else -> More { factorial4(n.dec(), n * value) }
//    else -> More {
//        factorial4(n.subtract(BigDecimal.ONE), value.multiply(n))
//    }
}