package no.rodland.advent_2024

import no.rodland.advent.*

// template generated: 15/12/2024
// Fredrik Rødland 2024

class Day15(val input: List<String>) : Day<Long, Long, Pair<Pair<Pos, Cave>, List<Direction>>> {

    private val parsed = input.parse()
    private val start = parsed.first.first
    private val grid = parsed.first.second
    private val directions = parsed.second
    private val width = grid[0].size
    private val height = grid.size

    override fun partOne(): Long {
        val (endGrid, robot) = directions.fold(grid to start) { g, d ->
            g.first.move(d, g.second)
        }
        // endGrid.print()
        return endGrid.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'O') {
                    100 * y + x
                } else {
                    0
                }

            }
        }.sum().toLong()
    }

    private fun getRest(d: Direction, robot: Pos): List<Pos> {
        return when (d) {
            Direction.NORTH -> ((robot.y) downTo 0).map { Pos(robot.x, it) }
            Direction.SOUTH -> ((robot.y)..<height).map { Pos(robot.x, it) }
            Direction.WEST -> ((robot.x) downTo 0).map { Pos(it, robot.y) }
            Direction.EAST -> ((robot.x)..<width).map { Pos(it, robot.y) }
        }
    }

    private fun List<Pos>.fix(cave: Cave, d: Direction, robot: Pos): Map<Pos, Char> {
        // cannot move walls

        val chars = map { cave[it] }
        val firstSpace = chars.indexOf('.')
        val firstWall = chars.indexOf('#')
        if (firstWall > -1 && firstWall < firstSpace) {
            return emptyMap()
        }
        if (firstSpace == -1) {
            return emptyMap()
        }
        var i = 0
        return (listOf('.') + (1..firstSpace).map { chars[it - 1] }).map { robot.next(d, i++) to it }.toMap()
    }

    private fun Cave.move(d: Direction, robot: Pos): Pair<Cave, Pos> {
        val line: List<Pos> = getRest(d, robot)
        val fixedLine = line.fix(this, d, robot)
        var newRobot = Pos(0, 0)
        val newGrid = indices.map { y ->
            indices.map { x ->
                (fixedLine[Pos(x, y)] ?: get(Pos(x, y))).also { if (it == '@') newRobot = Pos(x, y) }

            }.toCharArray()
        }.toTypedArray<CharArray>()
        return newGrid to newRobot
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<Pair<Pos, Cave>, List<Direction>> {
        val (map, move) = joinToString("\n").split("\n\n")
        var start = Pos(0, 0)
        val lines = map.lines()
        val cave = lines.indices.map { y -> lines.indices.map { x -> lines[y][x].also { if (it == '@') start = Pos(x, y) } }.toCharArray() }.toTypedArray<CharArray>()
        val directions = move.split("\n").flatMap { s -> s.map { Direction.fromChar(it) } }
        return (start to cave) to directions
    }

    override val day = "15".toInt()
}


