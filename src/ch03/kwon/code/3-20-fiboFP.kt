package ch03.kwon.code

import kotlin.system.measureNanoTime

fun main() {
    // 실행 시간 비교
    /*val elapsed1 = measureNanoTime {
        fiboRecursion(100)
    }
    println(elapsed1)*/

    val elapsed2 = measureNanoTime {
        fiboMemoization(10000)
    }
    println(elapsed2)

    val elapsed3 = measureNanoTime {
        fiboFP(10000)
    }
    println(elapsed3)

    val elapsed4 = measureNanoTime {
        fiboFPTail(10000)
    }
    println(elapsed4)

    // 스택 오버플로 발생 확인
    // fiboFP(20000)
    fiboFPTail(50000)
}

private fun fiboRecursion(n: Int): Int {
    return when (n) {
        0 -> 0
        1 -> 1
        else -> fiboRecursion(n - 2) + fiboRecursion(n - 1)
    }
}

// 1. 메모이제이션(memoization): 반복된 연산을 수행할 때 이전에 계산했던 값을 캐싱해서 중복된 연산을 제거하는 방법
private var memo = Array(10001) { -1 }

private fun fiboMemoization(n: Int): Int = when {
    n == 0 -> 0
    n == 1 -> 1
    memo[n] != -1 -> memo[n]    // memo라는 전역변수를 선언함으로써 부수효과가 발생했으며 재귀 함수 내에서 값을 수정하였으므로 불변성을 지키지 못하였다
    else -> {
        memo[n] = fiboMemoization(n - 2) + fiboMemoization(n - 1)
        memo[n]
    }
}

// 함수형 프로그래밍에서의 성능 개선 방법
// 2. 이미 계산된 값을 별도의 메모리에 저장하지 않고, 재귀 함수의 매개변수로 받아서 캐싱을 대신한다
private fun fiboFP(n: Int): Int = fiboFP(n, 0, 1)

private fun fiboFP(n: Int, first: Int, second: Int): Int = when (n) {
    0 -> first
    1 -> second
    else -> fiboFP(n - 1, second, first + second)
}

// 3. 꼬리 재귀(tail recursion) 최적화
// 꼬리 재귀는 마지막에 재귀 호출이 수행된다 (마지막 호출에서 재귀 함수만 호출되어야 한다)
// 재귀가 꼬리 호출인 경우, 컴파일러는 현재 스택 프레임에서 함수의 시작 지점으로 점프하여 재귀 호출을 할 수 있다 (새로운 스택 프레임을 생성 X)
// 일반적인 재귀는 깊이가 깊어지면 스택 오버플로(stack overflow)가 발생할 수 있지만 꼬리 재귀 최적화가 일어나면 스택 오버플로를 방지할 수 있다
private fun fiboFPTail(n: Int): Int = fiboFPTail(n, 0, 1)

private tailrec fun fiboFPTail(n: Int, first: Int, second: Int): Int = when (n) {
    0 -> first
    1 -> second
    else -> fiboFPTail(n - 1, second, first + second)
}
