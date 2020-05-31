package ch03.kwon

// 확장 함수는 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스 밖에 선언된 함수다
// 확장 함수를 만들려면 추가하려는 함수 이름 앞에 그 함수가 확장할 클래스의 이름을 덧붙인다
fun <T> List<T>.head() = first()
fun <T> List<T>.tail() = drop(1)

// 내부적으로 확장 함수는 수신 객체를 첫 번째 인자로 받는 정적 메소드다
fun <T> head2(list: List<T>) = list.first()
fun <T> tail2(list: List<T>) = list.drop(1)

fun String.head() = first()
fun String.tail() = drop(1)

fun <T> Sequence<T>.head() = first()
fun <T> Sequence<T>.tail() = drop(1)
