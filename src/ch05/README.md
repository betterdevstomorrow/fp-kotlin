# 컬렉션으로 데이터 다루기
## 5.1 함수형 컬렉션의 데이터 처리
- 코틀린에서 제공하는 컬렉션: List, Set, Array, Set, Sequence,...
- 콤비네이터(Combinator): map, filter

### 컬렉션 데이터 처리 (Linked list 구현)

> sealed class: 제약없이 새로운 타입을 확장할 수 있는 클래스. 클래스를 묶은 클래스. 서로 다른 프로퍼티와 함수를 가진 이름을 가질 수 있다. 
- 순수한 함수형 언어의 리스트는 lazy evaluation 되지만, 스칼라, 코틀린의 리스트는 아래 FunList와 구조가 유사해 **lazy evaluation 하지 않는다** (5.7에서 lazy evaluation 나옴)
```kotlin
sealed class FunList<out T>{
    object Nil: FunList<Nothing>()
    data class Cons<out T>(val head: T, val tail: FunList<T>): FunList<T>()
}
```

> 체이닝 형태의 작성을 지원하기위해서 콤비네이터들을 FunList<T>의 확장함수로 작성하였다. 

#### addHead 구현: 리스트 맨앞에 값을 추가
```kotlin
fun<T> FunList<T>.addHead(value: T): FunList<T> = FunList.Cons(value, this)
```
- 함수형 컬렉션에서 제공하는 함수들은 불변성(immutability)을 지키고 side effect를 없애기 위해 **원본데이터를 변경하지않고 가공된 데이터를 매번 새로 생성하여 반환**한다
```kotlin
val doubleList = 
     Cons(1,
        Cons(2,
           Cons(3, Nil)))

val newDoubleList = doubleList.addHead(40)
println(doubleList)  // [1, 2, 3]
println(newDoubleList) // [40, 1, 2, 3]
```

#### appendTail 구현: 리스트 마지막 값을 추가
```kotlin
fun<T> FunList<T>.appendTail(value: T): FunList<T> = when(this){
   FunList.Nil -> Cons(value, Nil)
   is FunList.Cons -> Cons(head, tail.appendTail(value))
// is FunList.Cons -> this.reverse().addHead(value).reverse(); 이렇게하면안될까
}
```

**이 appendTail 함수는 tailrec이 아니므로 stack에 안전하지않다!**

```kotlin
tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = Nil): FunList<T> = when(this){
    FunList.Nil -> Cons(value, acc).reverse();
    is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
}

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> acc
    is FunList.Cons -> tail.reverse(acc.addHead(head))
}
```

#### getTail 구현: head를 제외한 나머지 리스트를 얻는다
```kotlin
fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}
```

### 5.2 컬렉션 데이터 걸러내기
- 조건에 맞는 데이터만 남기고 제거

1) 명령형 방식: for, while등으로 컬렉션을 순회하면서 if 문으로 걸러낸다

<details><summary>example code</summary>
<p>

```kotlin
fun imperativeFilter(numList: List<Int>): List<Int> {
    val newList = mutableListOf<Int>()
    for (num in numList) {
        if (num % 2 == 0) {
            newList.add(num)
        }
    }
    return newList;
}
```

</p>
</details>

2) 함수형 방식: 필터링 조건이 되는 함수만 넘겨서 실행하고 결과를 반환
```kotlin
fun funtionalFilter(numList: List<Int>): List<Int> = numList.filter { it % 2 == 0 }
```

함수형 방식의 장점
- 코드가 간결해져서 가독성이 좋다.
- 결괏값을 저장하기위한 리스트(ex. newList)가 필요없다.
- 비즈니스 로직에 집중할 수 있다.
- 버그가 발생할 확률이 적다
- 테스트, 유지보수가 용이하다

#### filter 구현
```kotlin
tailrec fun <T> FunList<T>.filter(acc: FunList<T> = FunList.Nil, p: (T) -> Boolean): FunList<T> = when (this) {
    FunList.Nil -> acc.reverse();
    is FunList.Cons -> if (p(head)) tail.filter(acc.addHead(head), p) else tail.filter(acc, p)

//    FunList.Nil -> acc;
//    is FunList.Cons -> if(p(head)) tail.filter(acc.appendTail(head), p) else tail.filter(acc, p)
}

fun main(args: Array<String>) {
    val intList = Cons(11, Cons(210, Cons(21, Cons(14, Nil))))
    val filteredList = intList.filter(FunList.Nil, { it % 2 == 1 })
    require(filteredList == funListOf(11, 21))
}
```

### 5.3 컬렉션 데이터 변경
1) 명령형 방식: for, while등으로 컬렉션을 순회하면서 (변경가능한 컬렉션인 경우) setter로 해당 인덱스값을 직접 변경

<details><summary>example code</summary>
<p>

```kotlin
fun imperativeMap(numList: List<Int>): List<Int> {
    val newList = mutableListOf<Int>()
    for (num in numList) {
        newList.add(num+2)
    }
    return newList;
}
```
- 파라미터로 받은 numList가 mutable이라면 newList를 사용하지않고 바로 수정하는 방법도 있다

</p>
</details>

2) 함수형 방식: map함수를 사용하여 리스트 각 값에 2를 더한다
```kotlin
fun functionalMap(numList: List<Int>): List<Int> {
    return numList.map { it + 2 }
}
```

