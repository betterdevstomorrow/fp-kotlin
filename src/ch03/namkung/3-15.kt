package ch03.namkung

// 연슴분제 3-5에서 작성한 replicate 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리 재귀가 아니라면 개선해보자.
private tailrec fun replicate(n: Int, element: Int, acc: List<Int>): List<Int> = when {
    n <= 0 -> acc
    else -> replicate(n - 1, element, listOf(element) + acc)
}

fun main() {
    println(replicate(3, 5, listOf()))
}
