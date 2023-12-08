package no.rodland.advent_2023

import no.rodland.advent.Day
import java.util.*

// template generated: 08/12/2023
// Fredrik Rødland 2023

class Day08(val input: List<String>) : Day<Long, Long, Pair<Day08.Instructions, List<Day08.Place>>> {

    private val parsed = input.parse()
    private val START = "AAA"
    private val END = "ZZZ"
    override fun partOne(): Long {
        val (instructions, places) = parsed
        val map = places.associateBy { it.id }
        val visitedFromAAA = dfs(START, map, instructions)
        return (visitedFromAAA?.size?.toLong() ?: 0L) - 1
    }

    override fun partTwo(): Long {
        return 2
    }

    data class State(val id: String, val pos: Int)

    private fun dfs(
        startId: String = START,
        places: Map<String, Place>,
        instructions: Instructions
    ): List<String>? {
        val path = mutableListOf<String>()
        val visited = mutableSetOf<State>()
        val stack = Stack<State>()
        val startPos = instructions.pos()

        stack.push(State(startId, startPos))

        while (stack.isNotEmpty()) {
            val (id, instructionPos) = stack.pop()
            if (id == END) {
                path.add(id)
                return path
            }
            val place = places[id] ?: return null
            // If this state has been visited before, skip
            if (!visited.add(State(id, instructionPos))) {
                continue
            }

            // println("A PLACE: $place $dir")

            val nextDir = instructions.next()
            val nextId = if (nextDir == Dir.L) place.left else place.right
            val nextPos = instructions.pos()

            stack.push(State(nextId, nextPos))

            path.add(id)
        }

        return null
    }

//    fun dfsBest(id: String, places: Map<String, Place>, visited: MutableSet<String> = mutableSetOf()): Boolean {
//        if (id == "ZZZ") {
//            return true
//        }
//        if (!visited.add(id)) {
//            return false
//        }
//        val place = places[id] ?: return false
//        return dfs(place.left, places, visited) || dfs(place.right, places, visited)
//    }

    enum class Dir { L, R }
    data class Instructions(val list: List<Dir>) : Iterator<Dir> {
        val size = list.size
        var i = 0

        override fun hasNext(): Boolean = true
        override fun next(): Dir = list[(i++ % size)]
        fun pos() = i % size
    }

    data class Place(val id: String, val left: String, val right: String) {
        fun next(dir: Dir) = if (dir == Dir.L) left else right
    }

    override fun List<String>.parse(): Pair<Instructions, List<Place>> {
        // AAA = (XMG, HJX)
        val list = drop(2).map { line ->
            val id = line.substringBefore(" =")
            val left = line.substringAfter(" = (").substringBefore(", ")
            val right = line.substringAfter(", ").substringBefore(")")
            Place(id, left, right)
        }
        val toList = first().toList()
        val instr = toList.map { Dir.valueOf(it.toString()) }
        return Instructions(instr) to list
    }

    override val day = "08".toInt()
}
