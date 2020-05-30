package ch03.kwon.code

// 확장 함수는 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스 밖에 선언된 함수다
// 확장 함수를 만들려면 추가하려는 함수 이름 앞에 그 함수가 확장할 클래스의 이름을 덧붙인다
fun List<Int>.head() = this.first() // this 생략 가능
fun List<Int>.tail() = this.drop(1) // this 생략 가능

// 내부적으로 확장 함수는 수신 객체를 첫 번째 인자로 받는 정적 메소드다
fun head2(list: List<Int>): Int = list.first()
fun tail2(list: List<Int>): List<Int> = list.drop(1)


// maximum 함수는 입력 리스트를 items로 받아 리스트 내의 최댓값을 반환한다
fun maximum(items: List<Int>): Int = when {
    // 리스트가 비어 있을 경우,
    // 최댓값을 구할 수 없기 때문에 오류 발생 후 종료
    items.isEmpty() -> error("empty list")

    // 리스트에 구성요소가 한 개인 경우,
    // 그 값이 최댓값
    1 == items.size -> items[0]

    // 리스트에 구성요소가 여러 갱인 경우,
    // 리스트의 첫 번째 값이 나머지 값들의 최댓값보다 크다면 첫 번째 값이 최댓값
    // 나머지 값들의 최댓값이 첫 번째 값보다 크다면 나머지 값들의 최댓값이 입력 리스트의 최댓값
    else -> {
        val head = items.head()
        val tail = items.tail()
        var maxVal = maximum(tail)
        if (head > maxVal) head else maxVal
    }
}

fun main(args: Array<String>) {
    var list: List<Int> = listOf(1, 3, 2, 8, 4)
    println(list.first())
    println(list.tail())

    println(head2(list))
    println(tail2(list))

    println(maximum(list))
}
