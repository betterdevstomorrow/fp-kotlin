package ch05.kim


import ch05.kim.FunList.Cons

fun main(args: Array<String>) {

    val intList =
            Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, FunList.Nil)))))
    println(intList)
}
