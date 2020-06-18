# 1장. 함수형 프로그래밍이란
## 1.1 함수형 프로그래밍의 특징
### 함수형 프로그래밍 핵심 개념
- 불변성 (immutable)
  - 함수형 프로그래밍에서는 일단 만들어지고 나면 내부 상태가 절대로 바뀌지 않는 불변 객체를 사용해 프로그램을 작성한다
- 참조 투명성 (referential transparency) 
- 일급 함수 (first-class-function)
  - 함수를 일반 값처럼 다룰 수 있다. 함수를 변수에 저장할 수 있고, 함수를 인자로 다른 함수에 전달할 수 있으며, 함수에서 새로운 함수를 만들어서 반환할 수 있다
- 게으른 평가 (lazy evaluation)
- 순수 함수 (pure function)
  - 순수 함수는 입력이 같으면 항상 같은 결과를 돌려준다
  - 부수효과 (side effect) 없음: 다른 객체의 상태를 변경하지 않으며, 함수 외부나 다른 바깥 환경과 상호작용하지 않는다

### 함수형 프로그래밍 이점
- 간결성
  - 코드의 복잡도가 낮아 간결한 코드를 만들 수 있다
- 다중 스레드를 사용해도 안전하다
  - 불변 데이터 구조를 사용하여 다중 스레드 환경에서 공유 자원이 변경될 걱정이 없어 복잡한 동기화를 적용하지 않아도 된다
- 테스트하기 쉽다

## 1.2 순수한 함수란 무엇인가
- 동일 입력 동일 출력
  - 순수 함수는 입력이 같으면 항상 같은 결과를 돌려준다
- 부수효과 없는 코드
  - 다른 객체의 상태를 변경하지 않으며, 함수 외부나 다른 바깥 환경과 상호작용하지 않는다

```
// 순수한 함수
fun pureFunction(x: Int, y: Int): Int = x + y

// 순수하지 않은 함수 - 외부 변수 참조
var z = 10
fun impureFunction(x: Int, y: Int): Int = x + y + z

// 부수 효과가 있는 함수 - 외부 변수 수정
fun withSideEffect(x: Int, y: Int): Int {
    z = y
    return x + y
}

// 부수 효과가 있는 함수 - 객체의 상태 변경에 의한 부수효과
data class MutablePerson(var name: String, var age: Int)
fun addAge(person: MutablePerson, num: Int) {
    person.age += num   // 인자로 들어온 객체의 상태를 변경
}

// 순수한 함수 - 불변 객체로 함수 작성하기
data class ImmutablePerson(val name: String, val age: Int)  // 객체 속성을 val로 선언

fun addAge(person: ImmutablePerson, num: Int): ImmutablePerson {
    return ImmutablePerson(person.name, person.age + num)   // Person 객체를 수정하지 않고, 새로운 객체를 생성하여 반환
}
```
