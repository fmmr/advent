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

    private fun iteration(cave: Caves): Caves {
        return cave.mapIndexed { y, row ->
            row.mapIndexed { x, c -> getNewValue(c, Pos(x, y), cave) }.toCharArray()
        }.toTypedArray()
    }

    private fun getNewValue(c: Char, pos: Pos, cave: Caves): Char {
        val num = pos.neighboorCellsAllEight().filter { it in cave }.map { cave[it] }.groupingBy { it }.eachCount()
        val open = num[OPEN] ?: 0
        val trees = num[TREE] ?: 0
        val lumberyard = num[LUMBERYARD] ?: 0
        val me = cave[pos]
        return if (me.isOpen()) {
            if (trees >= 3) TREE else me
        } else if (me.isTree()) {
            if (lumberyard >= 3) LUMBERYARD else me
        } else {
            if (lumberyard >= 1 && trees >= 1) LUMBERYARD else OPEN
        }

    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    fun init(list: List<String>): Caves = list.map { it.toCharArray() }.toTypedArray()

    private fun Char.isOpen(): Boolean = this == OPEN
    private fun Char.isTree(): Boolean = this == TREE
    private fun Char.isLumberyard(): Boolean = this == LUMBERYARD
}


