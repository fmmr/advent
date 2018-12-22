package no.rodland.advent_2018

import no.rodland.advent.Pos
import no.rodland.advent_2018.Day22.Gear.*
import java.util.*

object Day22 {
    fun partOne(depth: Int, target: Pos): Int {
        val map = buildMap(depth, target)
        return map
                .filterIndexed { i, _ ->
                    i <= target.y
                }
                .map { ar ->
                    ar
                            .filterIndexed { i, _ ->
                                i <= target.x
                            }
                            .map { it.modRes }
                            .sum()
                }.sum()
    }

    fun partTwo(depth: Int, target: Pos): Int {
        val map = buildMap(depth, target)

        calcDistances(map)
        return 2
    }


    private fun calcDistances(map: List<Array<Type>>): Path {
        val start = Pos(0, 0)
        val seen: MutableSet<Pos> = mutableSetOf(start)
        val paths: Deque<Pair<Pos, Int>> = ArrayDeque()

//        // Seed the queue with each of our neighbors
//        start.positiveNeighboor()
//                .forEach { paths.add(listOf(it)) }
//
//        // While we still have paths to examine, and haven't found the answer yet...
//        while (paths.isNotEmpty()) {
//            val path: Path = paths.removeFirst()
//            val pathEnd: Pos = path.last()
//
//            // If this is one of our destinations, return it
//            if (pathEnd in map) {
//                return path
//            }
//
//            // If this is a new path, create a set of new paths from it for each of its
//            // cardinal direction (again, in reader order), and add them all back
//            // to the queue.
//            if (pathEnd !in seen) {
//                seen.add(pathEnd)
//                pathEnd.positiveNeighboor()
//                        .filter { caves[it] == '.' }
//                        .filterNot { it in seen }
//                        .forEach { paths.add(path + it) }
//            }
//        }
        return emptyList()
    }


    fun buildMap(depth: Int, target: Pos): List<Array<Type>> {
        val list = mutableListOf<IntArray>()

        (0..(target.y * 2)).forEach { y ->
            list.add(IntArray((target.x * 2) + 1))
            (0..(target.x * 2)).forEach { x ->
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

    enum class Gear {
        CLIMB, TORCH, NEITHER
    }

    enum class Type(val modRes: Int) {
        ROCKY(0), WET(1), NARROW(2);

        fun gears(): List<Gear> {
            return when (this) {
                ROCKY -> listOf(CLIMB, TORCH)
                WET -> listOf(CLIMB, NEITHER)
                NARROW -> listOf(NEITHER, TORCH)
            }
        }

        fun next(toType: Type, gear: Gear): Pair<Gear, Int> {
            return if (this == toType) {
                // no need to include the expensive path-segment here - always cheaper to not change 
                // return listOf(gear to 1, gears().filterNot { it == gear }.first() to 8)
                gear to 1
            } else when (this) {
                ROCKY -> when (toType) {
                    ROCKY -> error("handled above")
                    WET -> when (gear) {
                        CLIMB -> CLIMB to 1
                        TORCH -> CLIMB to 8
                        NEITHER -> error("can't have neither for rocky")
                    }
                    NARROW -> when (gear) {
                        CLIMB -> TORCH to 8
                        TORCH -> TORCH to 1
                        NEITHER -> error("can't have neither for rocky")
                    }
                }
                WET -> when (toType) {
                    ROCKY -> when (gear) {
                        CLIMB -> CLIMB to 1
                        TORCH -> error("can't have torch for wet")
                        NEITHER -> CLIMB to 8
                    }
                    WET -> error("handled above")
                    NARROW -> when (gear) {
                        CLIMB -> NEITHER to 8
                        TORCH -> error("can't have torch for wet")
                        NEITHER -> NEITHER to 1
                    }
                }

                NARROW -> when (toType) {
                    ROCKY -> when (gear) {
                        CLIMB -> error("can't have climb for narrow")
                        TORCH -> TORCH to 1
                        NEITHER -> TORCH to 8
                    }
                    WET -> when (gear) {
                        CLIMB -> error("can't have climb for narrow")
                        TORCH -> NEITHER to 8
                        NEITHER -> NEITHER to 1
                    }
                    NARROW -> error("handled above")
                }
            }

        }
    }

    fun typeFromErosionlevel(value: Int): Type = Type.values().filter { it.modRes == (value % 3) }.first()
}