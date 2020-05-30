fun main(args: Array<String>) {
    println(replicate(3, 5))
}

private tailrec fun replicate(n: Int, element: Int, acc: List<Int> = listOf()): List<Int> = when {
    n == 0 -> acc
    else -> replicate(n - 1, element, acc + listOf(element))
}