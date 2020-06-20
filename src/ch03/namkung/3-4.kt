package ch03.namkung

//10진수 숫자를 입력받아서 2진수 문자열로 변환하는 함수를 작성하라
/*
   10 / 2 = 5  + 10 % 2 = 0
   5 / 2 = 2  + 5 % 2 = 1
   2 / 2 = 1 + 2 % 2 = 0
   1
 */
private fun toBinary(n: Int): String = when {
    n < 2 -> n.toString()
    else -> toBinary(n / 2) + (n % 2).toString()
}

fun main() {
    println(toBinary(10))
}
