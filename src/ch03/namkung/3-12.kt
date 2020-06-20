package ch03.namkung

// 연습문제 3-11에서 작성한 factorial 함수가 꼬리 재귀인지 확인해보자. 만약 꼬리재귀가 아니라면 최적화되도록 수정하자.
private tailrec fun factorial(input: Int, acc: Int): Int = when(input) {
    0 -> acc
    else -> factorial(input - 1, input * acc)
}
fun main() {
    println(factorial(5, 1))   // 120
}
