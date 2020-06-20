package ch04.kim

fun main(args: Array<String>) {
    val target = max(3)
    println(target(5)) // 5
    println(target(1)) //1
}

private fun max(a: Int) = { b: Int -> Math.max(a, b) }