package ch03.namkung

// 최대공약수를 구하는 gcd 함수를 작성해보자.

private fun gcd(m: Int, n: Int): Int = when {
    n == 0 -> m
    else -> gcd(n, m % n)
}

fun main() {
    println(gcd(12, 18))    // 6
}
