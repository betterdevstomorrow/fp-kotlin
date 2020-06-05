package ch03

// 문제 풀기 전에 정의해야 하는 함수들
fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

operator fun <T> Sequence<T>.plus(other: () -> Sequence<T>) = object : Sequence<T> {
    private val thisIterator: Iterator<T> by lazy { this@plus.iterator() }
    private val otherIterator: Iterator<T> by lazy { other().iterator() }
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        override fun next(): T =
                if (thisIterator.hasNext())
                    thisIterator.next()
                else
                    otherIterator.next()

        override fun hasNext(): Boolean = thisIterator.hasNext() || otherIterator.hasNext()
    }
}