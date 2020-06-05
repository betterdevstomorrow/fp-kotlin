# 고차함수
## 4.1 고차 함수란
다음 두 가지 조건 중 하나 이상을 만족하는 함수를 고차함수(higher order function)라 한다.
- 함수를 매개변수로 받는 함수
- 함수를 반환하는 함수

*함수형 언어에서 문제를 해결할 때는 받드시 고차 함수를 사용해아한다*??

### 고차 함수 조건을 만족하는 예
```kotlin
fun higherOrderFunction1(func: () -> Unit): Unit {
  func()
}

fun higherOrderFunction2(): () -> Unit {
  return { println("Hello, Kotlin")}
}
```

higherOrderFunction1은 매개변수로 함수를 전달 받고, higherOrderFunction2는 함수를 반환하므로 두 함수 모두 고차함수이다.

### 코드의 재사용성을 높인다
- 상속을 사옹해서 작성한 계산기
```kotlin
fun main(args: Array<String>) {
  val calcSum = Sum()
  val calcMinus = Minus()
  val calcProduct = Product()

  println(calcSum.calc(1, 5))
  println(calcMinus.calc(1, 5))
  println(calcProduct.calc(1, 5))
}

interface Calcable {
  fun calc(x: Int, y: Int): Int
}

class Sum : Calcable {
  override fun calc(x: Int, y: Int): Int {
    return x + y
  }
}

class Minus : Calcable {
  override fun calc(x: Int, y: Int): Int {
    return x - y
  }
}

class Product : Calcable {
  override fun calc(x: Int, y: Int): Int {
    return x * y
  }
}
```

- 고차 함수를 사용해서 작성한 계산기
```kotlin
fun main(args: Array<String>) {
  val sum: (Int, Int) -> Int = { x, y -> x + y}
  val minus: (Int, Int) -> Int = { x, y -> x - y}
  val product: (Int, Int) -> Int = { x, y -> x * y}
}

fun higherOrder(func: (Int, Int) -> Int, x: Int, y: Int): Int = func(x, y)
```
higherOrder는 함수를 매개변수로 받고 있으므로 고차 함수이다. 매개변수로 받은 함수는 오직 타입으로만 일반회되어 있다. 비즈니스 로직은 호출자로부터 주입받는다.

### 기능의 확장이 쉽다
- 상속을 사용한 계산기에 TwiceSum 기능 추가
```kotlin
fun main(args: Array<String>) {
  val calcTwiceSum = TwiceSum()
  println(calcTwiceSum.calc(8, 2))
}

class TwiceSum : Calcable {
  override fun calc(x: Int, y: Int): Int {
    return (x + y) * 2
  }
}
```

- 고차 함수를 사용한 계산기에 TwiceSum 기능 추가
```kotlin
fun main(args: Array<String>) {
  val twiceSum: (Int, Int) -> Int = { x, y -> (x + y) * 2 }
  println(higherOrder(twiceSum, 8, 2))
}
```
### 코드를 간결하게 작성할 수 있다.
입력 리스트의 값들을 두 배해서 10보다 큰 수의 리스트를 반환하는 예제

- 명령형 프로그래밍으로 작성한 예
```kotlin
val over10Values: ArrayList<Int> = ArrayList()
for (i in 0 until ints.size) {
  val twiceInt = ints[i] * 2
  if (twiceInt > 10) {
    over10Values.add(twiceInt)
  }
}

println(over10Values)
```

- 컬렉션 API와 고차 함수를 활용해서 작성한 예
```kotlin
val ints: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val result = ints.map{ value -> value * 2 }.filter{ value -> value > 10 }

println(result)
```

- 람다 표현식에 it을 사용한 예
```kotlin
val result = ints.map{ it * 2 }.filter{ it > 10 }

println(result)
```

## 4.2 부분 함수
일반적으로 허용되지 않는 읿력값으로 함수를 호출하면 예외를 던지거나, 비정상적인 상황을 알려 주는 값을 반환한다.
함수형 언어에서는 부분 함수를 사용해서 이 문제를 해결할 수 있다.

