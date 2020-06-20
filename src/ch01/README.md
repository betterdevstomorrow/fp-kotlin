# 2장. 함수형 프로그래밍이란?
## 1.1 함수형 프로그래밍의 특징
불변성
참조투명성
일급함수
게으른 평가

## 1.2 순수한 함수란 무엇인가?
동일한 입력으로 실행하면 항상 동일한 결과가 나온다.
부수효과가 없다.

```kotlin
// 순수한 함수의 예
fun test (x: Int, y: Int): Int = x + y
```

```kotlin
// 순수하지 못한 함수의 예 (외부 변수 참조)
fun main(){
    println(test(1, 2)) // 13출력
    z = 20
    println(test(1, 2)) // 23출력
}

fun test (x: Int, y: Int): Int = x + y + z
```

```kotlin
// 순수하지 못한 함수의 예 (외부 변수 수정)
fun test (x: Int, y: Int): Int{
    z = y
    return x + y
}
```

## 1.3 부수효과 없는 프로그램 작성하기
#### 공유 변수 수정으로 인한 부수효과
```kotlin
fun main(){
    println(test(1, 2))     // 13출력
    println(test2(10, 20)) // 30출력
    println(test(1, 2))     // 23출력
}

//순수하지 않은 함수
fun test (x: Int, y: Int): Int = x + y + z

//부수효과가 있는 함수
fun test (x: Int, y: Int): Int{
    z = y
    return x + y
}
```

#### 객체의 상태 변경으로 인한 부수효과
```kotlin
//객체의 상태 변경으로 인한 부수효과
data class MutablePerson(var name:String, var age: Int)

//인자로 들어온 객체의 상태를 변경
fun addAge(person: MutablePerson, num: Int){
    person.age += num
}
```

#### 불변 객체로 함수 작성
```kotlin
data class ImmutablePerson(var name:String, var age: Int)

//Person 객체를 수정하지 않고, 새로운 객체를 반환
fun addAge(person: MutablePerson, num: Int){
   return ImmutablePerson(person.name, person.age + num)
}
```

## 1.4 참조 투명성으로 프로그램을 더 안전하게 만들기
참조투명성이란, 프로그램의 변경 없이 어떤 표현식을 값으로 대체할 수 없다는 뜻이다.

#### 참조 투명하지 않은 함수
```kotlin
var someName: String = "Joe"

fun hello1(){
    println("Hello ${someName}")
}

fun hello2(name: String){
    println("Hello ${name}")
}
```

#### 참조 투명한 함수
```kotlin
fun main(){
    val res = test("Joe")
    print(res)
}

fun test(name: String){
    return "Hello ${name}"
}

fun print(str: String){
    println(str)
}
```

## 1.5 일급함수란?

#### 일급 객체
객체를 함수의 매개변수로 넘길 수 있다.
객체를 함수의 반환값으로 돌려 줄 수 있다.
객체를 변수나 자료구조에 담을 수 있다.

```kotlin
//일급 객체를 만족하는 예

//Any를 함수의 매개변수로 넘길 수 있다.
fun test(any: Any){
    // TODO
}

//Any를 함수의 반환값으로 돌려줄 수 있다.
fun test(any: Any){
    return Any()
}

//Any를 List 자료구조에 담을 수 있다.
val anyList: List<Any> = listOf(Any())
```

#### 일급함수
함수를 함수의 매개변수로 넘길 수 있다.
함수를 함수의 반환값으로 돌려줄 수 있다.

```kotlin
//일급 함수를 만족하는 예

//함수를 함수의 매개변수로
fun test(func: (Int) -> String){
    // TODO
}

//함수를 함수의 반환값으로
fun test(func: (Int) -> String){
    return {value -> value.toString()}
}

//함수를 List 자료구조에 담을 수 있다.
val funList: List<(Int) -> String> = listOf({ value -> value.toString()})
```

## 1.6 일급 함수를 이용한 추상화와 재사용성 높이기

#### 명령형 프로그래밍
```kotlin
//명령형 프로그래밍으로 만든 계산기

fun main(args: Array<String>) {
	val cal = SimpleCal()
	println(cal.cal('+' , 3, 1)) //4출력
	println(cal.cal('-' , 3, 1)) //2출력
}

class SimpleCal{
	fun cal(operator: Char, num1: Int, num2: Int): Int = when(operator){
		'+' -> num1+num2
		'-' -> num1-num2
		else -> throw IllegalArgumentException()
	}
}
```

정상적으로 동작하지만 좋은 코드는 아니다. cal함수 하나에 여러가지 기능이 포함되어 있다.

#### 객체지향 프로그래밍
```kotlin
//객체지향 프로그래밍으로 만든 계산기

fun main(args: Array<String>) {
	val plusCal = SimpleCal(Plus())
	val minusCal = SimpleCal(Minus())
	println(plusCal.cal(3, 1)) //4출력
	println(minusCal.cal(3, 1)) //2출력
}

interface Cal{
	fun cal(num1: Int, num2: Int): Int
}

class Plus : Cal{
	override fun cal(num1: Int, num2: Int){
		return num1+num2
	}
}

class Minus : Cal{
	override fun cal(num1: Int, num2: Int){
		return num1-num2
	}
}

class SimpleCal(private val cal: Cal){
	fun cal(num1: Int, num2: Int): Int{
		if(num1 > num2 && 0 != num2){
			return cal.cal(num1, num2)
		} else{
			throw IllegalArgumentException()
		}
	}
}
```

명령형 프로그래밍에 비해 결합도가 낮아지고 응집도는 높아져서 유지보수가 쉬워졌다.

#### 함수형 프로그래밍
```kotlin
//함수형 프로그래밍으로 만든 계산기

fun main(args: Array<String>) {
	val cal = SimpleCal()

	println(cal.cal({n1, n2 -> n1+n2}, 3, 1)) //4출력
	println(cal.cal({n1, n2 -> n1-n2}, 3, 1)) //2출력
}

class SimpleCal{
	fun cal(cal : (Int, Int) -> Int, num1: Int, num2: Int): Int{
		if(num1 > num2 && 0 != num2){
			return cal(num1, num2)
		} else{
			throw IllegalArgumentException()
		}
	}
}
```

일금함수를 사용해 덧셈과 뺄셈 같은 계산기 로직을 추상화했다.
코드가 훨씬 간결해지고 유지보수하기도 더 쉬워졌다.

## 1.7 게으른 평가로 무한 자료구조 만들기
함수형 언어는 기본적으로 값이 필요한 시점에 평가되고 프래그래머가 평가 시점을 지정할 수도 있다.

####  게으른 평가의 특성
```kotlin
val lazyValue: String by lazy{
    println("lazy")
    "hello"
}

fuc main(){
    println(lazyValue)
    println(lazyValue)
}
```

값을 by lazy로 선언하고 매개변수로 람다식을 넘기면, 해당 인스턴스가 호출되는 시점에 람다식이 실행된다. 중요한것은 lazyValue를 선언하는 시점에 람다식을 실행하지 않고, lazyValue가 실제로 호출되는 시점인 println(lazyValue)에서 람다식을 실행한다는 점이다.

####  무한대 값을 자료구조에 담다
```kotlin
val infinite = generateSequence(0) { it + 5}
infinite.take(5).forEach { print("$it") } // "0 5 10 15 20" 출력
```

명령형 언어에서는 일반적으로 무한대 값을 자료구조에 담을 수 없다. 하지만 함수형 언어에서는 게으른 평가라는 특성을 활용해서 무한대 값을 자료구조에 저장할 수 있다.
