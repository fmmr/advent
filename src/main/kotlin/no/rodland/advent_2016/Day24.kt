package no.rodland.advent_2016

import no.rodland.advent.Pos
import no.rodland.advent.permutations

object Day24 {
    fun partOne(list: List<String>): Int {
        return solve(list) { s -> permutations(s) }
    }

    fun partTwo(list: List<String>): Int {
        return solve(list) { s -> permutations(s, '0') }
    }

    private fun solve(list: List<String>, permutations: (String) -> Sequence<String>): Int {
        val grid = Array(list.size) { y -> CharArray(list[0].length) { x -> list[y][x] } }

        val digits = grid
                .flatMapIndexed { y, chars ->
                    chars.mapIndexed { x, c -> Pos(x, y) to c }.filter { it.second != '#' }
                }
                .filter { it.second.isDigit() }
        val digitMap = digits.map { it.second to it.first }.toMap()
        val digitPos = digits.map { it.first }
        val allDistances = digitPos
                .flatMap { pos ->
                    digitPos.filterNot { it == pos }.map {
                        (pos to it) to bfs(grid, pos, it)
                    }
                }
                .filter { it.second != null }
                .toMap()

        return permutations(digits.map { it.second }.joinToString(""))
                .map { it.windowed(2) }
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

    private fun permutations(allDigitChar: String, endChar: Char? = null) = allDigitChar
            .permutations()
            .filter { it[0] == '0' }
            .map { str -> endChar?.let { str + endChar } ?: str }
}
