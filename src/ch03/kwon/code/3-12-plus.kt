package ch03.kwon.code

operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }
    override fun iterator() = object : Iterator<T> {
        override fun next(): T =
                if (thisIterator.hasNext())
                    thisIterator.next()
                else
                    otherIterator.next()

        override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
    }
}
