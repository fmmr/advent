package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Point
import no.rodland.advent.Pos


// template generated: 21/12/2023
// Fredrik Rødland 2023

class Day21(val input: List<String>) : Day<Int, Long, Map<Pos, Char>> {


    private val parsed = input.parse()
    private val width = parsed.keys.maxOf { it.x } + 1
    private val height = parsed.keys.maxOf { it.y } + 1




    // https://github.com/eagely/adventofcode/blob/main/src/main/kotlin/solutions/y2023/Day21.kt
    fun solvePart2(): Any {
        if (parsed.size < 50) return "womp womp"
        val req = 26501365L
        var delta = 0L
        var skip = 0L
        val queue = ArrayDeque<Pair<Point, Long>>()
        val start = parsed.filterValues { it == 'S' }.keys.single()

        queue.add(Pair(start, 0))
        val visited = hashSetOf<Point>()
        val size = Point(height, width)
        val cycle = size.x * 2
        var lastStep = 0L
        var previousPlots = 0L
        var delta1 = 0L
        var delta2 = 0L
        var plots = 0L
        while (queue.isNotEmpty()) {
            val (position, step) = queue.removeFirst()
            if (position in visited) continue
            if (step % 2 == 1L) visited.add(position)
            if (step % cycle == 66L && step > lastStep) {
                println("step: $step, plots: $plots, delta1: $delta1, delta2: $delta2")
                lastStep = step
                if (plots - previousPlots - delta1 == delta2) {
                    delta = plots - previousPlots + delta2
                    skip = step - 1
                    break
                }
                delta2 = (plots - previousPlots) - delta1
                delta1 = plots - previousPlots
                previousPlots = plots
            }
            plots = visited.size.toLong()
            queue.addAll(position.neighbourCellsUDLR().filter { parsed[it % size] != '#' }.map { it to step + 1 })
        }
        while (skip < req) {
            skip += cycle
            plots += delta
            delta += delta2
        }
        return plots
    }

    operator fun Pos.rem(other: Point) = Point(x pm other.x, y pm other.y)
    operator fun Pos.rem(other: Int) = Point(x pm other, y pm other)
    infix fun Pos.pm(other: Point) = this % other
    infix fun Int.pm(other: Int): Int {
        val mod = this % other
        return if (mod < 0) mod + other else mod
    }
    override fun partOne(): Int {
//        val start = parsed.filterValues { it == 'S' }.map { it.key }.single()
        var newMap = parsed
        val repeat = if (parsed.size == 121) 6 else 64
        repeat(repeat) { newMap = step(newMap) }
        return newMap.count { it.value == 'O' }
    }

    fun step(cave: Map<Pos, Char>): Map<Pos, Char> {
        val previousSteps = cave.filterValues { it == 'O' || it == 'S' }.keys
        val newOs = previousSteps
            .flatMap { it.neighbourCellsUDLR() }
            .filterNot { cave[it] == '#' }
            .map { it to 'O' }
        val nullOld = previousSteps.map { it to '.' }
        return cave + nullOld + newOs
    }


    override fun partTwo(): Long {
        println(solvePart2())
        return 2
    }

    override fun List<String>.parse(): Map<Pos, Char> {
        return flatMapIndexed { y, line ->
            line.mapIndexed() { x, c ->
                Pos(x, y) to c
            }
        }.toMap()
    }

    override val day = "21".toInt()
}

