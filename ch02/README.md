# 2장. 코틀린으로 함수형 프로그래밍 시작하기
## 2.1. 프로퍼티 선언과 안전한 널 처리
#### 프로퍼티 선언
```kotlin
// 타입을 명시하여 선언
val value: Int = 10     // 읽기 전용 프로퍼티 (~= final 변수 in Java)
var variable: Int = 10  // 가변 프로퍼티 (~= 일반 변수 in Java)

// 타입 추론을 사용하여 선언
val value = 10
var variable = 10
```
#### 안전한 널 처리 (null safety)
`?`을 붙이면 null을 할당 가능.
```kotlin
val nonNull: Int = null     // 컴파일 오류
val nullable: Int? = null
```
## 2.2. 함수와 람다
#### 다양한 함수 선언 방법
```kotlin
/* {} return을 사용하면 반환값 타입 추론을 하지 않기 때문에 항상 반환 타입을 명시.
   반환 타입을 명시하지 않으면 Unit 타입(~= void in Java)을 반환 */
fun twice(value: Int): Int {
	return value * 2
}
fun twice(value: Int): Int = value * 2
fun twice(value: Int) = value * 2

// 매개변수가 2개 인 함수 선언
fun add(x: Int, y: Int): Int = x + y
```
#### 매개변수에 기본값 설정하기
```kotlin
fun add(x: Int, y: Int = 3): Int = x + y

// 호출자가 할당하는법
add(x = 20, y)
```
#### 익명 함수와 람다 표현식
익명 함수(anonymous function)은 함수 이름을 선언하지 않고, 구현부만 작성하는 함수를 말함.
```kotlin
fun sum(x: Int, y: Int, calculate: (Int, Int) -> Int): Int {
	return calculate(x, y)
}

// x, y -> x + y 가 람다식. 람다식에 return이 없으면 마지막 라인의 결과가 반환 됨.
val value = sum(5, 10, {x, y -> x + y})
```
#### 확장 함수 (extension function)
상속을 하거나 내부를 수정하지 않고도 **이미 작성된 클래스에 함수나 프로퍼티를 추가**할 수 있음. 단, 다른 파일에서 사용하려면 `import`를 해주어야 함. ([Kotlin의 Exntention은 어떻게 동작하는가?](https://medium.com/til-kotlin-ko/kotlin%EC%9D%98-extension%EC%9D%80-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%99%EC%9E%91%ED%95%98%EB%8A%94%EA%B0%80-part-1-7badafa7524a))
```kotlin
// 모든 Int 타입에서 product 함수를 사용할 수 있게 됨
fun Int.product(value: Int): Int {
	return this*value
}

10.product(2) // 20
```
## 2.3. 제어 구문
#### if
코틀린에서 if문은 기본적으로 표현식 (~= 삼항연산자 in Java). if 구문으로도 사용가능.
```kotlin
// 구문
if (x > y) {
  max = x
}
// 표현식
max = if (x > y) x else y
```
### when
when문도 표현식.
```kotlin
// 값에 따른 패턴 매칭. Java의 switch와 비슷.
when (x) {
	1 -> print(x)
	2, 3 -> print(2)
	parseInt("4") -> print(4)  // = 4 -> print(4)
	else -> print("else")
}

// 조건문에 따른 분기 처리. 이 때는 (x)를 생략. 결과값으로 프로퍼티에 할당하기 위해서는 반드시 else를 작성할 것.
val numType = when {
	x == 0 -> "zero"
	x > 0 -> "pos"
	else -> "neg"
}
```
### for
```kotlin
val collection = listOf(1,2,3)

for (item in collection) {
	print(item)
}

for ((index, item) in collection.withIndex()) {
	println("the element at $index is $item")
}

// 3 이하
for (i in 1..3) {
	print(i)  // 123
}
// 3 미만
for (i until 1..3) {
	print(i) // 12
}
for (i in 6 downTo 0 step 2) {
	print(i) // 6420
}
```
## 2.4. 인터페이스
코틀린에서 제공하는 인터페이스 특징
- 다중 상속 가능
- 추상 (abstract) 함수 가질 수 있음
- 함수 본문 구현 가능
- 여러 인터페이스에서 같은 이름의 함수를 가질 수 있음
- 추상 프로퍼티 가질 수 있음 (`get()`을 이용하여 초기화)

```kotlin
interface Foo {
	val bar: Int   // 추상 프로퍼티
  		get() = 3

	fun printFoo() // 추상 함수
	fun printk() {
		println("FooK")
	}
}
interface Bar {
	fun printBar() {
		println("Bar")
	}
	fun printk() {
		println("BarK")
	}
}


class A: Foo, Bar {
	override val bar: Int = 3

	override fun printFoo() {}
	override fun printk() {
		super<Foo>.printk()
	}
}
```
## 2.5. 클래스
```kotlin
class User(var name: String, val age: Int = 18)

val user = User("FP", 32)
println(user.name) // FP
user.name="kotlin"
println(user.name) // kotlin
```
### data 클래스
기본적으로 getter, setter 생성해주고, hashCode, equals, toString 함수와 같이 Java Object클래스에 정의된 함수들을 자동으로 생성.
- copy  - 객체의 값을 그대로 복사한 새로운 객체 생성.
- componentN - 객체가 가진 프로퍼티 개수 만큼 호출. (component1 = 객체의 첫번째 프로퍼티)
```kotlin
data class Person(val firstName: String, val lastName: String)
```

### enum 클래스
특정 상수에 이름을 붙여 주는 클래스. 동일한 타입의 프로퍼티와 함수를 가진 이름들을 정의해서 사용 가능. **프로퍼티와 함수가 모두 타입이 동일해야 함**.
```kotlin
enum class Error(val num: Int) {
	WARN(2) {
		override fun getErrorName(): String {
			return "WARN"
		}
	}
	ERROR(3) {
		override fun getErrorName(): String {
			return "ERROR"
		}
	}
	abstract fun getErrorName(): String
}
```

### sealed 클래스
제약 없이 새로운 타입을 확장할 수 있는 클래스. enum class의 확장 형태로, 클래스를 묶은 클래스.

- enum과 달리 각 하위 클래스는 모두 다른 프로퍼티와 함수를 가질 수 있음.
- 단 sealed class와 동일한 파일에서만 선언이 가능.
```kotlin
// Const, Sum, NotANumber 모두 Expr의 하위 클래스
sealed class Expr
data class Const(val number: Double): Expr()
data class Sum(val e1: Expr, val e2: Expr): Expr()
object NotANumber: Expr()
```

## 2.8. 패턴 매칭
값, 조건, 타입 등의 패턴에 따라서 매칭되는 동작을 수행하게 하는 기능.
- when (value) {} - 값에 따른 패턴 매칭
- when {} - 조건에 따른 패턴 매칭

## 2.7. 객체 분해
객체를 구성하는 프로퍼티를 분해하여 편리하게 변수에 할당하는 것.
```koltin
data class User(val name: String, val age: Int)

val user: User = User("kotlin", 28)
val (name, age) = user;

val users = listOf(user);
for ((name, age) in users) {}
```

## 2.8. 컬렉션
함수형 프로그래밍에서는 기본적으로 불변(immutable) 자료구조를 이용함. 가변 자료구조는 `Mutable`을 접두사로 붙인 별도의 클래스로 제공 됨.
#### List
```kotlin
// 불변. 컴파일 오류
val list: List<Int> = listOf(1,2,3);
list.add(4)

// 가변. 정상
val list: MutableList<Int> = listOf(1,2,3);
list.add(4)
```
#### Map
```kotlin
val map1 = mapOf(1 to "One")
val map2 = map1.plus(Pair(2, "Two"));    // map1을 변경하지 않음.
val mutableMap = mutableMapOf(1 to "One");
mutableMap.plus(2, "Two");

println(map1) 				// "{1=One}"
println(map2) 				// "{1=One, 2=Two}"
println(mutableMap)   // "{1=One, 2=Two}"
```

## 2.9 제네릭
객체 내부에서 사용할 데이터 타입을 외부에서 정의하는 기법. 재네릭을 사용하면 클래스를 선언할 때 타입을 확정 짓지 않고, 클래스가 객체화되는 시점에 타입이 결정됨.
```kotlin
class Box<T>(t: T) {
	var value = t
}
```
클래스를 일반화하여 재사용성이 높아짐
```kotlin
// 첫번째 값을 꺼내오는 함수로 어떤 list에도 동작.
fun <T> head(list: List<T>): T {
	if (list.isEmpty()) {
		throw NoSuchElementException()
	}
	return list[0]
}
```

## 2.10. 코틀린 표준 라이브러리
### let
```kotlin
// T의 확장함수
fun <T, R> T.let(block: (T) -> R): R

// 객체가 null인 경우 기본값 설정
val name = user?.let{ it.lastName + it.firstName } ?: "lazysoul"
```

## 2.11. 변성
변성(variance)은 많은 언어에 존재하는 개념으로, 제네릭을 포함한 타입의 계층 관계에서 타입의 가변셩을 처리하는 방식. `"타입 S가 T의 하위 타입일 때, Box[S]가 Box[T]의 하위 타입인가?"`라는 질문에 대한 답이 어떤 변성을 가졌는지 결정함.
- Box[S]와 Box[T]는 상속 관계가 없다 -> 무공변 `<T>`
- Box[S]는 Box[T]의 하위 타입이다 -> 공변 `<out T>`
- Box[T]는 Box[S]의 하위 타입이다 -> 반공변 `<in T>`