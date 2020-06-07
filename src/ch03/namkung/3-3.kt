package ch03.namkung

// 입력 n의 팩터리얼(Factorial)인 n!을 구하는 함수를 재귀로 구현보자.

private fun factorial(input: Int): Int = when(input) {
    0 -> 1
    else -> input * factorial(input - 1)
}

fun main() {
    println(factorial(5))   // 120
}
