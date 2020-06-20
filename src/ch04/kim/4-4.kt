package ch04.kim

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 ->
    { p2: P2 ->
        this(p1, p2)
    };
}

fun main(args: Array<String>) {
    var min = { a: Int, b: Int -> if (a < b) a else b }
    var curriedMin = min.curried();
    System.out.println(curriedMin(1)(2));
}