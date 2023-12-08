package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.cycle
import java.math.BigInteger
import java.util.*

// template generated: 08/12/2023
// Fredrik Rødland 2023

class Day08(val input: List<String>) : Day<Long, Long, Pair<Day08.Instructions, Map<String, Day08.Place>>> {

    private val parsed = input.parse()
    override fun partOne(): Long {
        val (instructions, places) = parsed
        return find(listOf("AAA"), places, instructions)
    }

    override fun partTwo(): Long {
        val (instructions, places) = parsed
        val start = places.filterKeys { it.endsWith("A") }.keys.toList()
        return find(start, places, instructions)
    }

    private fun find(
        start: List<String>,
        places: Map<String, Place>,
        instructions: Instructions
    ): Long {
        val periods = start
            .map {
                dfs(it, places = places, instructions = instructions)
            }
            .map { BigInteger.valueOf(it) }
        return periods.reduce { acc, i ->
            lcm(acc, i)
        }.toLong()
    }

    private fun lcm(n1: BigInteger, n2: BigInteger): BigInteger {
        // https://no.wikipedia.org/wiki/Minste_felles_multiplum
        // lcm = (n1 * n2) / gcd
        val gcd = n1.gcd(n2)
        return ((n1 * n2) / gcd)
    }

    data class State(val id: String, val pos: Int)

    private fun dfs(
        startId: String,
        places: Map<String, Place>,
        instructions: Instructions
    ): Long {
        var seenCount = 0L
        val stack = Stack<State>()
        val pos = instructions.pos()
        stack.push(State(startId, pos))
        while (stack.isNotEmpty()) {
            val (id, _) = stack.pop()
            if (id.endsWith("Z")) {
                return seenCount
            }
            val nextPlaces = places[id] ?: error("hm - why don't we have place $id")
            // If this state has been visited before, skip
            // if (!visited.add(State(id, instructions.pos()))) {                println("SEEN");                continue            }
            // println("A PLACE: $nextPlaces $ids")

            val nextDir = instructions.next()
            val nextIds = if (nextDir == Dir.L) nextPlaces.left else nextPlaces.right
            val nextPos = instructions.pos()
            stack.push(State(nextIds, nextPos))
            seenCount++
        }
        return seenCount
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
