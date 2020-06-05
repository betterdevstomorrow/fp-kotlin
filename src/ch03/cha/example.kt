import ch03.plus

fun main() {
    println("연습문제 3-2 : " + power(3.0,3))
    println("연습문제 3-3 : " + factorial(4))
    println("연습문제 3-4 : " + toBinary(11))
    println("연습문제 3-5 : " + replicate(3, 5))
    println("연습문제 3-6 : " + elem(3, listOf(1)))
    println("연습문제 3-7 : " + takeSequence(5, repeat(3)))
    println("연습문제 3-8 : " + quicksort(listOf(5,3,4,9,20,1,2)))
    println("연습문제 3-9 : " + gcd(192, 72))
    println("연습문제 3-10: " + factorialMemo(4))
    println("연습문제 3-11: " + factorialFP(4))
    println("연습문제 3-12: " + factorialTail(4))
    println("연습문제 3-13: " + powerTail(3.0, 3))
    println("연습문제 3-14 : " + toBinaryTail(11))
    println("연습문제 3-15 : " + replicateTail(3, 5))
}

fun repeat(n: Int): Sequence<Int> = sequenceOf(n) + { repeat(n) }

// 연습문제 3-2
private fun power(x: Double, n: Int): Double = when (n) {
    1 -> 1.0
    else -> x * power(x, n-1)
}

// 연습문제 3-3
private fun factorial(n: Int): Int = when (n) {
    0 -> 1
    else -> n * factorial(n-1)
}

// 연습문제 3-4
private fun toBinary(n: Int): String = when(n) {
    0 -> "0"
    1 -> "1"
    else -> toBinary(n/2) + (n%2).toString()
}

// 연습문제 3-5
private fun replicate(n: Int, element: Int): List<Int> = when {
    n < 1 -> listOf()
    else -> replicate(n-1, element) + listOf(element)
}

// 연습문제 3-6
private fun elem(num: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    num == list.first() -> true
    else -> elem(num, list.tail())
}

// 연습문제 3-7
private fun takeSequence(n: Int, sequences: Sequence<Int>): List<Int> = when {
    n <= 0 -> listOf()
    sequences.none() -> listOf()
    else -> listOf(sequences.first()) + takeSequence(n-1, sequences.drop(1))
}

// 연습문제 3-8
private fun quicksort(list: List<Int>): List<Int> = when {
    list.size <= 1 -> list
    else -> {
        val pivot = list.first()
        val (less, greater) = list.partition { it < list.first() }
        quicksort(less) + listOf(pivot) + quicksort(greater.drop(1))
    }
}

// 연습문제 3-9 (유클리드 호제법) m > n
private fun gcd(m: Int, n: Int): Int = when {
    m % n == 0 -> n
    else -> gcd(n, m % n)
}

var factoCache = Array(100) { -1 }
// 연습문제 3-10
fun factorialMemo(n: Int): Int = when {
    n == 0 -> 1
    factoCache[n] != -1 -> factoCache[n]
    else -> {
        factoCache[n] = n * factorialMemo(n-1)
        factoCache[n]
    }
}

// (Failed) 연습문제 3-11
fun factorialFP(n: Int, acc: Int = 1): Int = when (n) {
    0 -> acc
    else -> factorialFP(n-1, n * acc)
}

// 연습문제 3-12
tailrec fun factorialTail(n: Int, acc: Int = 1): Int = when (n) {
    0 -> acc
    else -> factorialTail(n-1, n * acc)
}

// 연습문제 3-13
tailrec fun powerTail(x: Double, n: Int, acc: Double = x): Double = when (n) {
    1 -> acc
    else -> powerTail(x, n-1, acc * x)
}

// 연습문제 3-14
tailrec fun toBinaryTail(n: Int, b: String = ""): String = when(n) {
    0 -> b
    1 -> "1".plus(b)
    else -> toBinaryTail(n/2, (n%2).toString() + b)
}

// 연습문제 3-15
tailrec fun replicateTail(n: Int, element: Int, list: List<Int> = listOf()): List<Int> = when (n) {
    0 -> list
    else -> replicateTail(n-1, element, list + listOf(element))
}
