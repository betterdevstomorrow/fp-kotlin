package ch03.kwon.code

import ch03.kwon.head
import ch03.kwon.tail

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
    println(maximum(list))
}
