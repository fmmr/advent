package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 24/12/2024
// Fredrik Rødland 2024

class Day24(val input: List<String>) : Day<Long, Long, Pair<Map<String, Day24.Wire>, Map<String, String>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val (values, logic) = parsed
        val system = values.toMutableMap()

        val map = logic
            .map { (k, v) -> buildNode(system, logic, k, v) }
        println("hm - ran through buildNode: $i")
        return map
            .filter { it.name.startsWith("z") }
            .sortedByDescending { it.name }
            .map { it.input.value }
            .joinToString("") { if (it) "1" else "0" }
            .toLong(2)
    }

    var i = 0
    private fun buildNode(system: MutableMap<String, Wire>, input: Map<String, String>, k: String, v: String): Wire {
        system[k]?.let { wire -> return wire }
        i++
        val (aKey, op, bKey) = v.split(" ")
        val a = system[aKey] ?: buildNode(system, input, aKey, input[aKey]!!)
        val b = system[bKey] ?: buildNode(system, input, aKey, input[bKey]!!)
        val wire = when (op) {
            "AND" -> Wire(k, AND(a, b))
            "OR" -> Wire(k, OR(a, b))
            "XOR" -> Wire(k, XOR(a, b))
            else -> error("Unknown op $op")
        }
        return wire
    }

    override fun partTwo(): Long {
        return 2
    }

    sealed interface Op {
        val value: Boolean
    }

    data class VALUE(override var value: Boolean) : Op

    data class AND(var a: Wire, var b: Wire) : Op {
        override val value: Boolean
            get() = a.input.value && b.input.value
    }

    data class OR(var a: Wire, var b: Wire) : Op {
        override val value: Boolean
            get() = a.input.value || b.input.value
    }

    data class XOR(var a: Wire, var b: Wire) : Op {
        override val value: Boolean
            get() = a.input.value xor b.input.value
    }

    data class Wire(val name: String, var input: Op) {}

    //x00: 1
    //x01: 1
    //x02: 1
    //y00: 0
    //y01: 1
    //y02: 0
    //
    //x00 AND y00 -> z00
    //x01 XOR y01 -> z01
    //x02 OR y02 -> z02
    override fun List<String>.parse(): Pair<Map<String, Wire>, Map<String, String>> {
        val (first, second) = joinToString("\n").split("\n\n")
        val values = first.lines().map { it.split(": ").let { it[0] to it[1].toB() } }.map { (name, value) -> name to Wire(name, VALUE(value)) }.toMap()
        val logic = second.lines().associate { line -> line.split(" -> ").let { it[1] to it[0] } }

        return values to logic
    }

    private fun String.toB() = this == "1"

    override val day = "24".toInt()
}

