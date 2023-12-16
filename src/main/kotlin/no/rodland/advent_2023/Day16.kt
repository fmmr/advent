package no.rodland.advent_2023

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Day
import no.rodland.advent.Direction
import no.rodland.advent.Pos

// template generated: 16/12/2023
// Fredrik Rødland 2023

class Day16(val input: List<String>) : Day<Int, Int, Array<Array<Char>>> {

    private val parsed = input.parse()
    private val width = parsed[0].size
    private val height = parsed.size
    override fun partOne(): Int {
        return traverse(Pos(0, 0), Direction.EAST).keys.distinct().size
    }

    override fun partTwo(): Int {
        return bruteForceAll().max()
    }

    private fun bruteForceAll(): List<Int> = runBlocking(Dispatchers.Default) {
        val topBottom = parsed[0].indices.flatMap { x ->
            listOf(
                async { traverse(Pos(x, 0), Direction.SOUTH).keys.distinct().size },
                async { traverse(Pos(x, height - 1), Direction.NORTH).keys.distinct().size },
            )
        }
        val leftRight = parsed.indices.flatMap { y ->
            listOf(
                async { traverse(Pos(0, y), Direction.EAST).keys.distinct().size },
                async { traverse(Pos(width - 1, y), Direction.WEST).keys.distinct().size },
            )
        }
        (topBottom + leftRight).awaitAll()
    }

    private fun traverse(pos: Pos, dir: Direction, seen: MutableSet<Pair<Pos, Direction>> = mutableSetOf()): Map<Pos, Direction> {
        if (pos !in parsed) {
            return emptyMap()
        }
        val added = seen.add(pos to dir)
        if (!added) {
            return emptyMap()
        }
        val c = parsed[pos]
        val next = when (dir) {
            Direction.NORTH -> when (c) {
                '/' -> traverse(pos.next(Direction.EAST), Direction.EAST, seen)
                '\\' -> traverse(pos.next(Direction.WEST), Direction.WEST, seen)
                '.', '|' -> traverse(pos.next(Direction.NORTH), Direction.NORTH, seen)
                '-' -> traverse(pos.next(Direction.WEST), Direction.WEST, seen) + traverse(pos.next(Direction.EAST), Direction.EAST, seen)
                else -> error("Unknown char: $c")
            }

            Direction.SOUTH -> when (c) {
                '/' -> traverse(pos.next(Direction.WEST), Direction.WEST, seen)
                '\\' -> traverse(pos.next(Direction.EAST), Direction.EAST, seen)
                '.', '|' -> traverse(pos.next(Direction.SOUTH), Direction.SOUTH, seen)
                '-' -> traverse(pos.next(Direction.WEST), Direction.WEST, seen) + traverse(pos.next(Direction.EAST), Direction.EAST, seen)
                else -> error("Unknown char: $c")
            }

            Direction.WEST -> when (c) {
                '/' -> traverse(pos.next(Direction.SOUTH), Direction.SOUTH, seen)
                '\\' -> traverse(pos.next(Direction.NORTH), Direction.NORTH, seen)
                '|' -> traverse(pos.next(Direction.SOUTH), Direction.SOUTH, seen) + traverse(pos.next(Direction.NORTH), Direction.NORTH, seen)
                '.', '-' -> traverse(pos.next(Direction.WEST), Direction.WEST, seen)
                else -> error("Unknown char: $c")
            }

            Direction.EAST -> when (c) {
                '/' -> traverse(pos.next(Direction.NORTH), Direction.NORTH, seen)
                '\\' -> traverse(pos.next(Direction.SOUTH), Direction.SOUTH, seen)
                '|' -> traverse(pos.next(Direction.SOUTH), Direction.SOUTH, seen) + traverse(pos.next(Direction.NORTH), Direction.NORTH, seen)
                '.', '-' -> traverse(pos.next(Direction.EAST), Direction.EAST, seen)
                else -> error("Unknown char: $c")
            }
        }
        return mapOf(pos to dir) + next
    }

    operator fun Array<Array<Char>>.contains(pos: Pos): Boolean = pos.x in 0..<width && pos.y in 0..<height
    operator fun Array<Array<Char>>.get(pos: Pos): Char = this[pos.y][pos.x]

    override fun List<String>.parse(): Array<Array<Char>> {
        return mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                c
            }.toTypedArray()
        }.toTypedArray()
    }

    override val day = "16".toInt()
}
