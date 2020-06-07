package ch04.namkung.example
//연습문제 4-5에서 작성한 함수를 compose 함수를 사용해서 다시 작성해 보자.

private val max: (list: List<Int>) -> Int = { list: List<Int> -> list.max()!! }
private val power = { a: Int -> a * a }
private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { gInput: G -> this(g(gInput))}
}

private  fun composeMaxPoser(list: List<Int>) : Int = (power compose max)(list)
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    val list2 = listOf(10, 2, 13, 4, 0, 6, 1)

    println(composeMaxPoser(list) == 49)
    println(composeMaxPoser(list2) == 169)
    println(composeMaxPoser(list2) == 1)
}
