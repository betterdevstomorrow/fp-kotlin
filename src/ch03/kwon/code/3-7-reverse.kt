package ch03.kwon.code

import ch03.kwon.head
import ch03.kwon.tail

fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

fun main(args: Array<String>) {
    println(reverse("abcd"))
}
