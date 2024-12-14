package no.rodland.advent_2024

import no.rodland.advent.*

// template generated: 12/12/2024
// Fredrik Rødland 2024

class Day12(val input: List<String>) : Day<Int, Int, Cave> {

    private val cave = input.parse()


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
        val c = cave.getOrNull(this)
        return listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH)
            .zipWithNext()
            .map { (first, second) ->
                listOf(
                    cave.getOrNull(next(first)),
                    cave.getOrNull(next(second)),
                    cave.getOrNull(next(first).next(second))
                )
            }.count { (side1, side2, corner) ->
                (c != side1 && c != side2) || (side1 == c && side2 == c && corner != c)
            }
    }

    @Suppress("ConvertCallChainIntoSequence")
    private fun findAllRegions(): List<Region> {
        fun getRegion(grid: Cave, pos: Pos, visited: MutableSet<Pos>): Set<Pos> {
            return (setOf(pos) + pos.neighbourCellsUDLR()
                .filter { it !in visited }
                .filter { it in cave }
                .filter { cave[it] == cave[pos] }
                .onEach { visited.add(it) }
                .flatMap { getRegion(grid, it, visited) }
                .toList()).toSet()
        }

        val visited = mutableSetOf<Pos>()
        return cave.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val pos = Pos(x, y)
                if (pos !in visited) {
                    Region(c, getRegion(cave, pos, visited))
                } else {
                    null
                }
            }.filterNotNull()
        }
    }


    override fun List<String>.parse(): Cave = indices.map { y -> indices.map { x -> this[y][x] }.toCharArray() }.toTypedArray<CharArray>()

    override val day = "12".toInt()

    data class Region(val c: Char, val positions: Set<Pos>) {
        operator fun contains(pos: Pos): Boolean = pos in positions
        fun area() = positions.size
        fun neighbours() = positions.flatMap { it.neighbourCellsUDLR() }.filterNot { it in positions }
        fun perimeter(): Int = neighbours().size
    }
}




