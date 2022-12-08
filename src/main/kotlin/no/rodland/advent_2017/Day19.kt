package no.rodland.advent_2017

import no.rodland.advent.Direction
import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day19 {
    fun partOne(list: List<String>): String {
        val seq = traverse(list)
        return seq.mapNotNull { it?.char }.filter { it in 'A'..'Z' }.joinToString("")
    }

    fun partTwo(list: List<String>): Int {
        val seq = traverse(list)
        return seq.count()
    }

    private fun traverse(list: List<String>): Sequence<State?> {
        val size = list.maxOfOrNull { it.length }!!
        val grid = Array(list.size) { y ->
            CharArray(size) { x -> if (x < list[y].length) list[y][x] else ' ' }
        }
        val start = Pos(grid[0].indexOf('|'), 0)
        var current = State(start, '|', Direction.SOUTH) as State?
        val states = sequence {
            while (true) {
                if (current != null) {
                    yield(current)
                    val next = current?.next(grid)
                    current = next
                } else {
                    yield(null)
                }
            }
        }
        val seq = states.takeWhile { it != null }
        return seq
    }

    data class State(val pos: Pos, val char: Char, val dir: Direction) {
        private fun Pos.isValid(grid: Array<CharArray>) = isInGrid(grid) && grid[y][x] != ' ' && getDirection(pos, this) != dir.goBack()

        fun next(grid: Array<CharArray>): State? {

            val nextSameDirection = pos.next(dir.toString()[0])
            if (nextSameDirection.isValid(grid)) {
                return State(nextSameDirection, grid[nextSameDirection.y][nextSameDirection.x], dir)
            }

            val neighboors = pos.neighbourCellsUDLR()
                .filter { it.isValid(grid) }
                .map { it to grid[it.y][it.x] }

            if (neighboors.size == 1) {
                return State(neighboors[0].first, neighboors[0].second, getDirection(pos, neighboors[0].first))
            }
            if (neighboors.isEmpty()) {
                return null
            }



            return null
        }

        private fun getDirection(from: Pos, to: Pos): Direction {
            return if (from.x == to.x) {
                if (to.y > from.y) {
                    Direction.SOUTH
                } else {
                    Direction.NORTH
                }
            } else {
                if (to.x > from.x) {
                    Direction.EAST
                } else {
                    Direction.WEST
                }
            }
        }
    }

}

