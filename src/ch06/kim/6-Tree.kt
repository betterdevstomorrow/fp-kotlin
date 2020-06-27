package ch06.kim


sealed class Tree<out T> {


    data class Node<out T>(val value: T, val left: Tree<T>, val right: Tree<T>) : Tree<T>()
    object EmptyTree : Tree<Nothing>()
}