*부분 함수란 모든 가능한 입력 중, 일부 입력에 대한 결과만 정의한 함수*

### 부분 함수의 예
다음 twice와 partialTwice 함수는 입력값을 두 배한 결과를 반환한다.

- partialTwice 함수
```kotlin
fun twice(x: Int) = x * 2

fun partialTwice(x: Int) = Int =
  if (x < 100) {
    x * 2
  } else {
    throw IllegalArgumentExcpetion()
  }
```
twice 함수는 모든 입력을 두 배하지만, partialTwice 함수는 입력값이 100보다 작은 경우에만 두 배 한다. 여기서 particalTwice 함수의 x값은 twice함수의 x값의 부분 집합니다.
따라서 partialTwice는 tiwce의 부분 함수다.


sayNumber1 함수는 입력받은 숫자 x를 문자열로 바꾸어 준다.
- sayNumber1 함수
```kotlin
fun sayNumber1(x: Int): String = when (x) {
  1 -> "One!"
  2 -> "Two!"
  3 -> "Three!"
  else -> "Not between 1 and 3"
}
```
sayNumber1 함수는 모든 입력값에 대한 결과를 정의했다. 따라서 sayNumber1 함수는 부분 함수가 아니다.

- sayNumber2
```kotlin
fun sayNumber2(x: Int): String = when(x) {
  1 -> "One!"
  2 -> "Two!"
  3 -> "Three!"
  else -> throw IllegalArgumentException()
}
```
sayNumber2 함수는 입력값 1,2,3에 대한 결과만 정의했다. 이 외의 값이 들어오면 예외가 발생한다. 따라서 sayNumber2는 부분 함수다.

### 부분 함수 만들기
- PartialFunction 클래스
```kotlin
class PartialFunction<P, R> (
  private val condition: (P) -> Boolean,
  private val f: (P) -> R
) : (P) -> R {
  override fun invoke(p: P): R = when {
    condition(p) -> f(p)
    else -> throw IllegalArgumentException("$p isn`t supported.")
  }

  fun isDefinedAt(p: P): Boolean = condition(p)
}
```
PartialFunction의 생성자는 입력값을 확인하는 함수 condition과, 조건에 만족했을 때 수행할 함수 f를 매개변수로 받는다. [invoke](http://joshskeen.com/kotlins-invoke-operator/) 함수의 입력값 p가 condition 함수에 정의된 조건에 맞을 때만 f 함수가 실행되고, 조건에 맞지 않으면 예외를 발생시킨다.

- PartialFunction을 사용한 oneTwoThree 부분 함수
```kotlin
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
if (oneTwoThree.isDefinedAt(3)) {
  print(oneTwoThree(3))
} else {
  print("isDefinedAt(x) return false")
}
```

- PartialFunction를 사용한 isEven 부분 함수
```kotlin
val isEven = PartialFunction<Int, String>({ it % 2 == 0}, { "$it is even" })

if (isEven.isDefinedAt(100)) {
  print(isEven(100))
} else {
  print("isDefinedAt(x) return false")
}
```

- toPartialFuction 함수
```kotlin
fun <P, R> ((P) -> R).toPartialFuction(definedAt: (P) -> Boolean)
        : PartialFunction<P, R> = PartialFunction(definedAt, this)
```

- toPartialFuction 함수 사용 예
```kotlin
val condition: (Int) -> Boolean = { it.rem(2) == 0 }
val body: (Int) -> String = { "$it is even" }

val isEven = body.toPartialFuction(condtion)

