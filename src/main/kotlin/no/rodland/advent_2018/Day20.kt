package no.rodland.advent_2018

import no.rodland.advent.Pos
import java.util.*

object Day20 {
    fun partOne(re: String): Int {
        val map = parse(re, Pos(0, 0))
        return map.maxBy { it.value }?.value ?: -1
    }

    fun parse(input: String, start: Pos = Pos(0, 0)): MutableMap<Pos, Int> {
        val grid = mutableMapOf(start to 0)
        val stack = ArrayDeque<Pos>()
        var current = start
        input.forEach {
            when (it) {
                '(' -> stack.push(current)
                ')' -> current = stack.pop()
                '|' -> current = stack.peek()
                '^' -> println("start")// do nothing
                '$' -> println("end")// do nothing
                else -> {
                    val nextDistance = grid.getValue(current) + 1
                    current = current.getNext(it)
                    grid[current] = minOf(grid.getOrDefault(current, Int.MAX_VALUE), nextDistance)
                }
            }
        }
        return grid
    }

    fun traverse(re: String, visited: MutableMap<Pos, Char>, pos: Pos): Pos {
        if (re.startsWith("^") && re.endsWith("$")) {
            return traverse(re.substring(1, re.length - 1), visited, pos)
        }
        if (re.startsWith("(") && re.endsWith(")")) {
            return traverse(re.substring(1, re.length - 1), visited, pos)
        }
        if (!re.contains("(") && !re.contains(")") && re.count { it == '|' } == 1) {
            traverse(re.split("|")[0], visited, pos)
            traverse(re.split("|")[1], visited, pos)
            return pos
        }
        var taken = 0
        val oki = re.takeWhile { it.isDirection() }.fold(pos) { p, c ->
            taken++
            val tmpP = p.getNext(c)
            tmpP.getSidesAfterMoving(c).forEach { visited[it] = '#' }
            visited[tmpP] = c.getDoor()
            val next = tmpP.getNext(c)
            visited[next] = '.'
            next
        }
        val rest = re.substring(taken)
        return oki
    }

    fun partTwo(re: String): Int {
        val map = parse(re, Pos(0, 0))
        return map.count { it.value >= 1000 }
    }
}

fun Map<Pos, Char>.toMap(): List<CharArray> {
    val minMax = Pos.getMinMax(this.keys)
    val xDelta = 0 - minMax.first.first
    val yDelta = 0 - minMax.second.first
    val maxX = minMax.first.second + xDelta
    val maxY = minMax.second.second + yDelta
    return (0..maxY).map { y ->
        (0..maxX).map { x ->
            this[Pos(x - xDelta, y - yDelta)] ?: '?'
        }.toCharArray()
    }

}

private fun Char.getDoor(): Char = when (this) {
    in "WE" -> '|'
    in "NS" -> '-'
    else -> error("not a valid dir: $this")
}

private fun String.isDirection(): Boolean = this.all { it.isDirection() }

private fun Char.isDirection(): Boolean = this in "NWES"
