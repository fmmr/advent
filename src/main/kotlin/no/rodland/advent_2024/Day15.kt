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
        val gridCopy = grid.copy()
        var p = start
        directions.forEach { d -> p = gridCopy.move(d, p) }
        return gridCopy
            .flatMapIndexed { y, row ->
                row.mapIndexed { x, c -> if (c == 'O') 100 * y + x else 0 }
            }
            .sum().toLong()
    }

    override fun partTwo(): Long {
        return 2
    }

    private fun getRest(cave: Cave, d: Direction, robot: Pos): Set<Pos> {
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

//                ']' -> {
//                    set.add(p)
//                    val side = Pos(p.x - 1, p.y)
//                    set.add(side)
//                    val pnext = p.next(d)
//                    val sidenext = side.next(d)
//                    if (pnext !in queue) set.add(pnext)
//                    if (sidenext !in queue) set.add(sidenext)
//                }
//
//                '[' -> {
//                    set.add(p)
//                    val side = Pos(p.x + 1, p.y)
//                    set.add(side)
//                    val pNext = p.next(d)
//                    val sideNext = side.next(d)
//                    if (pNext !in queue) set.add(pNext)
//                    if (sideNext !in queue) set.add(sideNext)
//                }

                else -> error("Unexpected character: ${cave[p]}")
            }
        }
        val possible = true
        return if (possible) set else emptySet()
    }

    private fun Set<Pos>.changes(cave: Cave, d: Direction, robot: Pos): Map<Pos, Char> {

        val clearAll = map { p -> p to '.' }.toMap()
        val newValues = map { p -> p.next(d) to cave[p] }.toMap()
        // XXX probably something I can do here
        return clearAll.mapValues { (k, v) -> newValues[k] ?: v }


//        val chars = map { cave[it] }
//        var i = 0
//        val old = if (isEmpty()) {
//            emptyMap()
//        } else {
//            (listOf('.') + (1..<size).map { chars[it - 1] }).associateBy { robot.next(d, i++) }
//        }
//        return old
    }

    private fun Cave.move(d: Direction, robot: Pos): Pos {
        val line: Set<Pos> = getRest(this, d, robot)
        val changes = line.changes(this, d, robot)
        return changes
            .mapNotNull { (p, c) ->
                this[p] = c
                if (c == '@') p else null
            }
            .firstOrNull() ?: robot
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


