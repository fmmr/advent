package no.rodland.advent_2019

import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

object Day22 {
    val NUMBER_OF_CARDS = 119315717514047.toBigInteger()
    val SHUFFLES = 101741582076661.toBigInteger()
    val TWO = 2.toBigInteger()

    fun partOne(list: List<String>, deck: List<Int>): List<Int> {
        return list.fold(deck) { acc, cmd -> doit(cmd, acc) }
    }

    private fun doit(cmd: String, list: List<Int>): List<Int> {
        if (cmd == "deal into new stack") {
            return list.dealNew()
        }
        "cut -(\\d+)".toRegex().matchEntire(cmd)?.let { match ->
            val (precision) = match.destructured
            return list.cutNegative(precision.toInt())
        }
        "cut (\\d+)".toRegex().matchEntire(cmd)?.let { match ->
            val (precision) = match.destructured
            return list.cut(precision.toInt())
        }
        "deal with increment (\\d+)".toRegex().matchEntire(cmd)?.let { match ->
            val (precision) = match.destructured
            return list.dealIncrement(precision.toInt())
        }
        error("unable to parse $cmd")
    }

    fun partTwo(list: List<String>): BigInteger {
        return modularArithmeticVersion(list, 2020.toBigInteger())
    }

    // copied from: https://todd.ginsberg.com/post/advent-of-code/2019/day22/
    private fun modularArithmeticVersion(list: List<String>, find: BigInteger): BigInteger {
        val memory = arrayOf(ONE, ZERO)
        list.reversed().forEach { instruction ->
            when {
                "cut" in instruction ->
                    memory[1] += instruction.getBigInteger()
                "increment" in instruction ->
                    instruction.getBigInteger().modPow(NUMBER_OF_CARDS - TWO, NUMBER_OF_CARDS).also {
                        memory[0] *= it
                        memory[1] *= it
                    }
                "stack" in instruction -> {
                    memory[0] = memory[0].negate()
                    memory[1] = (memory[1].inc()).negate()
                }
            }
            memory[0] %= NUMBER_OF_CARDS
            memory[1] %= NUMBER_OF_CARDS
        }
        val power = memory[0].modPow(SHUFFLES, NUMBER_OF_CARDS)
        return ((power * find) +
            ((memory[1] * (power + NUMBER_OF_CARDS.dec())) *
                ((memory[0].dec()).modPow(NUMBER_OF_CARDS - TWO, NUMBER_OF_CARDS))))
            .mod(NUMBER_OF_CARDS)
    }
}

fun List<Int>.dealNew(): List<Int> = reversed()
fun List<Int>.dealIncrement(i: Int): List<Int> {
    val newList = Array(size) { 0 }
    var count = 0
    forEach { v ->
        newList[count] = v
        count += i
        if (count > size) {
            count -= size
        }
    }
    return newList.toList()
}

private fun String.getBigInteger(): BigInteger = this.split(" ").last().toBigInteger()
fun List<Int>.cut(i: Int): List<Int> = subList(i, size) + subList(0, i)
fun List<Int>.cutNegative(i: Int): List<Int> = subList(size - i, size) + subList(0, size - i)
