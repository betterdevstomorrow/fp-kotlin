package ch06.kim


/**
 *
 * 연습문제 6-1
 *
 * 재귀적 데이터 구조를 활용하여 이진 트리를 만들어 보자. 여기서 이진 트리는 균형잡힌 트리가 아니고 일반적인 트리다. 트리의 정의는 아래와 같다.
 *
 * 힌트 : 모든 노드는 하위 노드가 없거나(EmptyTree) 최대 두개의 하위 노드를 가진다.(Node)
 *       두개의 하위 노드 중, 하나는 왼쪽에 다른 노드는 오른쪽에 있다.
 *       함수의 기본선언은 다음과 같다.
 */


import ch06.kim.Tree.Node;
import ch06.kim.Tree.EmptyTree;


fun main(args: Array<String>) {
    val leftNode = Node( 3, EmptyTree, EmptyTree)

    val myTree = Node( 10, leftNode, EmptyTree)
    println(myTree)
}