package no.rodland.advent_2018

import asInt

object Day14 {
    var count = 0  // only for debug

    val scores = sequence {
        count = 0
        var elf1 = 0
        var elf2 = 1
        val list = mutableListOf(3, 7)
        yield(3)
        yield(7)
        while (true) {
            val valElf1 = list[elf1]
            val valElf2 = list[elf2]
            val digits = toListOfDigits(valElf1 + valElf2)
            list.addAll(digits)
            yieldAll(digits)
            elf1 = newPos(valElf1, elf1, list.size)
            elf2 = newPos(valElf2, elf2, list.size)
            count++
        }
    }


    fun partOne(numRec: Int): String {
        val result = scores.drop(numRec).take(10).joinToString("")
        println("found $result index $numRec after $count iterations")
        return result
    }

    fun partTwo(numRec: String): Int {
        val find = numRec.toCharArray().map { it.asInt() }
        while (true) {
            val result = scores.windowed(numRec.length).indexOf(find)
            if (result >= 0) {
                println("found $numRec on index $result after $count iterations")
                return result
            }
        }
    }

    fun newPos(v: Int, current: Int, size: Int): Int {
        return (1 + v + current) % size
    }

    fun toListOfDigits(num: Int): List<Int> {
        return if (num < 10) {
            listOf(num)
        } else {
            listOf(num / 10, num % 10)
        }
    }
}