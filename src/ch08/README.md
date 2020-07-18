# 애플리케이티브 펑터
## 8.1. 애플리케이티브 펑터란?
함수를 가진 펑터는 또 다른 펑터의 값을 적용해야 할 때, 컨텍스트 안에서 처리하는 것이 불가능 함. 이 한계를 극복하기 위해 만들어진 것이 **애플리케이티브** 펑터임.

애플리케이티브 펑터는 첫 번쨰 상자에 담겨있는 함수와 두 번째 상자에 담겨있는 값을 꺼내서 매핑하고, 다시 상자 안에 넣어서 반환함.
```kotlin
// infix 키워드는 함수의 두 매개 변수 P1, P2를 P1 apply P2와 같이 호출할 수 있도록 해줌.
interface Applicative<A, B>: Functor<(A) -> B> {
  fun <V> pure(value: V): Applicative<V>
  infix fun <B> apply(ff: Applicative<(A) -> B>): Applicative<B>
}

```
```kotlin
// 펑터의 한계 (컴파일 오류)
Just(5).fmap(Just({ x -> x * 2}))

// 애플리케이티브 펑터
AJust(5) apply AJust({ x -> x * 2})
AJust(5).apply(AJust({ x -> x * 2}))
```
## 8.2. 애플리케이티브 펑터 법칙
- 항등(Identitiy) 법칙: `pure(identitiy) apply af = af`
- 합성(Composition) 법칙: `pure(compose) apply af1 apply af2 apply af3 = af1 apply (af2 apply af3)`
- 준동형 사상(homomorphism) 법칙: `pure(function) apply af = af.fmap(function)`
- 교환 (interchange) 법칙: `af apply pure(x) = pure(of(x)) apply af`