#### map 구현
```kotlin
tailrec fun <T, R> FunList<T>.map(acc:FunList<R> = Nil, f: (T)-> R): FunList<R> = when(this){
    Nil -> acc.reverse()
    is Cons -> tail.map(acc.addHead(f(head)), f)
}

fun main(args: Array<String>) {
    val intList = funListOf(1, 2, 3)
    require(intList.map { it + 5 } == funListOf(6, 7, 8))
}
```
- map은 리스트의 타입이 변경될 수 있다: 입력 리트의 타입은 `FunList<T>`, 반환되는 리스트 타입은 `FunList<R>`로 일반화함.


### 5.4 컬렉션 데이터 단계별로 줄이기 (reduce)
- 3장에서 했던 재귀를 fold함수로 일반화해보자. 

#### foldLeft 구현: 컬렉션의 값을 왼쪽에서부터 오른쪽으로 줄여나가는 함수
```kotlin
tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    Nil -> acc
    is Cons -> tail.foldLeft(f(acc, head), f)
}

fun main(args: Array<String>) {
    val intList = Cons(2, Cons(10, Cons(1, Cons(2, Nil))))
    val foldedValue = intList.foldLeft(0, { acc, x -> acc + x })
    require(foldedValue == 16)
}
```

- sum()도 foldLeft()를 사용하여 정의할수있다
<details><summary>sum() code example</summary>
<p>

```kotlin
fun FunList<Int>.sum(): Int = foldLeft(0) { acc, x -> acc + x }

fun main(args: Array<String>) {
    val intList = Cons(1, Cons(2, Cons(3, Nil)))
    val resultValue = intList.map { it + 3 }.filter { it % 2 == 0 }.sum()
    require(resultValue == 10)
}
```

</p>
</details>

#### foldRight 구현: 컬렉션의 값을 오른쪽에서부터 줄여나가는 함수
```kotlin
fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    Nil -> acc
    is Cons -> f(head, tail.foldRight(acc, f))
}

fun main(args: Array<String>) {
    val intList = Cons(1, Cons(3, Cons(10, Nil)))
    val resultValue = intList.foldRight(0, { x, acc -> x - acc })
    require(resultValue == 8) // 1 - (3 - (10 - 0))
}
```

#### foldLeft vs foldRight
- foldRight는 꼬리재귀(tailrec)가 아니다! (Stackoverflow가 날 수 있다.)
- foldRight는 acc값을 구하기 위한 f 함수를 lazy evaluation 한다. (끝까지 도달하고나서 연산을 시작한다.)

따라서,
- foldLeft: 리스트가 클때(stack크기 제한없음), f함수 실행 비용이 클때
- foldRight: f함수 실행이 부담없는 일반적인 리스트 변환. 

> foldRight는 언제쓸까?
>- .map() 함수를 직접 구현해보면 foldLeft는 appendTail()을 사용하고, foldRight는 비용이 훨씬 적은 addHead()를 사용해 구현할 수 있다.

### 5.5 여러 컬렉션 데이터 합치기

#### zipWith 구현: 두개의 리스트와 두 리스트가 가진 각 값을 합치는 방법을 기술한 f를 입력으로 받음
```kotlin
private tailrec fun <T1, T2, R> FunList<T1>.zipWith(f: (T1, T2) -> R, list: FunList<T2>,
                                                    acc: FunList<R> = FunList.Nil): FunList<R> = when {
    this === FunList.Nil || list === FunList.Nil -> acc.reverse()
    else -> getTail().zipWith(f, list.getTail(), acc.addHead(f(getHead(), list.getHead())))
}

fun main(args: Array<String>) {
    val intList = Cons(1, Cons(20, Cons(3, Nil)))
    val intList2 = Cons(1, Cons(3, Cons(10, Nil)))
    val concatList = intList.zipWith({ x, y -> Math.max(x, y) }, intList2)
    require(concatList == funListOf(1, 20, 10))
}
```

### 5.6 명령형 vs 함수형 방식 비교
- 코틀린 컬렉션 기본적으로 값이 즉시 평가(eager evaluation)되기때문에 명령형에 비해 성능이 떨어진다.
  (성능에 민감한 경우, 컬렉션의 크기가 고정되어 있지 않은 경우, 컬렉션 크기가 매우 큰 경우 __컬렉션을 사용하면 안된다__)
- lazy evaluation을 위한 컬렉션이 바로 **sequence**다.

- `asSequence()` 사용
```kotlin
private fun functionalWay(intList: List<Int>): Int = intList.map { it * it }.filter { it < 10 }.first()  // 400ms
private fun realFunctionalWay(intList: List<Int>): Int = intList.asSequence().map { it * it }.filter { it < 10 }.first() // 7ms
```

### 5.7 게으른 컬렉션 FunStream
- Cons가 생성되는 시점에 head, tail이 evaluate되지 않는다.
```kotlin
sealed class FunStream<out T> {
    object Nil : FunStream<Nothing>()
    data class Cons<out T>(val head: () -> T, val tail: () -> FunStream<T>) : FunStream<T>();
}
```
- getHead(), getTail() 시 값을 확인할 수 있다.
```kotlin
 println(funStreamOf(1, 2, 3).getHead())   // 1
 println(funStreamOf(1, 2, 3).getTail())   // Cons(head=() -> T, tail=() -> fp.kotlin.example.chapter05.FunStream<T>)
```

#### generateFunStream 구현: FunStream으로 무한대 값 만들기
```kotlin
fun <T> generateFunStream(seed: T, generate: (T) -> T): FunStream<T> =
        Cons({ seed }, { generateFunStream(generate(seed), generate) })

fun main(args: Array<String>) {
    val infiniteVal = generateFunStream(0) { it + 5 }
    infiniteVal.forEach { print(it) }   // 0부터 계속 5씩 증가하는 값을 출력
}
```