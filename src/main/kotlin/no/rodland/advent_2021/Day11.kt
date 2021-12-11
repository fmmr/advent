package no.rodland.advent_2021

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos


@Suppress("UNUSED_PARAMETER")
object Day11 {
    fun partOne(list: List<String>, steps: Int = 100): Int {
        return solve(list, steps)
    }

    fun partTwo(list: List<String>): Int {
        return solve(list, 2000)
    }

    private fun solve(list: List<String>, steps: Int): Int {
        return (1..steps).runningFold(IntGrid.fromInput(list) to 0) { acc, i ->
            acc.first.increase().step().apply {
                if (second == 100) { // part 2
                    return i
                }
            }
        }.sumOf { it.second }
    }

    private fun IntGrid.step(flashed: Set<Pos> = emptySet()): Pair<IntGrid, Int> {
        val willFlash = this.willFlash(flashed)
        return if (willFlash.isEmpty()) {
            this.clear() to flashed.size
        } else {
            flash(willFlash).step(flashed + willFlash)
        }
    }

    private fun IntGrid.clear(): IntGrid {
        return IntGrid(mapIndexed { y, ar -> IntArray(ar.size) { x -> if (this[y][x] > 9) 0 else this[y][x] } })
    }

    private fun IntGrid.flash(positions: Set<Pos>): IntGrid {
        val allNeighbors = positions.flatMap { pos -> pos.neighboorCellsAllEight().filter { it.isInGrid(this) } }.groupingBy { it }.eachCount()
        return IntGrid(mapIndexed { y, ar -> IntArray(ar.size) { x -> this[y][x] + allNeighbors.getOrDefault(Pos(x, y), 0) } })
    }

    private fun IntGrid.willFlash(flashed: Set<Pos>) =
        flatMapIndexed { y, ar ->
            ar.mapIndexed { x, i -> Pos(x, y) to i }
                .filter { it.second > 9 }
                .map { it.first }
        }.filterNot { it in flashed }.toSet()
}


