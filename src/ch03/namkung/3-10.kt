package ch03.namkung

// 연습문제 3-3에서 작성한 factorial 함수를 메모이제이션을 사용해서 개선해 보라.
private var memo = Array(100, { -1 })
private fun factorial(input: Int): Int = when {
    0 == input -> 1
    memo[input] != -1 -> memo[input]
    else -> {
        memo[input] = input * factorial(input - 1)
        memo[input]
    }
}
fun main() {
    println(factorial(5))   // 120
}
