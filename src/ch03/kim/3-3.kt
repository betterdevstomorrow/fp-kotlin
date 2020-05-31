fun main(args: Array<String>) {
    println(factorial(5))
//    println(reverse("maisy"))
}

fun factorial(n: Int): Int = when {
    n == 1 -> 1
    else -> n * factorial(n - 1)
}


fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}