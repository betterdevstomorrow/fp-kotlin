package ch03.kwon.example

fun main() {
    require(listOf(5, 5, 5) == replicate(3, 5))
    require(listOf(1, 1, 1, 1, 1) == replicate(5, 1))
}

private fun replicate(n: Int, element: Int): List<Int> = when(n) {
    0 -> listOf()
    else -> listOf(element) + replicate(n-1, element)
}
