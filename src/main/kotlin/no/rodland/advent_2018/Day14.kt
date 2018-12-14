package no.rodland.advent_2018

object Day14 {
    fun partOne(numRec: Int): String {
        var count = 0
        var elf1 = 0
        var elf2 = 1
        val list = mutableListOf<Int>(3, 7)
        while (count++ < (numRec + 10)) {
            val valElf1 = list[elf1]
            val valElf2 = list[elf2]
            list.addAll(toListOfDigits(valElf1 + valElf2))
            elf1 = newPos(valElf1, elf1, list.size)
            elf2 = newPos(valElf2, elf2, list.size)
        }
        return list.subList(numRec, numRec + 10).joinToString("")
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    fun newPos(v: Int, current: Int, size: Int): Int {
        val index = 1 + v + current
        return if (index > size - 1) {
            (index - size) % size
        } else {
            index
        }
    }

    fun toListOfDigits(num: Int): List<Int> {
        return if (num < 10) {
            listOf(num)
        } else {
            listOf(num / 10, num % 10)
        }
    }
}