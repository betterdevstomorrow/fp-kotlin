package ch03.namkung

// 연습문제 3-4에서 작성한 toBinary 함수가 꼬리 재귀인지 확인해보자. 만약꼬리 재귀가 아니라면 개선해보자.
private tailrec fun toBinary(n: Int, acc: String): String = when {
    n < 2 -> n.toString() + acc
    else -> {
        val binary = (n % 2).toString() + acc
        toBinary(n / 2, binary)
    }
}
fun main() {
    println(toBinary(10, ""))
}
