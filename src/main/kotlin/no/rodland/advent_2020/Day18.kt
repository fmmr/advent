package no.rodland.advent_2020

import java.lang.Character.getNumericValue
import java.util.*

@Suppress("UNUSED_PARAMETER")
object Day18 {
    fun partOne(list: List<String>): Long {
        return list.map { shuntingYard(it) }.map { rpn(it) }.sum()
    }

    fun partTwo(list: List<String>): Long {
        val precendence = mapOf('+' to 1, '*' to 0)
        return list.map { shuntingYard(it, precendence) }.map { rpn(it) }.sum()
    }

    //123*+456+*+
    fun rpn(rpn: String): Long {
        val stack = Stack<Long>()
        var i = 0
        while (i < rpn.length) {
            when (val next = rpn[i]) {
                '*' -> {
                    stack.push((stack.pop() * stack.pop()))
                }
                '+' -> {
                    stack.push((stack.pop() + stack.pop()))
                }
                else -> stack.push(next.getNum())
            }
            i++
        }
        return stack.pop()
    }

    // 1 + (2 * 3) + (4 * (5 + 6))
    fun shuntingYard(input: String, precedence: Map<Char, Int> = mapOf('+' to 1, '*' to 1)): String {
        val str = input.toCharArray().toList()
        val output = mutableListOf<Char>()
        val stack = Stack<Char>()
        str.forEach { c ->
            when (c) {
                ' ' -> {
                }
                '*', '+' -> {
                    while (stack.isNotEmpty() && stack.peek() != '(' && stackHasHigherPrecedence(c, stack.peek(), precedence)) {
                        output.add(stack.pop())
                    }
                    stack.push(c)
                }
                '(' -> stack.add(c)
                ')' -> {
                    while (stack.isNotEmpty() && stack.peek() != '(') {
                        output.add(stack.pop())
                    }
                    if (stack.peek() == '(') {
                        stack.pop()
                    }
                }
                else -> output.add(c)
            }
        }
        output.addAll(stack.toList().reversed())
        return output.joinToString("")

    }

    private fun stackHasHigherPrecedence(next: Char, topOfStack: Char, precedence: Map<Char, Int>): Boolean {
        return precedence[topOfStack]!! >= precedence[next]!!
    }

    private fun Char.getNum() = getNumericValue(this).toLong()

}
