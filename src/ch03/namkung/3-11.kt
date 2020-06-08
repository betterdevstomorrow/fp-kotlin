package ch03.namkung

// 연습문제 3-10에서 작성한 factorial 함수를 함수형 프로그래밍에 접합한 방식으로 개선해보라.
private fun factorial(input: Int, acc: Int): Int = when(input) {
    0 -> acc
    else -> factorial(input - 1, input * acc)
}
fun main() {
    println(factorial(5, 1))   // 120
}
