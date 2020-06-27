# 함수형 타입 시스템
## 6.1 타입시스템
- 타입 시스템은 런타임에 발생할 수 있는 오류를 컴파일 타임에 발생시킨다. reflection, 타입추론 가능.
- weak type: 프로그래밍의 자유도를 높여준다
  strong type: 많은 오류를 컴파일 타임에 잡아주지만, 언어가 복잡해지고 컴파일이 어렵다.

> 하스켈의 경우 함수 내부에 IO 작업이 있다는 것도 반환값에 명시하여, 타입을 통해서 더 많은 정보를 얻을 수 있도록 설계됨.

## 6.2 대수적 데이터 타입(algebraic data type)
- 대수적 타입: 다른 타입을 결합하여 새로운 타입을 정의. 
- product type, sum type.

#### 곱 타입(product type)
- 두개 이상의 타입을 AND로 결합한 형태. (e.g. tuple, record..)
- `class Circle(name: String, val x: Float, val y: Float, val radius: Float)`: Circle class는 String type, Float type을 AND로 결합한 새로운 타입이다.
- 상속을 받는 클래스를 생성하다보면 계층구조가 복잡해질 수 있고, 유지보수하기 어려워지며 유연성이 떨어진다.

<details><summary>example code</summary>
<p>

```kotlin
open class Shape(val name: String)
class Circle(name: String, val x: Float, val y: Float, val radius: Float) : Shape(name)
class Square(name: String, val x: Float, val y: Float, val length: Float) : Shape(name)
class Line(name: String, val x1: Float, val y1: Float, val x2: Float, val y2: Float) : Shape(name)
```
```kotlin
fun getGirthLength(shape: Shape): Double = when (shape) {
    is Circle -> 2 * Math.PI * shape.radius
    is Square -> 4 * shape.length.toDouble()
    is Line -> {
        val x2 = Math.pow(shape.x2 - shape.x1.toDouble(), 2.0)
        val y2 = Math.pow(shape.y2 - shape.y1.toDouble(), 2.0)
        Math.sqrt(x2 + y2)
    }
    else -> throw java.lang.IllegalArgumentException()
}
```
- Shape를 상속받는 클래스가 얼마나 더 있을지 예측할 수 없으므로 **else 구문이 필요**하다.

</p>
</details>

#### 합 타입(sum type)
- 두개 이상의 타입을 OR로 결합한 형태.
- 가질 수 있는 모든 종류를 열거한다.
- 코틀린은 `sealed class`를 사용해서 합 타입을 만든다.
- `enum`도 sum type 이다. (단, enum은 모든 값의 매개변수 타입이 동일해야한다.)
- 복잡한 상속 구조를 피하면서 확장이 용이한 타입을 정의할 수 있다.
- **생성자 패턴 매칭**을 사용해 타입체크를 할 수 있다.

<details><summary>example code</summary>
<p>

```kotlin
sealed class Shape
data class Circle(val name: String, val x: Float, val y: Float, val radius: Float) : Shape()
data class Square(val name: String, val x: Float, val y: Float, val length: Float) : Shape()
data class Line(val name: String, val x1: Float, val y1: Float, val x2: Float, val y2: Float) : Shape()
```
```kotlin
fun getGirthLength(shape: Shape): Double = when (shape) {
    is Circle -> 2 * Math.PI * shape.radius
    is Square -> 4 * shape.length.toDouble()
    is Line -> {
        val x2 = Math.pow(shape.x2 - shape.x1.toDouble(), 2.0)
        val y2 = Math.pow(shape.y2 - shape.y1.toDouble(), 2.0)
        Math.sqrt(x2 + y2)
    }
}
```
- Shape는 Circle, Square, Line 일 경우 밖에 없다. 따라서 **else가 필요없다**.
- Shape의 생성자는 Circle, Square, Line 이며, **생성자 패턴 매칭**을 사용해 타입체크를 할 수 있다.

</p>
</details>

