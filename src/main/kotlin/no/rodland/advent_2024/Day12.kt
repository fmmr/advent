package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Direction
import no.rodland.advent.Pos

// template generated: 12/12/2024
// Fredrik Rødland 2024

class Day12(val input: List<String>) : Day<Int, Int, Array<CharArray>> {

    private val grid = input.parse()


    override fun partOne(): Int {
        return findAllRegions().sumOf { region ->
            region.area() * region.perimeter()
        }
    }

    override fun partTwo(): Int {
        return findAllRegions().sumOf { region ->
            region.sides() * region.area()
        }
    }

    private fun Region.sides(): Int {
        return positions.sumOf { p -> p.corners() }
    }

    // got help from: https://todd.ginsberg.com/post/advent-of-code/2024/day12/
    private fun Pos.corners(): Int {
        val c = grid[this]
        return listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH)
            .zipWithNext()
            .map { (first, second) ->
                listOf(
                    grid[next(first)],
                    grid[next(second)],
                    grid[next(first).next(second)]
                )
            }.count { (side1, side2, corner) ->
                (c != side1 && c != side2) || (side1 == c && side2 == c && corner != c)
            }
    }

    @Suppress("ConvertCallChainIntoSequence")
    private fun findAllRegions(): List<Region> {
        fun getRegion(grid: Grid, pos: Pos, visited: MutableSet<Pos>): Set<Pos> {
            return (setOf(pos) + pos.neighbourCellsUDLR()
                .filter { it !in visited }
                .filter { it in this@Day12.grid }
                .filter { this@Day12.grid[it] == this@Day12.grid[pos] }
                .onEach { visited.add(it) }
                .flatMap { getRegion(grid, it, visited) }
                .toList()).toSet()
        }

        val visited = mutableSetOf<Pos>()
        return grid.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val pos = Pos(x, y)
                if (pos !in visited) {
                    Region(c, getRegion(grid, pos, visited))
                } else {
                    null
                }
            }.filterNotNull()
        }
    }


    operator fun Grid.contains(pos: Pos): Boolean = pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

    operator fun Grid.get(pos: Pos): Char? = if (pos in this) grid[pos.y][pos.x] else null

    override fun List<String>.parse(): Grid = indices.map { y -> indices.map { x -> this[y][x] }.toCharArray() }.toTypedArray<CharArray>()

    override val day = "12".toInt()

    data class Region(val c: Char, val positions: Set<Pos>) {
        operator fun contains(pos: Pos): Boolean = pos in positions
        fun area() = positions.size
        fun neighbours() = positions.flatMap { it.neighbourCellsUDLR() }.filterNot { it in positions }
        fun perimeter(): Int = neighbours().size
    }
}




