# 펑터
## 7.1 펑터란
펑터(Functor)는 매핑할 수 있는 것(can be mapped over)이라는 행위를 선언한 타입 클래스를 말한다. 여기서 `매핑할 수 있는 것`이라는 동작은 리스트에서 사용한 map과 동일하다. 코틀린 리스트이 map 함수의 타입을 보면 다음과 같다.

- 코드 7-1 map 함수 타입
```kotlin
fun <T, R> Iterable<T>.map(f: (T) -> R): List<R
```
객체가 가진 T 타입의 값을 f 함수에 적용하여 R 타입의 값을 얻은 후, 이 값을 다시 List 객체 안에 넣는 List<R>을 반환하는 함수다.

- 코드 7-2 타입 한정 map 함수
```kotlin
fun Iterable<Int>.map(f: (Int)-> String): List<String>
```
여기서 f 함수가 입력받은 Int 값을 영어 문자열로 바꾸어 준다고 가정하자. 예를 들어 1이 입력되면 "One"을 반환한다. 그리고 Iterable과 List는 각각 Int와 String 타입을 담을 수 있는 박스라고 가정한다. map 함수의 행위를 비유해서 설명해 보자. Iterable 박스에 담긴 Int 값을 꺼내서 문자열로 반환하고, 변경한 문자열을 다시 List상자에 담아서 반환한다.

- 정리

`펑터는 리스트 같은 컨테이너형 타입의 값을 꺼내서 입력받은 함수를 적용한 후, 함수의 결과값을 컨테이너형 타입에 넣어서 반환하는 행위를 선언한 타입 클래스를`의미한다.
`펑터 자체는 추상화된 타입 클래스이기 때문에 컨테이너형 타입이 가진 구체적인 타입까지 포함해서 정의하지 않는다는 점을 주의해야 한다.`

### 펑터 선언하기
- 코드 7-3 Functor 타입 클래스
```kotlin
interface Functor<Out A> {
  fun <B> fmap(f: A -> B): Functor<B>
}
```
`fmap 함수는 입력받은 f 함수를 사용해서 A값을 B로 변환 후, 펑터에 담아서 Functor<B>를 반환한다.`

## 7.2 메이비 펑터 만들기
메이비(Maybe)는 어떤 값이 있을 수도 있고 없을 수도 있는 컨테이너형 타입이다.

- 코드 7-6 Maybe 펑터
``` kotlin
sealed class Maybe<out A> : Functor<A> {
  abstract override fun toString(): String
  abstract override fun <B> fmap(f: (A) -> B) Maybe<B>
}
```
fmap 함수는 펑터에 정의도니 fmap과 동일하지만, 반환하는 값의 타입만 Maybe로 변경한다.
이렇게 하는 이유는 fmap 함수를 호출한 이후에 함수의 체이닝을 통해서 Maybe에 정의된 함수를 사용할 수 있어야 하기 때문이다.

- 코드 7-7 Just값 생성자
```kotlin
data class Just<out A>(val value: A) Maybe<A>() {
  override fun toString(): String = "Just($value)"
  override fun <B> fmap(f: (A) -> B): Maybe<B> = Just(f(value))
}
```

- 코드 7-8 Nothing 값 생서자 구현
```kotlin
object Nothing : Mabe<kotlin.Nothing>() {
  override fun toString(): String = "Nothing"
  override fun <B> fmap(f: (kotlin.Nothing) -> B): Mayne<b> = Nothing
}
```

- 코드 7-9 Maybe 펑터 사용 예
```kotlin
fun main() {
  println(Just(10).fmap { it + 10}) // "Just(20) 출력
  println(Nothing.fmap( a: Int -> a + 10)) // "Nothing" 출력
}
```
펑터는 타입 생성자에서 컨테이너형 타입을 요구한다. 따라서 어떤 값을 담을 수 있는 타입은 항상 펑터로 만드는 것을 생각해 볼 수 있다.

## 7.3 트리 펑터 만들기
- 트리는 비어 있거나 어떤 값과 두 개의 자식 트리를 가진다.
- 새로운 트리를 만들 수 있다.
- 트리를 화면에 출력할 수 있다.
- 토리의 모든 노드의 값을 변환 함수에 적용한 트리를 만들 수 있다

- 코드 7-10 Tree 펑터
``` kotlin
sealed class Tree<out A> : Functor<A> {
  abstract override fun toString(): String
  abstract override fun <B> fmap(f: (A) -> B): Tree<B>
}
```

- 코드 7-11 EmptyTree 값 생서자
```kotlin
object EmptyTree : Tree<kotlin.Nothing>() {
  override fun toString(): String = "E"
  override fun <B> fmap(f: (Nothing) -> B) : Tree<B> = EmptyTree
}
```

