package no.rodland.advent_2020

import java.lang.Character.getNumericValue
import java.util.*

// https://en.wikipedia.org/wiki/Shunting-yard_algorithm
object Day18 {
    fun partOne(list: List<String>): Long {
        return list.map { shuntingYard(it) }.map { rpn(it) }.sum()
    }

    fun partTwo(list: List<String>): Long {
        val precendence = mapOf('+' to 1, '*' to 0)
        return list.map { shuntingYard(it, precendence) }.map { rpn(it) }.sum()
    }

    fun rpn(rpn: String): Long {
        // example: 123*+456+*+
        val stack = Stack<Long>()
        var i = 0
        while (i < rpn.length) {
            when (val next = rpn[i++]) {
                '+', '*' -> stack.push(next.calc(stack.pop(), stack.pop()))
                else -> stack.push(next.getNum())
            }
        }
        return stack.pop()
    }

    fun shuntingYard(input: String, precedence: Map<Char, Int> = mapOf('+' to 1, '*' to 1)): String {
        // example: 1 + (2 * 3) + (4 * (5 + 6))
        val str = input.toCharArray().toList()
        val output = mutableListOf<Char>()
        val stack = Stack<Char>()
        str.forEach { c ->
            when (c) {
                ' ' -> {
                }
                '*', '+' -> {
                    while (popStack(stack, c, precedence)) {
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

    private fun popStack(stack: Stack<Char>, c: Char, precedence: Map<Char, Int>) = stack.isNotEmpty() && stack.peek() != '(' && stackHasHigherPrecedence(c, stack.peek(), precedence)

    private fun stackHasHigherPrecedence(next: Char, topOfStack: Char, precedence: Map<Char, Int>) = precedence[topOfStack]!! >= precedence[next]!!

    private fun Char.getNum() = getNumericValue(this).toLong()

    private fun Char.calc(pop: Long, pop1: Long): Long = when (this) {
        '+' -> pop + pop1
        '*' -> pop * pop1
        else -> error("unable to calc $this with $pop and $pop1")
    }
}


