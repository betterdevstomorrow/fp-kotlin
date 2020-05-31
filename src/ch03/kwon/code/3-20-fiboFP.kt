package ch03.kwon.code

import kotlin.system.measureNanoTime

fun main() {
    val elapsed1 = measureNanoTime {
        fiboMemoization(10000)
    }
    println(elapsed1)

    val elapsed2 = measureNanoTime {
        fiboFP(10000)
    }
    println(elapsed2)

    val elapsed3 = measureNanoTime {
        fiboFPTail(10000)
    }
    println(elapsed3)
}

private fun fiboFP(n: Int): Int = fiboFP(n, 0, 1)

private fun fiboFP(n: Int, first: Int, second: Int): Int = when (n) {
    0 -> first
    1 -> second
    else -> fiboFP(n - 1, second, first + second)
}

private fun fiboFPTail(n: Int): Int = fiboFPTail(n, 0, 1)

private tailrec fun fiboFPTail(n: Int, first: Int, second: Int): Int = when (n) {
    0 -> first
    1 -> second
    else -> fiboFP(n - 1, second, first + second)
}

private var memo = Array(10001) { -1 }

private fun fiboMemoization(n: Int): Int = when {
    n == 0 -> 0
    n == 1 -> 1
    memo[n] != -1 -> memo[n]
    else -> {
        // println("fiboMemoization($n)")
        memo[n] = fiboMemoization(n - 2) + fiboMemoization(n - 1)
        memo[n]
    }
}