- 코드 7-12 Node 값 생성자
```kotlin
data class Node<out A>(val value: A, val leftTree: Tree<A>, val rightTree: Tree<A>): Tree<A>() {
  override fun toString(): String = "(N $value $leftTree $rightTree)"
  override fun <B> fmap(f: (A) -> B): Tree<B> = Node(f(value), leftTree.fmap(f), rightTree.fmap(f))
}
```
Node 값 생서자는 어떤 값 value와 두 개의 노드 leftTree와 rightTree를 가진다.
fmap은 f 함수를 입력받아서 노드의 값 value에 적용하고, 왼쪽 트리와 오른쪽 트리의 fmap 함수에 f 함수를 넣어 호출함으로써 트리의 모든 노드의 f값에 f 함수가 적용될 수 있도록 한다.

- 코드 7-13 Tree 펑터 사용 예
``` kotlin
fun <T> treeOf(value: T, leftTree: Tree<T> = EmptyTree, rightTree: Tree<T> = EmptyTree): Tree<T> = Node(value, leftTree, rightTree)

fun main() {
  val tree = treeOf(1,
    treeOf(2,
            treeOf(3), treeOf(4)),
    treeOf(5
            treeOf(6), treeOf(7)))

  println(tree) // "(N 1 (N 2 (N 3 E E) (N 4 E E)) (N 5 (N 6 E E) (N 7 E E)))" 출력

  val transformedTree = tree.fmap{ it + 1 }

  println(transformedTree) // "(N 2 (N 3 (N 4 E E) (N 5 E E)) (N 6 (N 7 E E) (N 8 E E)))" 출력
}
```

## 7.4 이터 펑터 만들기
이더는 레퍼트(Left) 또는 라이트(Right) 타입만 허용하는 대수적 타입이다.
함수 호출이 성고하면 올바른 결과를 라이트에 담고, 실패하면 실패 이류에 대한 정보를 레프트로 표시한다.

- 레프트 또는 라이트이고, 모두 컨테이너형 타입으로 값을 포함한다.
- 레프트와 라이트가 포함하는 값의 타입은 다를 수 있다.
- 이더의 라이트의 값을 변경하고, 변경된 값을 가진 이더를 얻을 수 있다. (fmap)

이더는 베이비나 트라이와 달리 타입 매개변수가 두 개다. 레프트에 포함된 값의 타입과 라이트에 포함된 값의 타입이 같을 수도 있고 다를 수도 있기 때문이다.
이더는 일반적으로 라이트 값만 변경할 수 있다. 따라서 레프트 값은 생성되는 시점에 고정된다.

- 코드 7-14 Either 타입 클래스
```kotlin
sealed class Either<out L, out R> : Functor<R> {
  abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>
}
```
fmap의 f 함수는 R에 대해서만 적용되고, L은 변경하지 않는다. Either를 상속한 Left와 Right 값 생성자를 구하면 다음과 같다.

- 코드 7-15 Left, Right 값 생성자
```kotlin
data class Left<out L>(val value: L): Either<L, Nothing>() {
  override fun <R2> fmap(f: (Nothing) -> R2): Either<L, R2> = this
}

data class Right<out R>(val value: R): Either<Nothing, R>() {
  override fun <R2> fmap(f: (R) -> R2): Either<Nothing, R2> = Right(f(value))
}
```
- 코드 7-16 Either 펑터 사용 예
```kotlin
fun main() {
  println(divideTenByN(5)) //"Right(value=2)" 출력
  println(divideTenByN(0)) //"Left(value=divide by zero)" 출력
  println(divideTenByN(5).fmap { r -> r * 2 }) //"Right(value=4)" 출력
  println(divideTenByN(0).fmap { r -> r * 2 }) //"Right(value=devide by zero)" 출력
}

fun divideTenByN(n: Int): Either<String, Int> = try {
  Right(10 / n)
} catch(e: ArithmeticException) {
  Left("divide by zero")
}
```
Funtor의 타입 생성자는 매개변수가 한 개이기 때문에 다른 타입이 다른 두 개 이상의 매개변수를 가지는 타입을 Functor의 인스턴스로 만들기 위해서는 fmap함수에 의해서 변겨오디는 매개변수를 제외한 나머지 값들은 고정해야한다.

## 7.5 단항 함수 펑터 만들기
일급 함수
- 함수를 함수의 매개변수로 넘길 수 있다.
- 함수를 함수의 반환값으로 돌려 줄 수 있다.
- 함수를 변수나 자료구조에 담을 수 있다.

함수형 언어에서는 함수도 Maybe, Tree, Either 처럼 타입이다. 그렇다면 함수도 펑터가 될 수 있지 않을까? 함수를 배핑하면 어떻게 될까? 이번에는 함수를 펑터로 만들어 보면서 어떻게 동작하는지 살펴보자.