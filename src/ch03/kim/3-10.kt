fun main(args: Array<String>) {
    println(factorial2(5))
    println(factorial2(7))
}

var memo = Array(100, { -1 })

fun factorial2(n: Int): Int = when {
    n == 1 -> 1
    memo[n] != -1 -> memo[n]
    else -> {
        println("calc" + n)

        memo[n] = n * factorial2(n - 1)
        memo[n]
    }
}