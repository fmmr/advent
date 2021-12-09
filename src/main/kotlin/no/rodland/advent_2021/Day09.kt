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
            .sorted()
            .takeLast(3)
            .product()
    }

    private fun Grid.getRegion(lowPos: Pos): Set<Pos> {
        val frontier = ArrayDeque<Pos>()
        val visited = mutableSetOf<Pos>()
        frontier.add(lowPos)
        while (!frontier.isEmpty()) {
            frontier.removeFirst().let { potentialPos ->
                if (this[potentialPos.y][potentialPos.x] < 9 && visited.add(potentialPos)) {
                    neighboors(potentialPos).forEach { frontier.add(it) }
                }
            }
        }
        return visited
    }

    private fun Grid.lowPoints(): List<Pair<Pos, Int>> {
        return flatMapIndexed { y, line ->
            line.mapIndexed { x, value -> Pos(x, y) to value }
                .filter { (pos, value) -> neighboors(pos).all { neighboor -> this[neighboor.y][neighboor.x] > value } }
        }
    }

    private fun Grid.neighboors(p: Pos) = p.neighboorCellsUDLR().filter { it.isInGrid(this) }

    private fun List<String>.toGrid() = List(size) { row ->
        this[row].toCharArray().map { it.asInt() }.let { ints ->
            IntArray(ints.size) { value -> ints[value] }
        }
    }
}


