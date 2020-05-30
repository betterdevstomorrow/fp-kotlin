fun main(args: Array<String>) {
    println(toBinary(6))
}

private fun toBinary(n: Int): String = when {
    n < 2 -> n.toString()
    else -> toBinary(n / 2) + (n % 2).toString()
}

