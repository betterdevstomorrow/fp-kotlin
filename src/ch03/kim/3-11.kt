fun main(args: Array<String>) {
    println(factorial3(5))
    println(factorial3(7))
}

//private fun factorial3(n: Int): Int = factorial3(n, 1);

private fun factorial3(n: Int, value: Int = 1): Int = when (n) {
    1 -> 1
    else -> {
        println("calc" + n)
        factorial3(n - 1, n * value)
    }
}