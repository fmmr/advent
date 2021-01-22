package no.rodland.advent_2016

import no.rodland.advent.Pos
import no.rodland.advent.permutations

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val grid = Array(list.size) { y -> CharArray(list[0].length) { x -> list[y][x] } }
        val allOpen = grid.flatMapIndexed { y, chars ->
            chars.mapIndexed { x, c -> Pos(x, y) to c }.filter { it.second != '#' }
        }
        val digits = allOpen.filter { it.second.isDigit() }
        val digitMap = digits.map { it.second to it.first }.toMap()
        val digitPos = digits.map { it.first }
        val allDistances = digitPos
                .flatMap { pos ->
                    digitPos.filterNot { it == pos }.map {
                        //println("calc for ${(pos to it)}")
                        (pos to it) to bfs(grid, pos, it)
                    }
                }
                .filter { it.second != null }
                .toMap()

        val allDigitChar = digits.map { it.second }.joinToString("")
        val perms = allDigitChar.permutations().filter { it[0] == '0' }
        return perms.map { it.windowed(2) }
                .map { pairs ->
                    pairs
                            .map {
                                it[0] to it[1]
                            }
                            .map { (from, to) -> digitMap[from] to digitMap[to] }
                            .map { allDistances[it.first to it.second] }
                }
                .map { it.map { it!!.path.size }.sum() }
                .minOrNull()!!
    }

    private fun bfs(grid: Array<CharArray>, myPos: Pos, endPos: Pos): State? {
        val visited = mutableSetOf(myPos)
        val queue = ArrayDeque(listOf(State(myPos)))

        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            if (state.pos == endPos) {
                return state
            }

            state.pos.neighboorCellsNDLR()
                    .filter { visited.add(it) }
                    .filterNot { pos -> grid[pos.y][pos.x] == '#' }
                    .forEach { pos ->
                        queue.add(State(pos, state.path + pos))
                    }
        }
        return null
    }


    data class State(val pos: Pos, val path: List<Pos> = emptyList())


    fun partTwo(list: List<String>): Int {
        return 2
    }
}
