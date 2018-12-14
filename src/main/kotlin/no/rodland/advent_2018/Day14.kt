package no.rodland.advent_2018

import asInt

object Day14 {
    fun partOne(numRec: Int): String {
        var count = 0
        var elf1 = 0
        var elf2 = 1
        val list = mutableListOf(3, 7)
        while (count++ < (numRec + 10)) {
            val valElf1 = list[elf1]
            val valElf2 = list[elf2]
            list.addAll(toListOfDigits(valElf1 + valElf2))
            elf1 = newPos(valElf1, elf1, list.size)
            elf2 = newPos(valElf2, elf2, list.size)
        }
        return list.subList(numRec, numRec + 10).joinToString("")
    }

    fun partTwo(numRec: String): Int {
        val find = numRec.toCharArray().map { it.asInt() }
        var elf1 = 0
        var elf2 = 1
        val list = mutableListOf(3, 7)
        val innerLoop = 2_000_000

        while (true) {  //   split in two loops - windowing with size==1 for large lists is expensive
            repeat(innerLoop) {
                val valElf1 = list[elf1]
                val valElf2 = list[elf2]
                list.addAll(toListOfDigits(valElf1 + valElf2))
                elf1 = newPos(valElf1, elf1, list.size)
                elf2 = newPos(valElf2, elf2, list.size)
            }
            val result = list.windowed(numRec.length).indexOf(find)
            if (result >= 0) {
                println(list.size)
                return result
            }
            println("generated $innerLoop recipes without getting a match")
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