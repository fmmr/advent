package no.rodland.advent_2021

import asInt
import no.rodland.advent.Pos
import product

typealias Grid = List<IntArray>

object Day09 {
    fun partOne(list: List<String>): Int {
        return list
            .toGrid()
            .lowPoints()
            .map { (_, value) -> value }
            .sumOf { it + 1 }
    }

    fun partTwo(list: List<String>): Long {
        val grid = list.toGrid()
        return grid
            .lowPoints()
            .map { (lowPos, _) -> grid.getRegion(lowPos).size }
            .sortedDescending()
            .take(3)
            .product()
    }

    private fun Grid.getRegion(lowPos: Pos): Set<Pos> {
        val explore = ArrayDeque<Pos>()
        val visited = mutableSetOf<Pos>()
        explore.add(lowPos)
        while (!explore.isEmpty()) {
            explore.removeFirst().let { pos ->
                if (visited.add(pos)) {
                    explore.addAll(neighboors(pos).filter { this[it] < 9 })
                }
            }
        }
        return visited
    }

    private operator fun Grid.get(pos: Pos): Int = this[pos.y][pos.x]

    private fun Grid.lowPoints(): List<Pair<Pos, Int>> {
        return flatMapIndexed { y, line ->
            line.mapIndexed { x, value -> Pos(x, y) to value }
                .filter { (pos, value) -> neighboors(pos).all { neighboor -> this[neighboor] > value } }
        }
    }

    private fun Grid.neighboors(p: Pos) = p.neighboorCellsUDLR().filter { it.isInGrid(this) }

    private fun List<String>.toGrid() = List(size) { row ->
        this[row].toCharArray().map { it.asInt() }.let { ints ->
            IntArray(ints.size) { value -> ints[value] }
        }
    }
}


