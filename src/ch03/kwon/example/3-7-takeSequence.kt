package ch03.kwon.example

import ch03.kwon.code.plus

fun main() {
    require(listOf(3, 3, 3, 3, 3) == takeSequence(5, repeat(3)))
}

private fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when {
    n <= 0 -> listOf()
    else -> listOf(sequence.first()) + takeSequence(n-1, sequence.drop(1))
}

private fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }
