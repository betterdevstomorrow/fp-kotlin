package ch03.namkung

/*
 코드 3-9의 take 함수를 참고하고 repeat 함수를 테스트하기 위해서 사용한 takeSequence 함수를 작성해 보자. 그리고 repeat 함수가 잘 동작하는지 테스트해보자.
 */

// 문제 풀기 전에 정의해야 하는 함수들
private fun Sequence<Int>.head() = first()
private fun Sequence<Int>.tail() = drop(1)

private fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when {
    n <= 0 -> listOf()
    sequence.none() -> listOf()
    else -> listOf(sequence.head()) + takeSequence(n - 1, sequence.tail())
}

fun main() {
    println(takeSequence(5, generateSequence(3) { it }))
}
