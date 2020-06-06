fun main(args: Array<String>) {
    println(replicate(3, 5))
}

private fun replicate(n: Int, element: Int): List<Int> = when {
    n == 1 -> listOf(element)
    else -> listOf(element) + replicate(n - 1, element)
}