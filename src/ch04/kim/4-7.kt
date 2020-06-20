package ch04.kim

/**
private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 ->
{ p2: P2 ->
this(p1, p2)
};
}


private tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
0 -> acc
else -> power(x, n - 1, acc * x)
}

private tailrec fun gcd(m: Int, n: Int): Int = when (n) {
0 -> m
else -> gcd(n, m % n)
}

private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
return { gInput: G -> this(g(gInput)) }
}
 */

private fun List<Int>.head() = first()
private fun List<Int>.tail() = drop(1);


private tailrec fun takeWhile(list: List<Int>, acc: List<Int>): List<kotlin.Int> = when {
    list.isEmpty() -> acc
    list.head() < 3 -> takeWhile(list.tail(), acc + listOf(list.head()))
    else -> takeWhile(list.tail(), acc);
}


fun main(args: Array<String>) {

    val curTarget = listOf(5, 1, 0, 76, 32, 1, 2, 3, 4, 5);
    val rst = takeWhile(curTarget, listOf())
    println(rst)
}