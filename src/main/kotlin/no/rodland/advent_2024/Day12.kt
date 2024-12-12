package no.rodland.advent_2024

import no.rodland.advent.Day
import no.rodland.advent.Pos

// template generated: 12/12/2024
// Fredrik Rødland 2024

class Day12(val input: List<String>) : Day<Int, Int, Pair<Array<CharArray>, Array<CharArray>>> {

    private val parsed = input.parse()
    private val grid = parsed.first
    private val rotated = parsed.second

    data class Region(val c: Char, val positions: Set<Pos>) {
        operator fun contains(pos: Pos): Boolean = pos in positions
        fun area() = positions.size
        fun neighbours() = positions.flatMap { it.neighbourCellsUDLR() }.filterNot { it in positions }
        fun perimeter(): Int = neighbours().size
    }

    override fun partOne(): Int {
        return findAllRegions().sumOf {
            it.area() * it.perimeter()
        }
    }

    private fun findAllRegions(): List<Region> {
        val visited = mutableSetOf<Pos>()
        return grid.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val pos = Pos(x, y)
                if (pos !in visited) {
                    Region(c, grid.getRegion(pos, visited))
                } else {
                    null
                }
            }.filterNotNull()
        }
    }

    @Suppress("ConvertCallChainIntoSequence")
    private fun Array<CharArray>.getRegion(pos: Pos, visited: MutableSet<Pos>): Set<Pos> {
        return (setOf(pos) + pos.neighbourCellsUDLR()
            .filter { it !in visited }
            .filter { it in grid }
            .filter { grid[it] == grid[pos] }
            .onEach { visited.add(it) }
            .flatMap { getRegion(it, visited) }
            .toList()).toSet()
    }


    override fun partTwo(): Int {
        return 2
    }

    operator fun Grid.contains(pos: Pos): Boolean = pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

    operator fun Grid.get(pos: Pos): Char = this[pos.y][pos.x]

    override fun List<String>.parse(): Pair<Array<CharArray>, Array<CharArray>> {
        val upright = indices.map { y -> indices.map { x -> this[y][x] }.toCharArray() }.toTypedArray()
        val rotated = indices.map { y -> indices.map { x -> this[x][y] }.toCharArray() }.toTypedArray()
        return upright to rotated
    }


    override val day = "12".toInt()

//    private fun Grid.fences(): Int {
//        flatMap { row ->
//            val windowed = row.toList().windowed(2).map { (c1, c2) ->
//                if (c1 == c2) 0 else 2
//            }
//            windowed
//        }
//        return 2
//    }


}


