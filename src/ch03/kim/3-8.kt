fun main(args: Array<String>) {
//    println(zip(listOf(1, 3, 5), listOf(2, 4)))

    val target = listOf(5, 100, 23, 2, 3)
    println(quicksort(target))
}

fun zip(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> = when {
    list1.isEmpty() || list2.isEmpty() -> listOf()
    else -> listOf(Pair(list1.head(), list2.head())) + zip(list1.tail(), list2.tail())
}

private fun quicksort(elem: List<Int>): List<Int> = when {
    elem.isEmpty() -> listOf()
    else -> {
        val pivot = elem.head()
        val (list1, list2) = elem.partition { it < pivot }
        quicksort(list1) + listOf(pivot) + quicksort(list2.tail())
    }
}


//private fun quicksort(list: List<Int>): List<Int> = when {
//    list.isEmpty() -> list
//    else -> {
//        val pivot = list.head()
//        val (small, bigger) = list.tail().partition { it < pivot }
//        quicksort(small) + listOf(pivot) + quicksort(bigger)
//    }
//}

