fun main(args: Array<String>) {
    println(elem(1, listOf(4, 1, 1, 2, 3)))
}


private fun elem(n: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    else -> list.first() == n || elem(n, list.tail())
}

