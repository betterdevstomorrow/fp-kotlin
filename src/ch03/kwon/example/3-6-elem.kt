package ch03.kwon.example

fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun main() {
    require(elem(5, listOf(1, 3, 5)))
    require(!elem(5, listOf(1, 3, 7)))
}

private fun elem(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    num == list.head() -> true
    else -> elem(num, list.tail())
}
