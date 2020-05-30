fun main(args: Array<String>) {
    println(takeSequence(7, repeat(3)))
}


fun takeSequence(i: Int, repeat: Sequence<Int>): List<Int> = when {
    i <= 1 -> listOf(repeat.first())
    else -> listOf(repeat.first()) + (takeSequence(i - 1, repeat.drop(1)))
}

fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }

operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }
    override fun iterator() = object : Iterator<T> {
        override fun next(): T =
            if (thisIterator.hasNext())
                thisIterator.next()
            else
                otherIterator.next()

        override fun hasNext(): Boolean =
            thisIterator.hasNext() || otherIterator.hasNext()
    }

}