if (isEven.isDefinedAt(100)) {
  print(isEven(100))
} else {
  print("isDefinedAt(x) return false")
}
```
[rem](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/rem.html)

### 부분 함수의 필요성
- 호출하는 쪽에서 호출하기 전에 함수가 정상적으로 동작하는지 미리 확인할 수 있다.
- 호출자가 함수가 던지는 예외나 오류값에 대해서 몰라도 된다.
- 부분 함수의 조합으로 부분 함수 자체를 재사용할 수도 있고, 확장할 수도 있다.


## 4.3 부분 적용 함수
부분 적용 함수는 부분 함수와 이름이 비슷하지만 관계는 없다.

*매개변수의 일부만 전달할 수도 있고 아예 전달하지 않을 수도 있다. 이렇게 매개변수의 일부만 전달받았을 때, 제공받는 매개변수만 가지고 부분 적용 함수를 생성한다.*

- partial1, partial2 함수
```kotlin
fun <P1, P2, P3>((P1, P2) -> R).partial1(p1: P1): (P2) -> R {
  return { p2 -> this(p1, p2)}
}

fun <P1, P2, P3>((P1, P2) -> R).partial1(p2: P2): (P1) -> R {
  return { p1 -> this(p1, p2)}
}
```

매개변수 두 개를 받아서 값을 반환하는 함수 (P1, P2) -> )의 확장 함수 partial1, partial2를 만들었다. partical(p1: P1) 함수는 첫 번째 매개변수 p1만 받아서 적용하고 (P2) -> R 함수를 반환하다. 이때 (P2) -> R 함수는 첫 번째 매개 변수만 적용된 적용 함수다. partial2는 partial1과 반대로 p2만 받아서 적용하고 (P1) -> R 함수를 반환한다.

- partial1, partial2 함수를 사용해 부분 적용 함수 생성하기
```kotlin
fun main(args: Array<String>) {
  val func = { a: String, b:String -> a + b}

  val partiallyAppliedFunc1 = func.partial1("Hello")
  val result = partiallyAppliedFunc1("World")

  println(result)

  val partiallyAppliedFunc2 = func.partial2("World")
  val result2 = partiallyAppliedFunc2("Hello")

  println(result2)
}
```
partiallyAppliedFunc1은 값으로 평가되지 않고, 남은 매개변수를 받아서 결과를 반환하는 함수의 참조만 가지고 있다. partiallyAppliedFunc1은 첫 번째 매개변수만 적용된 부분 적용 함수다. 여기에 두 번째 매개변수에 "World"를 넣어서 호출하면 "Hello World"가 출력된다. partiallyAppliedFunc2 경우도 원리는 동일하다.

## 4.4 커링 함수
*커링(currying)이란 여러 개의 매개변수를 받는 함수를 분리하여, 단일 매개변수를 받는 부분 적용 함수의 체인으로 만드는 방법이다.*

- 3개 인자를 받는 multiThree 함수
```kotlin
fun mutiThree(a: Int, b: Int, c: Int): Int = a * b * c
```

- 부분 적용 함수를 반환하는 multiThree함수
```kotlin
fun mutiThree(a: Int) = { b: Int -> { c: Int -> a * b * c }}
```

- multiThree 함수를 사용 예
```kotlin
fun main(args: Array<String>) {
  println(mutiThree(1, 2, 3))

  val partial1 = mutiThree(1)
  val partial2 = partial1(2)
  val partial3 = partial2(3)

  println(partial3)

  println(mutiThree(1, 2, 3))
}
```

커링의 장점중 하나는 이런 부분 적용 함수를 다양하게 재사용할 수 있다는 점이다. 또한 마지막 매개변수가 입력될 때까지 함수 실행을 늦출 수 있다.

### 코틀린용 커링 함수 추상화하기
- curried, uncurried 함수
```kotlin
fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

fun <P1, P2, R> ((P1) -> (P2) -> (P3) -> R).uncurried(): (P1, P2, p3) -> R = { p1: P2, p2: P2, p3: P3 -> this(p1)(p2)(p3) }
```

- curried, uncurried 함수 사용 예
```kotlin
fun main(args: Array<String>) {
  val multiThree = { a: Int, b: Int, c: Int -> a * b * c}
  val curried = multiThree.curried()
  println(curried(1)(2)(3))

  val uncurried = curried.uncurried()
  println(uncurried(1, 2, 3))
}
```

## 4.5 합성 함수
*함성 함수란 함수를 매개변수로 받고, ㅎ마수를 반환할 수 있는 고차 함수를 이용해 서 두 개의 함수를 결합하는 것*

- 함수 함성의 예
```
fun main(args: Array<String>) {
  println(composed(3))
}

