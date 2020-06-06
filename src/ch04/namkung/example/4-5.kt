package ch04.namkung.example

// 숫자 (INT)의 리스트를 받아서 최댓값의 제곱을 구하는 함수를 작성해 보자. 이때 반드시 max함수 power 함수를 만들어 합성해야한다.
private val max: (list: List<Int>) -> Int = { list: List<Int> -> list.max()!! }
private val power = { a: Int -> a * a}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    val list2 = listOf(10, 2, 13, 4, 0, 6, 1)

    println(power(max(list)) == 49)
    println(power(max(list2)) == 169)
    println(power(max(list2)) == 1)
}
