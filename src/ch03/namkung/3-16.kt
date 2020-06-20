package ch03.namkung

//연슴문제 3-6에서 작성한 element 함수가 꼬리 재귀인지 확인해 보자, 만약 꼬맂 재귀가 아니라면 개선해 보자.
// 문제 풀기 전에 정의해야 하는 함수들
private fun List<Int>.head() = first()
private fun List<Int>.tail() = drop(1)

private tailrec fun element(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    list.contains(num) -> true
    else -> element(num, list.tail())
}
fun main() {
    println(element(5, listOf(1, 3, 5)))   // true
}
