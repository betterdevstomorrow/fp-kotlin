package ch06.kim

/**
 *
 * 연습문제 6-3
 *
 * 연습문제 6-2 에서 작성한 insert 코드를 100000만번 이상 연속해서 insert 해 보자.
 *
 * 힌트 : 테스트하는 디바이스마다 오류가 발생하는 시기는 다르겠지만 StackOverflowError 가 날 때까지 해보자.
 *
 */
import ch06.kim.Tree.EmptyTree;

fun main(args: Array<String>) {
    var tree1 = EmptyTree.insert(5)
    for (i in 1..100000) {
        val new = tree1.insert(i)
        tree1 = new;
    }
    println(tree1)
}