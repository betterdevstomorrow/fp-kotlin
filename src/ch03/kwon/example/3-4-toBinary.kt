package ch03.kwon.example

fun main() {
    require("1010" == toBinary(10))
    require("11011" == toBinary(27))
    require("11111111" == toBinary(255))
}

private fun toBinary(n: Int): String = when {
    n < 2 -> n.toString()
    else -> toBinary(n/2) + (n%2).toString()
}
