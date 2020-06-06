import ch03.plus

fun main(args: Array<String>) {
    println(takeSequence(7, repeat(3)))
}


private fun takeSequence(i: Int, repeat: Sequence<Int>): List<Int> = when {
    i <= 1 -> listOf(repeat.first())
    else -> listOf(repeat.first()) + (takeSequence(i - 1, repeat.drop(1)))
}


