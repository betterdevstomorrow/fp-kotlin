package ch03.namkung

/*
숫자를 두 개 입력받은 후 두 번째 숫자를 첫 번째 숫자만큼 가지고 있는 리스트를 반환하는 함수를 만들어 보자.
예를 들어 replicate(3, 5)를 입력하면 5가 3개 있는 리스트[5, 5, 5]를 반환한다.
*/

private fun replicate(n: Int, element: Int): List<Int> = when {
    n <= 0 -> listOf()
    else -> listOf(element) + replicate(n - 1, element)
}

fun main() {
    println(replicate(3, 5))
}
