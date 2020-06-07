package ch03.namkung

// 입력값이 n의 리스트에 존재하는지 확인하는 함수를 재귀로 작성해보자.

// 문제 풀기 전에 정의해야 하는 함수들
private fun List<Int>.head() = first()
private fun List<Int>.tail() = drop(1)

private fun element(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    list.contains(num) -> true
    else -> element(num, list.tail())
}

fun main() {
    println(element(10, listOf(1, 2, 10)))
    println(element(100, listOf(1, 2, 10)))
}
