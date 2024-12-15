package no.rodland.advent_2024

import no.rodland.advent.*

// template generated: 15/12/2024
// Fredrik Rødland 2024

class Day15(val input: List<String>) : Day<Int, Int, Pair<Pair<Array<CharArray>, Array<CharArray>>, Pair<Pos, List<Direction>>>> {

    private val parsed = input.parse()
    private val grid = parsed.first.first
    private val grid2 = parsed.first.second
    private val start = parsed.second.first
    private val directions = parsed.second.second
    private val boxCorner = setOf('O', '[')

    override fun partOne(): Int {
        return solve(start, grid.copy())
    }

    override fun partTwo(): Int {
        return solve(Pos(start.x * 2, start.y), grid2.copy())
    }

    private fun solve(p: Pos, gridCopy: Array<CharArray>): Int {
        directions.fold(p) { pos, direction ->
            gridCopy.move(direction, pos)
        }
        return gridCopy
            .flatMapIndexed { y, row ->
                row.mapIndexed { x, c -> if (c in boxCorner) 100 * y + x else 0 }
            }
            .sum()
    }

    private fun Cave.move(d: Direction, robot: Pos): Pos {
        val changes = getAreaToMove(this, d, robot).changes(this, d)
        return changes
            .mapNotNull { (p, c) ->
                this[p] = c
                if (c == '@') p else null
            }
            .firstOrNull() ?: robot
    }

    private fun getAreaToMove(cave: Cave, d: Direction, robot: Pos): Set<Pos> {
        val set = mutableSetOf<Pos>()
        val queue = ArrayDeque<Pos>()
        queue.add(robot)
        while (queue.isNotEmpty()) {
            val p = queue.removeFirst()
            when (cave[p]) {
                'O', '@' -> {
                    set.add(p)
                    queue.add(p.next(d))
                }

                '#' -> {
                    return emptySet()
                }

                '.' -> {
                    set.add(p)
                }

                ']' -> {
                    val side = Pos(p.x - 1, p.y)
                    val pNext = p.next(d)
                    val sideNext = side.next(d)
                    if (pNext !in set) queue.add(pNext)
                    if (sideNext !in set) queue.add(sideNext)
                    set.add(p)
                    set.add(side)
                }

                '[' -> {
                    val side = Pos(p.x + 1, p.y)
                    val pNext = p.next(d)
                    val sideNext = side.next(d)
                    if (pNext !in set) queue.add(pNext)
                    if (sideNext !in set) queue.add(sideNext)
                    set.add(p)
                    set.add(side)
                }

                else -> error("Unexpected character: ${cave[p]}")
            }
        }
        val possible = true
        return if (possible) set else emptySet()
    }

    private fun Set<Pos>.changes(cave: Cave, d: Direction): Map<Pos, Char> {
        val clearAll = associate { p -> p to '.' }
        val newValues = associate { p -> p.next(d) to cave[p] }
        return (clearAll + newValues).toMap().filterKeys { it in this }
    }

    override fun List<String>.parse(): Pair<Pair<Array<CharArray>, Array<CharArray>>, Pair<Pos, List<Direction>>> {
        val (map, move) = joinToString("\n").split("\n\n")
        var start = Pos(0, 0)
        val lines = map.lines()
        val cave = lines.indices.map { y -> lines.indices.map { x -> lines[y][x].also { if (it == '@') start = Pos(x, y) } }.toCharArray() }.toTypedArray<CharArray>()
        val cavePart2 = cave.mapIndexed { _, row ->
            row.flatMapIndexed { _, c ->
                when (c) {
                    'O' -> listOf('[', ']')
                    '@' -> listOf('@', '.')
                    '#' -> listOf('#', '#')
                    '.' -> listOf('.', '.')
                    else -> error("Invalid character")
                }
            }.toCharArray()
        }.toTypedArray<CharArray>()
        val directions = move.split("\n").flatMap { s -> s.map { Direction.fromChar(it) } }
        return (cave to cavePart2) to (start to directions)
    }

    override val day = "15".toInt()
}


