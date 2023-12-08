package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.cycle
import java.math.BigInteger

// template generated: 08/12/2023
// Fredrik Rødland 2023

class Day08(val input: List<String>) : Day<BigInteger, BigInteger, Pair<Day08.Instructions, Map<String, Day08.Place>>> {

    private val parsed = input.parse()
    override fun partOne(): BigInteger {
        val (instructions, places) = parsed
        return find(listOf("AAA"), places, instructions)
    }

    override fun partTwo(): BigInteger {
        val (instructions, places) = parsed
        val start = places.filterKeys { it.endsWith("A") }.keys.toList()
        return find(start, places, instructions)
    }

    private fun find(
        start: List<String>,
        places: Map<String, Place>,
        instructions: Instructions
    ): BigInteger {
        val periods = start
            .map { traverse(it, places = places, instructions = instructions) }
            .map { BigInteger.valueOf(it) }
        return periods.reduce { acc, i -> lcm(acc, i) }
    }

    private fun lcm(n1: BigInteger, n2: BigInteger): BigInteger {
        // https://no.wikipedia.org/wiki/Minste_felles_multiplum
        // lcm = (n1 * n2) / gcd
        return (n1 * n2) / n1.gcd(n2)
    }

    data class State(val id: String, val pos: Int)

    private fun traverse(startId: String, places: Map<String, Place>, instructions: Instructions): Long {
        var seenCount = 0L
        var id = startId
        for (dir in instructions) {
            seenCount++
            val nextPlaces = places[id]!!
            id = if (dir == Dir.L) nextPlaces.left else nextPlaces.right
            if (id.endsWith("Z")) {
                return seenCount
            }
        }
        error("Endstate not found")
    }

    enum class Dir { L, R }
    data class Instructions(val list: List<Dir>) : Iterator<Dir> {
        val size = list.size
        var i = 0
        val hei = list.cycle()
        override fun hasNext(): Boolean = true
        override fun next(): Dir = list[(i++ % size)]
        fun pos() = i % size
    }

    data class Place(val id: String, val left: String, val right: String) {
        fun next(dir: Dir) = if (dir == Dir.L) left else right
    }

    override fun List<String>.parse(): Pair<Instructions, Map<String, Place>> {
        // AAA = (XMG, HJX)
        val list = drop(2).map { line ->
            val id = line.substringBefore(" =")
            val left = line.substringAfter(" = (").substringBefore(", ")
            val right = line.substringAfter(", ").substringBefore(")")
            Place(id, left, right)
        }
        val toList = first().toList()
        val instr = toList.map { Dir.valueOf(it.toString()) }
        return Instructions(instr) to list.associateBy { it.id }
    }

    override val day = "08".toInt()
}
