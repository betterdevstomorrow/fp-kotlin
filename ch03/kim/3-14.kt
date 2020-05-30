fun main(args: Array<String>) {
    println(toBinary(6))
    println(toBinary(4))
}

private tailrec fun toBinary(n: Int, acc: String = ""): String = when {
    n < 1 -> acc
    else -> toBinary(n / 2, (n % 2).toString() + acc)
}