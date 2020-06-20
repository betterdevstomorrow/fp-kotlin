package ch04.cha

import java.lang.IllegalArgumentException

private class PartialFunction<P, R>(
        private val condition: (P) -> Boolean,
        private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported")
    }

    fun isDefinedAs(p: P): Boolean = condition(p)

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
            PartialFunction({ this.isDefinedAs(it) || that.isDefinedAs(it) },
                    {
                        when {
                            this.isDefinedAs(it) -> this(it)
                            that.isDefinedAs(it) -> that(it)
                            else -> throw IllegalArgumentException("$it isn't defined")
                        }
                    }
            )

    fun invokeOrElse(p: P, default: R): R = if (isDefinedAs(p)) invoke(p) else default
}

private fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean)
        : PartialFunction<P, R> = PartialFunction(definedAt, this)

fun main() {
    val condition: (Int) -> Boolean = { 0 == it.rem(2) }
    val body: (Int) -> String = { "$it is even" }

    val isEven = body.toPartialFunction(condition)
    val isOdd = { i: Int -> "$i is odd" }.toPartialFunction{ !condition(it) }

    println(listOf(1, 2, 3).map { isEven.invokeOrElse(it, "$it is odd") })    // [1 is odd, 2 is even, 3 is odd]
    println(listOf(1, 2, 3).map { isEven.orElse(isOdd)(it) })    // [1 is odd, 2 is even, 3 is odd]
}

