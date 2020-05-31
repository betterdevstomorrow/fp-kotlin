fun main(args: Array<String>) {
    println(factorial4(5))
    println(factorial4(7))
}

private tailrec fun factorial4(n: Int, value: Int = 1): Int = when (n) {
    1 -> value
    else -> {
        println("calc" + n)
        factorial4(n - 1, n * value)
    }
}