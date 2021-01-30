package no.rodland.advent_2017

import no.rodland.advent.Direction
import no.rodland.advent.Pos


@Suppress("UNUSED_PARAMETER")
object Day22 {

    var infectedCounter = 0
    fun partOne(list: List<String>, iterations: Int): Int {
        infectedCounter = 0
        val map = list.flatMapIndexed { y, str -> str.mapIndexed { x, c -> Pos(x, y) to c } }.toMap()
        val start = PosDir(Pos(list[0].length / 2, list.size / 2), Direction.NORTH)
        val last = generateSequence(start to map) { acc ->
            acc.burst()
        }
                .drop(1)
                .take(iterations)
//                .forEach { println("${it.first}, $infectedCounter:\n${it.second.printAsGrid(it.first)}") }
                .last()

//        println(last.second.printAsGrid(last.first))
        return infectedCounter
    }

    private fun Pair<PosDir, Map<Pos, Char>>.burst(): Pair<PosDir, Map<Pos, Char>> {
        val (posdir, map) = this
        val (current, dir) = posdir
        val currentInfected = map.getOrDefault(current, '.') == '#'
        val newStatus = !currentInfected
        if (newStatus) {
            infectedCounter++
        }
        val newDir = if (currentInfected) dir.right() else dir.left()
        val newPos = current.next(newDir)
        return PosDir(newPos, newDir) to (map + (current to newStatus.getChar()))
    }

    data class PosDir(val pos: Pos, val dir: Direction)

    fun partTwo(list: List<String>): Int {
        return 2
    }

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




