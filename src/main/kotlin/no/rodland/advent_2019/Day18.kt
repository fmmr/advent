package no.rodland.advent_2019

import no.rodland.advent.Pos

object Day18 {
    fun partOne(list: List<String>): Int {
        val maze = Maze.from(list)
        return maze.minimumSteps()
    }

    // copied from: https://github.com/tginsberg/advent-2019-kotlin/blob/master/src/main/kotlin/com/ginsberg/advent2019/Day18.kt
    class Maze(private val starts: Set<Pos>, private val keys: Map<Pos, Char>, private val doors: Map<Pos, Char>, private val openSpaces: Set<Pos>) {
        private fun findReachableKeys(from: Pos, haveKeys: Set<Char> = mutableSetOf()): Map<Char, Pair<Pos, Int>> {
            val queue = ArrayDeque<Pos>().apply { add(from) }
            val distance = mutableMapOf(from to 0)
            val keyDistance = mutableMapOf<Char, Pair<Pos, Int>>()
            while (queue.isNotEmpty()) {
                val next = queue.removeFirst()
                next.neighboorCellsUDLR()
                        .filter { it in openSpaces }
                        .filterNot { it in distance }
                        .forEach { point: Pos ->
                            distance[point] = distance[next]!! + 1
                            val door = doors[point]
                            val key = keys[point]
                            if (door == null || door.lowercaseChar() in haveKeys) {
                                if (key != null && key !in haveKeys) {
                                    keyDistance[key] = point to distance[point]!!
                                } else {
                                    queue.add(point)
                                }
                            }
                        }
            }
            return keyDistance
        }

        private fun findReachableFromPoints(from: Set<Pos>, haveKeys: Set<Char>): Map<Char, Triple<Pos, Int, Pos>> =
                from.map { point ->
                    findReachableKeys(point, haveKeys).map { entry ->
                        entry.key to Triple(entry.value.first, entry.value.second, point)
                    }
                }.flatten().toMap()

        fun minimumSteps(from: Set<Pos> = starts,
                         haveKeys: Set<Char> = mutableSetOf(),
                         seen: MutableMap<Pair<Set<Pos>, Set<Char>>, Int> = mutableMapOf()): Int {
            val state = Pair(from, haveKeys)

            if (state in seen) return seen.getValue(state)

            val answer = findReachableFromPoints(from, haveKeys).map { entry ->
                val (at, dist, cause) = entry.value
                dist + minimumSteps((from - cause) + at, haveKeys + entry.key, seen)
            }.minOrNull() ?: 0
            seen[state] = answer
            return answer
        }

        companion object {
            fun from(input: List<String>): Maze {
                val starts = mutableSetOf<Pos>()
                val keys = mutableMapOf<Pos, Char>()
                val doors = mutableMapOf<Pos, Char>()
                val openSpaces = mutableSetOf<Pos>()

                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        val place = Pos(x, y)
                        if (c == '@') starts += place
                        if (c != '#') openSpaces += place
                        if (c in ('a'..'z')) keys[place] = c
                        if (c in ('A'..'Z')) doors[place] = c
                    }
                }
                return Maze(starts, keys, doors, openSpaces)
            }
        }
    }
}