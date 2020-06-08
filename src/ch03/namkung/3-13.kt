package ch03.namkung

// 연습문제 3-2에서 작성한 power함수가 꼬리 재귀인지 확인해 보자, 만약 꼬리 재귀가 아니라면 개선해 보자.
private tailrec fun power(x: Double, n: Int, acc: Double): Double = when(n) {
    0 -> acc
    else -> power(x, n - 1, acc * x)
}
fun main() {
    println(power(5.0, 2, 1.0))   // 25.0
}
