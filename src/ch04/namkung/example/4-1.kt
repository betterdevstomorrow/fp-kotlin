package ch04.namkung.example
/*
코드 4-13에서 구현한 partialFunction클래스에 invokeOrElse함수와 orElse 함수를 추가해보자, 각 함수의 프로토타입은 다음과 같다.

```kotlin
fun invokeOrElse(p:P, default: R): R
fun orElse(that: PartialFuction<P, R>): PartialFunction<P, R>
```

invokeOrElse 함수는 입력값이 p가 조건에 맞지 않을 때 기본 값 default를 반환한다.

orElse 함수는 partialFunction의 입력값 p가 조건에 맞으면 PartialFuction을 그래로(this) 반환하고, 조건에 맞지 않으면 that를 반환한다.
 */

private class PartialFunction<P, R> (
        private val condition: (P) -> Boolean,
        private val f: (P) -> R
) : (P) -> R {
    override fun invoke(p: P): R = when {
        condition(p) -> f(p)
        else -> throw IllegalArgumentException("$p isn`t supported.")
    }

    fun isDefinedAt(p: P): Boolean = condition(p)

    fun invokeOrElse(p: P, default: R): R = when {
        condition(p) -> f(p)
        else -> default
    }

    /*
         첫 번째 파라미터 P에서 that, this 모두 참이면, R를 실행 한다.
         R에서 this가 참이면 this를 실행, that이 참이면 that를 실행
         else는 발생할 수 없다.
     */
    fun orElse(that: PartialFunction<P, R>): PartialFunction<P, R> =
            PartialFunction({ it: P -> this.isDefinedAt(it) || that.isDefinedAt(it) },
                    { it: P ->
                        when {
                            this.isDefinedAt(it) -> this(it)
                            that.isDefinedAt(it) -> that(it)
                            else -> throw IllegalArgumentException("$it isn't defined")
                        }
                    }
            )
}

fun main() {
    val condition: (Int) -> Boolean = { it in 1..3 }
    val body: (Int) -> String = {
        when(it) {
            1 -> "One!"
            2 -> "Two!"
            3 -> "Three!"
            else -> throw IllegalArgumentException()
        }
    }

    val oneTwoThree = PartialFunction(condition, body)

    println(oneTwoThree.invokeOrElse(10, "10"))

    if (oneTwoThree.isDefinedAt(3)) {
        print(oneTwoThree(3))
    } else {
        print("isDefinedAt(x) return false")
    }
}



