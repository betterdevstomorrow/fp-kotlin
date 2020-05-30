package ch03.kwon.code

fun String.head() = first()
fun String.tail() = drop(1)

fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

fun main(args: Array<String>) {
    println(reverse("abcd"))
}
