package no.rodland.advent_2021

import no.rodland.advent.IntGrid
import no.rodland.advent.Pos
import java.util.PriorityQueue

// more or less a copy of:
// https://todd.ginsberg.com/post/advent-of-code/2021/day15/
object Day15 { // dijkstra
    fun partOne(list: List<String>): Int {
        return IntGrid.fromInput(list).traverse()
    }

    fun partTwo(list: List<String>): Int {
        val grid = IntGrid.fromInput(list)
        val destination = Pos((grid.first().size * 5) - 1, (grid.size * 5) - 1)
        return grid.traverse(destination)
    }

    private fun IntGrid.traverse(destination: Pos = Pos(get(0).lastIndex, lastIndex)): Int {
        val initial = Pos(0, 0)
        val toBeEvaluated = PriorityQueue<Traversal>().apply { add(Traversal(initial, 0)) }
        val visited = mutableSetOf<Pos>()
        while (toBeEvaluated.isNotEmpty()) {
            val here = toBeEvaluated.poll()
            if (here.pos == destination) {
                return here.totalRisk
            }
            if (visited.add(here.pos)) {
                here.pos
                    .neighbourCellsUDLR().filter { it.x in 0..destination.x && it.y in 0..destination.y }
                    .forEach { toBeEvaluated.offer(Traversal(it, here.totalRisk + get(this, it))) }

            }
        }
        error("No path to destination (which is really weird, right?)")
    }


    private operator fun get(intGrid: IntGrid, pos: Pos): Int {
        val dx = pos.x / intGrid.first().size
        val dy = pos.y / intGrid.size
        val originalRisk = intGrid[pos.y % intGrid.size][pos.x % intGrid.first().size]
        val newRisk = (originalRisk + dx + dy)
        return newRisk.takeIf { it < 10 } ?: (newRisk - 9)
    }

    private data class Traversal(val pos: Pos, val totalRisk: Int) : Comparable<Traversal> {
        override fun compareTo(other: Traversal): Int = this.totalRisk - other.totalRisk
    }
}
