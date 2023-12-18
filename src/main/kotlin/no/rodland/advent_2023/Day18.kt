package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Direction
import no.rodland.advent.Pos

// template generated: 18/12/2023
// Fredrik Rødland 2023

// heavily inspired by https://www.youtube.com/watch?v=bGWK76_e-LM
// Shoelace formula: https://en.wikipedia.org/wiki/Shoelace_formula
// Pick's theorem: https://en.wikipedia.org/wiki/Pick%27s_theorem

class Day18(val input: List<String>) : Day<Long, Long, List<Day18.Command>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val points = parsed.map { it -> it.direction to it.num }
        return shoelacePick(points)
//        return buildMapAndTraverseAndCount()
    }

    override fun partTwo(): Long {
        val points = parsed.map { it.colour }.map { it.takeLast(1).toDir() to it.dropLast(1).toInt(16) }
        return shoelacePick(points)
    }

    private fun shoelacePick(points: List<Pair<Direction, Int>>): Long {
        var boundaryPoints = 0
        val posList = points
            .fold(listOf(Pos(0, 0))) { acc, (dir, num) ->
                boundaryPoints += num
                val pos = acc.last().next(dir, num)
                acc + pos
            }

        // https://en.wikipedia.org/wiki/Shoelace_formula
        // {\displaystyle A={\frac {1}{2}}\sum _{i=1}^{n}x_{i}(y_{i+1}-y_{i-1})}
        val shoelace = posList.mapIndexed { i, p -> p.x.toLong() * (posList[(i + 1) % posList.size].y - posList[(posList.size + i) % posList.size].y) }.sum()

        // https://en.wikipedia.org/wiki/Pick%27s_theorem
        // {\displaystyle A=i+{\frac {b}{2}}-1.}
        // => i = A - b/2 + 1
        val interiorPoints = shoelace - boundaryPoints / 2 + 1
        return interiorPoints + boundaryPoints
    }

    private fun String.toDir(): Direction = when (this.first()) {
        '0' -> Direction.EAST
        '1' -> Direction.SOUTH
        '2' -> Direction.WEST
        '3' -> Direction.NORTH
        else -> error("unknown dir: $this")
    }

    override fun List<String>.parse(): List<Command> {
        return map { line ->
            val split = line.split(" ", "(", ")", "#").filterNot { it.isBlank() }
            Command(Direction.fromUDLR(split[0].first()), split[1].toInt(), split[2])
        }
    }

    data class Command(val direction: Direction, val num: Int, val colour: String)

    override val day = "18".toInt()
}

