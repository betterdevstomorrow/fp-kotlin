package ch04.namkung.example


// 문제 풀기 전에 정의해야 하는 함수들
private fun List<Int>.head() = first()
private fun List<Int>.tail() = drop(1)
/*
리스트의 값을 조건 함수에 적용했을 때, 결과값이 참인 값의 리스트를 반환하는 takeWhile 함수를 꼬리 재귀로 작성해 보자.
예를 들어 입력 리스트가 1,2,3,4,5로 구성되어 있을 때, 조건 함수가 3보다 작은 값이면 1과 2로 구성된 리스트를 반환한다.
*/
/*
 list가 비여있거나, condition이 참이 아닐 경우 누적값 반환
 else의 경우 재귀로 조건 함수와, 하나씩 제외되는 리스트, condtion 함수에 참인 값 acc에 추가
 */
private tailrec fun takeWhile(condition: (Int) -> Boolean, list: List<Int>, acc: List<Int> = listOf()): List<Int> = when {
    list.isEmpty() || !condition(list.head()) -> acc
    else -> takeWhile(condition, list.tail(), acc + list.head())
}
fun main() {
    println(takeWhile({ p -> p < 3 }, listOf(1, 2, 3, 4, 5)))   // [1, 2]
}
