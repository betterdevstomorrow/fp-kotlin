package ch04.kim

import java.lang.IllegalArgumentException

fun main(args: Array<String>) {

    val cond1: (Int) -> Boolean = { it in 1..3 }
    val body1: (Int) -> String = {
        when (it) {
            1 -> "one"
            2 -> "two"
            3 -> "three"
            else -> throw IllegalArgumentException("THIS IS DEFAULT CATCHER")
        }
    };

    val cond2: (Int) -> Boolean = { it in 3..6 }
    val body2: (Int) -> String = {
        "wow " + it.toString()
    }

    val onetwothree = PartialFunction(cond1, body1);
    val conditionPartial = PartialFunction(cond2, body2);

    val test2 = conditionPartial.orElse(onetwothree)(3);
    println(test2) // wow 3
    val test3 = conditionPartial.orElse(onetwothree)(1);
    println(test3) // one
    val test1 = conditionPartial.orElse(onetwothree)(50);
    print(test1) // catch Error (THIS IS DEFAULT...)
}

class PartialFunction<P, R>(
        private val condition: (P) -> Boolean,
        private val f: (P) -> R
) : (P) -> R {

    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn't supported.")
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(
            p: P,
            default: R
    ): R = when {
        condition(p) -> f(p)
        else -> default
    }

    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
            PartialFunction({ it: P -> true },
                    { it: P ->
                        if (this.isDefinedAt(it))
                            f(it)
                        else
                            that.f(it)
                    })


}

