package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 13/12/2024
// Fredrik Rødland 2024

class Day13(val input: List<String>) : Day<Long, Long, List<Day13.Machine>> {

    private val machines = input.parse()
    private val machinesPart2 = machines.map { it.copy(p = P(10000000000000L + it.p.x, 10000000000000L + it.p.y)) }

    override fun partOne(): Long {
        return machines.filter { it.ok }.sumOf { it.ca * 3 + it.cb }
    }

    override fun partTwo(): Long {
        return machinesPart2.filter { it.ok }.sumOf { it.ca * 3 + it.cb }
    }

    data class Machine(val a: P, val b: P, val p: P) {
        // Button A: X+94, Y+34
        // Button B: X+22, Y+67
        // Prize: X=8400, Y=5400

        // 1: a.x*ca + b.x*cb = p.x
        // 2: a.y*ca + b.y*cb = p.y

        // 1: ca = (p.x - b.x*cb) / a.x
        // 2+1: a.y*((p.x - b.x*cb) / a.x) + b.y*cb = p.y
        //      b.y*cb  - a.y/a.x * b.x*cb  = p.y - a.y/a.x * p.x
        //      cb * (b.y - a.y/a.x * b.x) = p.y - a.y/a.x * p.x
        //      cb = (p.y - a.y/a.x * p.x) /  (b.y - a.y/a.x * b.x)
        //      cb = (a.x * p.y - a.y * p.x) / (a.x * b.y - a.y * b.x)

        val cb = (a.x * p.y - a.y * p.x) / (a.x * b.y - a.y * b.x)
        val ca = (p.x - b.x * cb) / a.x
        val ok = (cb * b.x + ca * a.x == p.x) && (cb * b.y + ca * a.y == p.y)
    }

    override fun List<String>.parse(): List<Machine> {
        return chunked(4).map { (a, b, p) ->
            val ax = a.substringAfter("X+").substringBefore(",").toLong()
            val ay = a.substringAfter("Y+").toLong()
            val bx = b.substringAfter("X+").substringBefore(",").toLong()
            val by = b.substringAfter("Y+").toLong()
            val px = p.substringAfter("X=").substringBefore(",").toLong()
            val py = p.substringAfter("Y=").toLong()
            Machine(P(ax, ay), P(bx, by), P(px, py))
        }
    }

    data class P(val x: Long, val y: Long)

    override val day = "13".toInt()
}
