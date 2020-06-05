package ch04.namkung.example
/*
코드 4-13에서 구현한 partialFunction클래스에 invokeOrElse함수와 orElse 함수를 추가해보자, 각 함수의 프로토타입은 다음과 같다.

```kotlin
fun invokeOrElse(p:P, default: R): R
fun orElse(that: PartialFuction<P, R>): PartialFunction<P, R>
```

involkeOrElse 함수는 입력값이 p가 조건에 맞지 않을 때 기본 값 default를 반환한다.

orElse 함수는 partialFunction의 입력값 p가 조건에 맞으면 PartialFuction을 그래로(this) 반환하고, 조건에 맞지 않으면 that를 반환한다.
 */
fun main() {

}