fun composed(i: Int) = addThree(twice(1))
fun addThree(i: Int) = i + 3
fun twice(i: Int) = i * 2
```

### 함수 합성 일반화하기
- compose 함수
```kotlin
fun main(args: Array<String>) {
  val addThree = { i: Int -> i + 3 }
  val twice = { i: Int -> i * 3 }
  val composedFunc = addThree compose twice
  println(composedFunc(3))
}

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
  return { gIntput: G -> this(g(gInput))}
}
```
[infix](https://kotlinlang.org/docs/reference/functions.html#infix-notation)

addThree compose tiwce로 합성 함수가 만들어지면, 실제 실행은 뒤에서 부터 된다는 점에 유의하자. 먼저 tiwce가 실행되고, 반환 값이 addThree의 입력으로 들어간다.

### 포인트 프리 스타일 프로그래밍
- 매개변수를 사용한 합성 예
```kotlin
val absolute = { i: List<Int> -> i.map { it -> abs(it) } }
val negative = { i: List<Int> -> i.map { it -> -it } }
val minimum = { i: List<Int> -> i.min() }

minimun(negative(absolute(listOf(3, -1, 5, -2, -4, 8, 14))))
```

- compose 함수를 사용한 합성 예
```kotlin
val composed = minimun compose negative compose absolute
val result2 = composed(listOf(3, -1, 5, -2, -4, 8, -14))
println(result2)
```
*함수 합성을 사용해서 매개변수나 타입 선언 없이 함수를 만드는 방식을 포인트 프리 스타일(point free style)프로그맹이라 한다.*

### 하나 이상의 매개변수를 받는 함수의 함성
두 개의 값을 받아서 각 값을 제곱한 값의 최대공약수를 구하는 합성 함수를 만들어 볼 것이다.
- gcd
```kotlin
tailrec fun gcd(m: int, n: Int): Int = when(n) {
  0 -> m
  else -> gcd(n, m % n)
}
```
- power
```kotlin
tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when(n) {
  0 -> acc
  else -> power(x, n - 1, x * acc)
}
```

- gcdPowerOfTwo 함수
```kotlin
fun main(args: Array<String>) {
  val powerOfTwo = { x: Int -> power(x.toDouble(), 2).toInt() }
  val gcdPowerOfTwo = { x1: Int, x2: Int -> gcd(powerOfTwo(x1), powerOfTwo(x2))}

  println(gcdPowerOfTwo(25, 5)) // 25출력
}
```

compose로 변경, *compose 함수는 매개변수가 한 개인 함수*에서만 동작할 수 있다.
입력 매개변수가 여러개인 함수를 입력 매개변수가 한 개인 함수의 체인으로 변경해야한다. gcd함수를 입력 매개변수가 한 개인 함수의 체인으로 분리하고, compose를 사용해서 제곱 함수와 합성해보자.

- composedGcdPowerOfTwo1 함수
```kotlin
fun main(args: Array<String>) {
  val curriedGcd1 = :: gcd.curried()
  val composedGcdPowerOfTwo1 = curriedGcd1 compose powerOfTwo

  println(composedGcdPowerOfTwo1(25)(5)) // 5출력
}
```

잘못된 값인 5가 출력된다. 함수 합성은 입력 매개변수가 한 개인 함수로 체이닝되어야 한다. 그러나 입력이 두 개인 함수를 커링을 사용해 분리했기 때문에 powerOfTwo 함수의 결과값은 첫번째 매개변수에만 반영되고, 두 번째 매개변수까지는 전달되지 않았다.


- composedGcdPowerOfTwo2 함수
```kotlin
fun main(args: Array<String>) {
  val curriedGcd2 = { m: Int, n: Int -> gcd(m, powerOfTwo(n)) }.curried()
  val composedGcdPowerOfTwo2 = curriedGcd2 compose powerOfTwo

  println(composedGcdPowerOfTwo2(25)(5)) // 25출력
}
```

*여러개의 매개변수에 동일한 함수를 적용해야 할 때, 함수 합성을 사용하는 것은 적합하지 않다. 이 경우 power함수와 같이 일반적인 고차 함수로 연결하는 것이 좋다.*


## 4.6 실전응용
### zipWith함수
zipWith는 한 개의 함수와 두 개의 리스트를 입력으로 받은 후 두 개의 리스트값을 입력받은 함수에 적용하고 합쳐진 리스트를 반환하는 함수다.

```kotlin
tailrec fun <P1, P2, R> zipWith(func: (P1, P2) -> R, List1: List<P>, list2: List<P>, acc: List<R> = listOf()) : List<R> = when {
  list1.isEmpty() || list2.isEmpty() -> acc
  else -> {
    val zipList = acc + listOf(func(list1.head(), list2.head()))
    zipWith(func, list1.tail(), list2.tail(), zipList)
  }
}
```

- list.head, list.tail
3장 3-6 maximun 함수에서 정의
```kotlin
fun List<Int>.head() = first()
fun List<Int>.tail() = drop(1)
```

- zipWith 함수 사용한 예
```kotlin
fun main(args: Array<String>) {
  val list1 = listOf(6, 3, 2, 1, 4)
  val list2 = listOf(7, 4, 2, 6, 3)

  val add = { p1: Int, p2: Int -> p1 + p2}
  val result1 = zipWith(add, list1, list2)
  println(reuslt1)

  val max = { p1: Int, p2: Int -> max(p1, p2)}
  val result2 = zipWith(max, list1, list2)
  println(result2)

  val stract = { p1: String, p2: String -> p1 + p2 }
  val result3 = zipWith(strac, listOf("a", "b"), listOf("c", "d"))
  println(result3)

  val product = { p1: Int, p2: Int -> p1 * p2 }
  val result4 = zipWith(product, replicate(3, 5), (1..5).toList())
  println(result4)
}
```

- replicate 함수 정의
```kotlin
fun <T> replicate(n:Int, x:T): List<T> {
   return List(n) { x }
}
```

함수형 프로그래밍은 이와 같이 코드를 작성할 때 자주 사용되는 패펀을 추상화하기 위해 고차 함수를 사용한다.

###콜백 리스너를 고차 함수로 대체하기
- 콜백 리스너를 중첩해서 사용한 예
```kotlin
fun main(args: Array<String>) {
  val result = object : Callback1 {
    override fun callBack(x1: String): Callback2 {
      override fun callback(x2: String): Callback3 {
        return object : CallBack3 {
          override fun callBack(x3: String): CallBack4 {
            return object : CallBack4 {
              override fun callBack(x4: String): CallBack5 {
                return object : CallBack5 {
                  override fun callback(x5: String): String {
                    return x1 + x2 + x3 + x4 + x5
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  println(result
    .callBack("1")
    .callBack("2")
    .callBack("3")
    .callBack("4")
    .callBack("5")) // "12345" 출력
}

interface CallBack1 {
  fun callBack(x: String): CallBack2
}

interface CallBack2 {
  fun callBack(x: String): CallBack3
}

interface CallBack3 {
  fun callBack(x: String): CallBack4
}

interface CallBack4 {
  fun callBack(x: String): CallBack5
}

interface CallBack5 {
  fun callBack(x: String): String
}
```

- 고차 함수와 커링을 사용해서 개선한 예
```kotlin
fun main(args: Array<String>) {
  val result = callback("1")("2")("3")("4")("5")

  println(reuslt) // "12345" 출력
}

val callback: (String) -> (String) -> (String) -> (String) -> (String) -> String = {
  v1 -> {
    v2 -> {
      v3 -> {
        v4 -> {
          v5 ->
            v1 + v2 + v3 + v4 + v5
        }
      }
    }
  }
}

val partialApplied = callBack("prefix")(":")
println(partialApplied("1")("2")("3")) // "prefix:123" 출력
println(partialApplied("a")("b")("c")) // "prefix:abc" 출력
```