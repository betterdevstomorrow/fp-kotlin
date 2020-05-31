fun main(args: Array<String>) {
    println(gcd(12, 21))
    println(12 % 21)
}

//유클리드 호제법
//fun gcd(m: Int, n: Int): Int {
//    val a = if (m > n) m else n;
//    val b = if (m > n) n else m;
//
//    return when {
//        a % b == 0 -> b
//        else -> {
//            val na = b;
//            val nb = a % b;
//            gcd(na, nb)
//        }
//    }
//}

private fun gcd(m: Int, n: Int): Int = when (n) {
    0 -> m
    else -> gcd(n, m % n)
}