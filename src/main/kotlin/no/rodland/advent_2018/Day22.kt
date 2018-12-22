package no.rodland.advent_2018

import no.rodland.advent.Pos

object Day22 {
    fun partOne(depth: Int, target: Pos): Int {
        val map = buildMap(depth, target)
        return map.map { ar ->
            ar.map { it.modRes }.sum()
        }.sum()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    fun buildMap(depth: Int, target: Pos): List<Array<Type>> {
        val list = mutableListOf<IntArray>()

        (0..target.y).forEach { y ->
            list.add(IntArray(target.x + 1))
            (0..target.x).forEach { x ->
                val pos = Pos(x, y)
                val geologicIndex = if (pos == Pos(0, 0) || pos == target) {
                    0
                } else if (pos.y == 0) {
                    pos.x * 16807
                } else if (pos.x == 0) {
                    pos.y * 48271
                } else {
                    list[pos.y][pos.x - 1] * list[pos.y - 1][pos.x]
                }
                list[y][x] = (geologicIndex + depth) % 20183
            }
        }

        return list.map { ar ->
            ar.map { typeFromErosionlevel(it) }.toTypedArray()
        }
    }

    enum class Type(val modRes: Int) {
        ROCKY(0), WET(1), NARROW(2)
    }

    fun typeFromErosionlevel(value: Int): Type = Type.values().filter { it.modRes == (value % 3) }.first()


}