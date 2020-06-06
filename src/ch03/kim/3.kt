fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)

fun String.head() = first()
fun String.tail() = drop(1)

sealed class Bounce<A>
data class Done<A>(val result: A) : Bounce<A>()
data class More<A>(val thunk: () -> Bounce<A>) : Bounce<A>()

tailrec fun <A> trampoline(bounce: Bounce<A>): A = when (bounce) {
    is Done -> bounce.result
    is More -> trampoline(bounce.thunk())
}