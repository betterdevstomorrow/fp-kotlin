package ch03.namkung

// 퀵정렬 알고리즘의 quicksort 함수를 작성해 보자.
// 문제 풀기 전에 정의해야 하는 함수들
private fun List<Int>.head() = first()
private fun List<Int>.tail() = drop(1)

/*
partition
주어진 함수로 리스트를 한쌍으로 구분한다.
https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/partition.html
it < pivot
pivot 값보다 작은 리스트와, 큰 리스트
*/

private fun quicksort(list: List<Int>): List<Int> = when {
    list.isEmpty() -> list
    else -> {
        val pivot = list.head()
        val (small, bigger) = list.tail().partition { it < pivot }
        quicksort(small) + listOf(pivot) + quicksort(bigger)
    }
}
fun main() {
    println(quicksort(listOf(5, 3, 7, 6, 2, 1, 4)))   // [1, 2, 3, 4, 5, 6, 7]
}
