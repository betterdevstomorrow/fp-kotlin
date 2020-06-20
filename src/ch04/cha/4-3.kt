package ch04.cha

private fun max(a: Int) = { b: Int -> if (a > b) a else b }

fun main() {
    println(max(10)(20))
    println(max(20)(10))
}