package no.rodland.advent_2017

import no.rodland.advent.Direction
import no.rodland.advent.Pos


@Suppress("UNUSED_PARAMETER")
object Day22 {

    fun partOne(list: List<String>, iterations: Int): Int {
        val newStateFunc: (Char) -> Char = { if (it == '#') '.' else '#' }
        val newDirFunc: (Char, Direction) -> Direction = { c, d -> if (c == '#') d.right() else d.left() }
        return runSimulation(list, newStateFunc, newDirFunc, iterations)
    }

    const val CLEANED = '.'
    const val INFECTED = '#'
    const val WEAKENED = 'W'
    const val FLAGGED = 'F'

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
        var i = 1
        val seq = generateSequence(0 to 0) { _ ->
            i to runSimulation(list, newStateFunc, newDirFunc, i++)
        }.drop(1).take(100)
//                .forEach { println(it) }
        return seq.last().second
    }

    private fun runSimulation(list: List<String>, newStateFunc: (Char) -> Char, newDirFunc: (Char, Direction) -> Direction, iterations: Int): Int {
        var infectedCounter = 0
        val map = list.flatMapIndexed { y, str -> str.mapIndexed { x, c -> Pos(x, y) to c } }.toMap()
        val start = PosDir(Pos(list[0].length / 2, list.size / 2), Direction.NORTH)
        val last = generateSequence(start to map) { acc ->
            val (next, didInfect) = acc.burst(newStateFunc, newDirFunc)
            if (didInfect) {
                infectedCounter++
            }
            next
        }
                .drop(1)
                .take(iterations)
                //                .forEach { println("${it.first}, $infectedCounter:\n${it.second.printAsGrid(it.first)}") }
                .last()

//                println(last.second.printAsGrid(last.first))
        return infectedCounter
    }

    private fun Pair<PosDir, Map<Pos, Char>>.burst(newStateFunc: (Char) -> Char, newDirFunc: (Char, Direction) -> Direction): Pair<Pair<PosDir, Map<Pos, Char>>, Boolean> {
        val (posdir, map) = this
        val (current, dir) = posdir
        val currentChar = map.getOrDefault(current, '.')
        val newStatus = newStateFunc(currentChar)
        val didInfect = newStatus == INFECTED
        val newDir = newDirFunc(currentChar, dir)
        val newPos = current.next(newDir)
        return (PosDir(newPos, newDir) to (map + (current to newStatus))) to didInfect
    }

    data class PosDir(val pos: Pos, val dir: Direction)


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

    private fun Boolean.getChar() = if (this) '#' else '.'
}




