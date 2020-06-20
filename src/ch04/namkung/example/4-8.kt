package ch04.namkung.example
/*
연습문제 4-7에서 작성한 takeWhile를 수정하여, 무한대를 입력바들 수 있는 takeWhile을 꼬리 재귀로 작성해 보자.
hint
 - generateSequence(1) { it + 1 }은 초기값 1에서 시작하여 무한대로 1씩 증가하는 무한대의 리스트를 표현한다.
 */

// 문제 풀기 전에 정의해야 하는 함수들
private fun Sequence<Int>.head() = first()
private fun Sequence<Int>.tail() = drop(1)

private tailrec fun takeWhile(condition: (Int) -> Boolean, sequence: Sequence<Int>, acc: List<Int> = listOf()): List<Int> = when {
    sequence.none() || !condition(sequence.head()) -> acc
    else -> takeWhile(condition, sequence.tail(), acc + sequence.head())
}
fun main() {
    println(takeWhile({ p -> p < 10 }, generateSequence(1) { it + 1 }))   // [1, 2]
}
