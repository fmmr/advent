package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 20/12/2023
// Fredrik Rødland 2023

class Day20(val input: List<String>) : Day<Long, Long, Map<String, Day20.Mod>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    sealed class Mod(val id: String, val destinations: List<String>)
    class FlipFlop(id: String, destinations: List<String>) : Mod(id, destinations)
    class Broadcast(destinations: List<String>) : Mod("broadcaster", destinations)
    class Conjunction(id: String, destinations: List<String>) : Mod(id, destinations)

    override fun List<String>.parse(): Map<String, Mod> {
        return associate { line ->
            println(line)
            val (id, list) = line.split(" -> ")
            val destinations = list.split(";", " ").filterNot { it.isEmpty() }
            val mod = when {
                id.startsWith("&") -> Conjunction(id.drop(1), destinations)
                id.startsWith("%") -> FlipFlop(id.drop(1), destinations)
                id == "broadcaster" -> Broadcast(destinations)
                else -> error("unknown mod: $id")
            }
            mod.id to mod
        }
    }

    override val day = "20".toInt()
}
