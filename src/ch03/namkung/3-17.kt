package ch03.namkung

/*
입력값 n의 제곱근을 2로 나눈 값이 1보자 작을 때까지 반복하고,
최초의 1보다 작은 값을 반환하는 함수를 상호 재귀를 사용하여 구현하라.
이 때 입력값 n은 2보다 크다.
 */

private fun squareRoot(n: Double): Double = when {
    n < 1 -> n
    else -> divideTwo(Math.sqrt(n))
}

private fun divideTwo(n: Double): Double = when {
    n < 1 -> n
    else -> squareRoot(n / 2)
}

fun main() {
    println(squareRoot(5.0))  // 0.528685631720282
}
