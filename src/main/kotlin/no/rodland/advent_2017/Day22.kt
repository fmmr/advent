package no.rodland.advent_2017

import no.rodland.advent.Direction
import no.rodland.advent.Pos


@Suppress("UNUSED_PARAMETER")
object Day22 {
    private const val CLEANED = '.'
    private const val INFECTED = '#'
    private const val WEAKENED = 'W'
    private const val FLAGGED = 'F'

    fun partOne(list: List<String>, iterations: Int): Int {
        val newStateFunc: (Char) -> Char = { if (it == '#') '.' else '#' }
        val newDirFunc: (Char, Direction) -> Direction = { c, d -> if (c == '#') d.right() else d.left() }
        return runSimulation(list, newStateFunc, newDirFunc, iterations)
    }

    fun partTwo(list: List<String>, iterations: Int): Int {
        val newStateFunc: (Char) -> Char = {
            when (it) {
                CLEANED -> WEAKENED
                WEAKENED -> INFECTED
                INFECTED -> FLAGGED
                FLAGGED -> CLEANED
                else -> error("unsupported char: $it")
            }
        }
        val newDirFunc: (Char, Direction) -> Direction = { c, d ->
            when (c) {
                CLEANED -> d.left()
                WEAKENED -> d
                INFECTED -> d.right()
                FLAGGED -> d.goBack()
                else -> error("unsupported char new dir: $c")
            }
        }
        return runSimulation(list, newStateFunc, newDirFunc, iterations)
    }

    private fun runSimulation(list: List<String>, newStateFunc: (Char) -> Char, newDirFunc: (Char, Direction) -> Direction, iterations: Int): Int {
        var infectedCounter = 0
        val map = list.flatMapIndexed { y, str -> str.mapIndexed { x, c -> Pos(x, y) to c } }.toMap().toMutableMap()
        var current = PosDir(Pos(list[0].length / 2, list.size / 2), Direction.NORTH)
        repeat(iterations) {
            val (posDir, didInfect) = map.burst(current, newStateFunc, newDirFunc)
            if (didInfect) {
                infectedCounter++
            }
            current = posDir
        }
        return infectedCounter
    }

    private fun MutableMap<Pos, Char>.burst(posDir: PosDir, newStateFunc: (Char) -> Char, newDirFunc: (Char, Direction) -> Direction): Pair<PosDir, Boolean> {
        val (current, dir) = posDir
        val currentChar = getOrDefault(current, '.')
        val newStatus = newStateFunc(currentChar)
        val newDir = newDirFunc(currentChar, dir)
        this[current] = newStatus
        return PosDir(current.next(newDir), newDir) to (newStatus == INFECTED)
    }

    @Suppress("unused")
    private fun Map<Pos, Char>.printAsGrid(current: PosDir): String {
        val maxX = keys.maxByOrNull { it.x }!!.x
        val minX = keys.minByOrNull { it.x }!!.x
        val maxY = keys.maxByOrNull { it.y }!!.y
        val minY = keys.minByOrNull { it.y }!!.y

        return Array(maxY - minY + 1) { y ->
            CharArray(maxX - minX + 1) { x ->
                val transformedPos = Pos(x + minX, y + minY)
                if (transformedPos == current.pos) {
                    val currentInfected = getOrDefault(transformedPos, '.') == '#'
                    if (currentInfected) 'X' else 'H'
                } else {
                    getOrDefault(transformedPos, '.')
                }
            }
        }.joinToString("", postfix = "\n") { it.joinToString("", postfix = "\n") }
    }

    data class PosDir(val pos: Pos, val dir: Direction)
}




