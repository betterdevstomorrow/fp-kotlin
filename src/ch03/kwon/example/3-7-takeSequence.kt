package ch03.kwon.example

import ch03.kwon.code.plus
import ch03.kwon.head
import ch03.kwon.tail

fun main() {
    require(listOf(3, 3, 3, 3, 3) == takeSequence(5, repeat(3)))
}

private fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when {
    n <= 0 -> listOf()
    sequence.none() -> listOf() // 빈 시퀀스 종료 조건 추가
    else -> listOf(sequence.head()) + takeSequence(n-1, sequence.tail())
}

private fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }
