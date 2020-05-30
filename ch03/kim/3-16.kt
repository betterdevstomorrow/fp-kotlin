fun main(args: Array<String>) {
    println(elem(2, listOf(4, 1, 1, 2, 3)))
    println(elem(11, listOf(4, 1, 1, 2, 3)))
}


//private tailrec fun elem(n: Int, list: List<Int>, acc: Boolean = false): Boolean = when {
//    acc -> acc
//    list.isEmpty() -> false
//    else -> elem(n, list.tail(), list.first() == n)
//}


//better solution
private tailrec fun elem(element: Int, list: List<Int>): Boolean = when {
    list.isEmpty() -> false
    element == list.head() -> true
    else -> elem(element, list.tail())
}