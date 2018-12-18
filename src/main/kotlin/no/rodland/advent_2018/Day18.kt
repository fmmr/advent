package no.rodland.advent_2018

import no.rodland.advent.Pos

object Day18 {
    const val TREE = '|'
    const val OPEN = '.'
    const val LUMBERYARD = '#'

    fun partOne(list: List<String>): Int {
        var cave: Caves = init(list)
        repeat(10) {
            cave = iteration(cave)
//            cave.printme()
        }

        val num = cave.flatMap { row ->
            row.map {
                it
            }
        }.groupingBy { it }.eachCount()

        return num[TREE]!! * num[LUMBERYARD]!!
    }


    // thanks to chriswk
    fun partTwo(list: List<String>): Int {
        var cave: Caves = init(list)
        var i = 0
        val map = mutableMapOf<String, Int>()
        map[cave.getSignature()] = 0
        while (true) {
            i++
            cave = iteration(cave)
            val sig = cave.getSignature()
            if (map.containsKey(sig)) {
                val previous = map[sig]!!
                val period = i - previous
                val rem = 1_000_000_000 - i
                val target = rem % period + previous
                val fasit = map.filterValues { v -> v == target }.keys.first()
                val num = fasit.groupingBy { it }.eachCount()
                println("found sig at round $previous with period $period $num")

                return (num[TREE] ?: 0) * (num[LUMBERYARD] ?: 0)
            } else {
                map[sig] = i
            }
        }
    }

    private fun iteration(cave: Caves): Caves {
        return cave.mapIndexed { y, row ->
            row.mapIndexed { x, c -> getNewValue(c, Pos(x, y), cave) }.toCharArray()
        }.toTypedArray()
    }

    private fun getNewValue(c: Char, pos: Pos, cave: Caves): Char {
        val num = pos.neighboorCellsAllEight().filter { it in cave }.map { cave[it] }.groupingBy { it }.eachCount()
        val trees = num[TREE] ?: 0
        val lumberyard = num[LUMBERYARD] ?: 0
        return with(c) {
            if (isOpen()) {
                if (trees >= 3) TREE else this
            } else if (isTree()) {
                if (lumberyard >= 3) LUMBERYARD else this
            } else {
                if (lumberyard >= 1 && trees >= 1) LUMBERYARD else OPEN
            }
        }
    }

    fun init(list: List<String>): Caves = list.map { it.toCharArray() }.toTypedArray()

    private fun Char.isOpen(): Boolean = this == OPEN
    private fun Char.isTree(): Boolean = this == TREE

    fun partTwoTrialAndError(list: List<String>): Int {
        var cave: Caves = init(list)
        // 508 - 536
        val recurringList = (1..536).map {
            cave = iteration(cave)
            val num = cave.flatMap { row ->
                row.map {
                    it
                }
            }.groupingBy { it }.eachCount()
            (num[TREE] ?: -1) * (num[LUMBERYARD] ?: -1)
        }.takeLast(28)

        val seq = sequence {
            while (true) {
                yieldAll(recurringList)
            }
        }

        val offset = 4
        val message = seq.drop(1000000000 - 1 - offset).take(1).first()

        return message
    }

}

private fun Array<CharArray>.getSignature(): String = map { row -> row.contentToString() }.joinToString("")


