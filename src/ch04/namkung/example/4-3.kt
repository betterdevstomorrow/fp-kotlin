package ch04.namkung.example

// 두 개의 매개변수를 받아서 큰 값을 반환하는 max 함수를, 커링을 사용할 수 있도록 구현하라

private fun max(i: Int) = { j: Int -> if (i > j) i else j }
fun main() {
    println(max(1)(2))
}