- **함수형 프로그래밍에서 대수형 타입은 합 타입으로 정의하여 컴파일러가 타입을 예측하기 쉽게 한다**
- 추가적인 else 구문을 작성하지 않아도 되고, 호출하는 곳에서도 예외처리를 할 필요가 없다. (side effect가 없다)
- 새로운 타입이 합 타입에 추가/삭제되면 호출하는 곳에서 컴파일에러가 난다.

## 6.3 타입의 구성요소
#### 타입 변수
- 제네릭으로 선언된 T.
- 함수를 쉽게 일반화할 수 있다.
```kotlin
fun <T> head(list: List<T>): T = list.first()
```
- 다형 함수(polymorphic function): 타입변수를 가진 함수

#### 값 생성자 (value constructor)
- 타입의 값을 반환하는것.
- Shape의 값 생성자는 Circle, Square, Line.
- class나 sealed class에서 값 생성자는 그 자체가 타입으로 사용될 수 있다.
  enum의 값 생성자는 값으로만 사용되고 타입으로 사용될 수 없다.
```kotlin
sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun getSum(): Sum { //Sum을 타입으로 사용할 수 있다
    //...
}
```
```kotlin
enum class Color(val rgb: Int) {
    RED(0x000),
    GREEN(0x111),
    BLUE(0x222)
}

fun getRed(): Color { //Color.RED를 타입으로 사용할 수 없다
    return Color.RED
}
```

#### 타입 생성자(type constructor)와 타입 매개변수(type parameter)
- 타입 생성자: 새로운 타입을 생성하기 위해서 매개변수화 된 타입.
  `sealed class FunList<T>(t:T)`에서 FunList가 타입 생성자, T가 타입 매개변수 
- 구체적인 타입이 되려면 모든 매개변수 가 채워져야 한다. (e.g. FunList<Int> , FunList<String>)
- 타입 매개변수는 값이 주어질 때 타입추론에 의해 결정될 수 있다.
```kotlin
val list1 = FunList<Int> = Cons<Int>(1, Cons<Int>(2, Nil))
val list2 = Cons(1, Cons(2, Nil)) //값의 타입으로 타입 매개변수가 정해진다.
```
- 알반적으로 **타입을 동작시키는데 중요하지 않을 때 타입매개변수를 사용한다**

## 6.4 행위를 가진 타입 정의하기
### 인터페이스(interface), 추상 클래스(abstract class), 트레이트(trait), 믹스인(mixin)
- 인터페이스: 클래스의 기능 명세. 구현부 없음. 다중상속 가능
- 트레이트: 인터페이스와 유사하지만 구현부를 포함한 메서드를 정의할 수 있다. (e.g. kotlin의 interface)
- 추상클래스: 상속관계에서의 추상적인 객체를 나타내기 위해서 사용. 인터페이스나 트레이트와 사용 목적이 다르다.
- 믹스인: 클래스들 간에 어떤 프로퍼티나 메서드를 결합. 다중상속의 모호성(diamond problem)도 해결!

#### 타입 클래스와 타입 클래스의 인스턴스 선언하기
1) Eq 타입 클래스 선언
```kotlin
interface Eq<in T> {
    // 선언
    fun equal(x: T, y: T): Boolean 
    fun notEqual(x: T, y: T): Boolean

    // 구현
    // fun equal(x: T, y: T): Boolean = x == y 
}
```
2) Eq를 인스턴스로 구현
- 타입 클래스가 **선언**만 되어 있다면 인스턴스로 만들 때 override 하여 구현해야한다.
```kotlin
sealed class TrafficLight : Eq<TrafficLight>
object Red : TrafficLignt() {
    override fun equal(x: TrafficLight, y: TrafficLight) = x == y
}
```
- 다른 타입 클래스를 포함한 타입클래스도 정의할 수 있다.
```kotlin
interface Ord<in T> : Eq<T> {
    fun compare(t1: T, t2: T): Int
}
```
- Ord 인스턴스 타입이어도 Eq 함수를 (그대로 또는 재사용하여)사용할 수 있다.

## 6.5 재귀적 자료구조
- 대수적 데이터 타입에서 구성하는 값생성자의 필드에 자신을 포함하는 구조
```kotlin
sealed class FunList<out T>
object Nil : FunList<Nothing>()
data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
```
