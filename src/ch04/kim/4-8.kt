package ch04.kim


private fun Sequence<Int>.head() = first()
private fun Sequence<Int>.tail() = drop(1)


private tailrec fun takeWhile(idx: Int, list: Sequence<Int>, acc: List<Int>): List<kotlin.Int> = when {
    idx < 1 -> acc
    list.head() < 3 -> takeWhile(idx - 1, list.tail(), acc + listOf(list.first()))
    else -> takeWhile(idx - 1, list.tail(), acc);
}


fun main(args: Array<String>) {
    val genSeq = generateSequence(-1) { it + 1 }
    println(takeWhile(5, genSeq, listOf()))
}