package ch03.kwon.example

import ch03.kwon.head
import ch03.kwon.tail

fun main() {
    require(listOf(1, 2, 3, 4, 5, 6, 7) == quicksort(listOf(5, 3, 7, 6, 2, 1, 4)))
}

private fun quicksort(list: List<Int>): List<Int> = when {
    list.isEmpty() -> list
    else -> {
        val pivot = list.head()
        val (small, bigger) = list.tail().partition { it < pivot }
        quicksort(small) + listOf(pivot) + quicksort(bigger)
    }
}
