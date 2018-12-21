package no.rodland.advent_2017

import java.util.*

object Day9 {
    fun partOne(input: String): Int {
        val onlyGroups = cleanGroups(input)
        var value = 0
        var sum = 0
        onlyGroups.forEach {
            if (it == '{') {
                value += 1
                sum += value
            } else {
                value -= 1
            }
        }
        println("current value: $value, sum: $sum")
        return sum
    }

    private fun cleanGroups(input: String): String {
        println(input)
        var isGarbage = false
        var cancelNext = false
        val stack: ArrayDeque<Int> = ArrayDeque()
        var count = 0
        val onlyGroups = input.mapNotNull {
            if (cancelNext) {
                cancelNext = false
                null
            } else if (it == '!') {
                cancelNext = true
                null
            } else if (isGarbage) {
                if (it == '>') {
                    isGarbage = false
                }
                null
            } else if (it == '<') {
                isGarbage = true
                null
            } else if (!"{}".contains(it)) {
                null

            } else {
                it
            }
        }.joinToString("")
        println(onlyGroups)
        return onlyGroups
    }

    fun partTwo(input: String): Int {
        return 2
    }
}