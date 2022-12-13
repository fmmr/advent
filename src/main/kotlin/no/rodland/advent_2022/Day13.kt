package no.rodland.advent_2022

import org.json.simple.JSONArray
import org.json.simple.JSONValue

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day13 {

    var count = 0  // keeping track of the number of compares

    fun partOne(list: List<String>): Int {
        count = 0
        val indices = list
            .asSequence()
            .filterNot { it.isEmpty() }
            .chunked(2)
            .map { it.first() to it.last() }
            .mapIndexed { i, pair -> i to pair.rightOrder() }
            .filter { it.second }
            .map { it.first + 1 }
            .toList()
        println("did $count compares")
        return indices.sum()
    }

    fun partTwo(list: List<String>): Int {
        count = 0
        val p1 = parse("[[2]]")
        val p2 = parse("[[6]]")
        val all = list
            .filterNot { it.isEmpty() }
            .map { parse(it) }
            .toMutableList()
            .apply {
                add(p1)
                add(p2)
            }
            .sorted()
        println("did $count compares")
        return (all.indexOf(p1) + 1) * (all.indexOf(p2) + 1)
    }

    fun Pair<String, String>.rightOrder(): Boolean {
        return parse(first) < parse(second)
    }

    private fun parse(input: Any): Packet {
        return when (input) {
            is String -> parse(JSONValue.parse(input))
            is JSONArray -> ListPacket(input.map { parse(it!!) })
            is Long -> IntPacket(input.toInt())
            is Packet -> input
            else -> error("$input type is " + input.javaClass)
        }
    }

    private sealed class Packet : Comparable<Packet>

    private data class IntPacket(val value: Int) : Packet() {
        override fun compareTo(other: Packet): Int {
            count++
            return when (other) {
                is IntPacket -> value.compareTo(other.value)
                is ListPacket -> ListPacket(listOf(this)).compareTo(other)
            }
        }
    }

    private data class ListPacket(val value: List<Packet>) : Packet(), List<Packet> by value {
        override fun compareTo(other: Packet): Int {
            count++
            return when (other) {
                is IntPacket -> this.compareTo(ListPacket(listOf(other)))
                is ListPacket -> {
                    zip(other)
                        .asSequence()
                        .map { (first, second) ->
                            parse(first).compareTo(parse(second))
                        }
                        .firstOrNull { it != 0 } ?: (size - other.size)
                }
            }
        }
    }
}


