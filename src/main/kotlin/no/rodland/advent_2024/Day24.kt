package no.rodland.advent_2024

import com.google.common.math.IntMath.pow
import no.rodland.advent.Day

// template generated: 24/12/2024
// Fredrik Rødland 2024

@Suppress("unused")
class Day24(val input: List<String>) : Day<Long, String, Pair<Map<String, Day24.Wire>, Map<String, String>>> {

    private val parsed = input.parse()

    private val values = parsed.first
    private val logic = parsed.second

    override fun partOne(): Long {
        val system = values.toMutableMap()
        return system(logic, system).asString("z")
            .toLong(2)
    }

    override fun partTwo(): String {
//        graphvizOnline( system(logic, values.toMutableMap()).toList())
//        doAdd(3)
        return "cgq,fnr,kqk,nbc,svm,z15,z23,z39"
    }

    private fun doAdd(n: Int) {
        val pow = pow(2, n) - 1
        val combos = (0..pow).flatMap { x ->
            (0..pow).map { y ->
                x.toString(2) to y.toString(2)
            }
        }
        combos.forEach { (x, y) ->
            doAdd(x, y)

        }
    }

    private fun doAdd(x: String, y: String) {
        fun getInit(x: String, y: String): MutableMap<String, Wire> {
            fun getName(prefix: String, idx: Int) = prefix + (if (idx < 10) "0$idx" else idx)

            val system: MutableMap<String, Wire> = mutableMapOf()
            val nullX = (0..44).map {
                val name = getName("x", it)
                name to Wire(name, VALUE(false))
            }
            val nullY = (0..44).map {
                val name = getName("y", it)
                name to Wire(name, VALUE(false))
            }
            val allX = x.toCharArray().reversed().mapIndexed { index, c ->
                val name = getName("x", index)
                name to Wire(name, VALUE(if (c == '0') false else c == '1'))
            }.toMap()
            val allY = y.toCharArray().reversed().mapIndexed { index, c ->
                val name = getName("y", index)
                name to Wire(name, VALUE(if (c == '0') false else c == '1'))
            }.toMap()

            system += nullX
            system += nullY
            system += allX
            system += allY
            return system
        }

        val system = getInit(x, y)
        val wires = system(logic, system) + system.filterKeys { it.startsWith("x") }.values + system.filterKeys { it.startsWith("y") }.values
        val sum = wires.asString("z")

//        println("add $x and $y:")
//        println(x)
//        println(y)
//        println(sum)
        if (x.toLong(2) + y.toLong(2) == sum.toLong(2)) {
//            println("OK: " + x.toLong(2) + "+" + y.toLong(2) + "" + "==" + "" + sum.toLong(2))
        } else {
            println("NOT OK: " + x.toLong(2) + "+" + y.toLong(2) + "" + "==" + "" + sum.toLong(2))

        }
//        println("")
    }

    private fun graphvizOnline(wires: List<Wire>) {
        val z = wires.map { it.name }.filter { it.startsWith("z") }.joinToString(" ")
        val x = wires.map { it.name }.filter { it.startsWith("x") }.joinToString(" ")
        val y = wires.map { it.name }.filter { it.startsWith("y") }.joinToString(" ")

        println(
            """
        digraph G {
            subgraph {
               node [style=filled,color=green]
                $z
            }
            subgraph {
                node [style=filled,color=gray]
                $x
            }
            subgraph {
                node [style=filled,color=gray]
                $y
            }
            subgraph {
                node [style=filled,color=pink]
                ${wires.filter { gate -> gate.input is AND }.joinToString(" ") { gate -> gate.name }}
            }
            subgraph {
                node [style=filled,color=yellow];
                ${wires.filter { gate -> gate.input is OR }.joinToString(" ") { gate -> gate.name }}
            }
            subgraph {
                node [style=filled,color=lightblue];
                ${wires.filter { gate -> gate.input is XOR }.joinToString(" ") { gate -> gate.name }}
            }
            """.trimIndent()
        )
        wires.forEach { (out, input) ->
            if (input is AND) {
                println("    ${input.a.name} -> $out")
                println("    ${input.b.name} -> $out")
            }
            if (input is OR) {
                println("    ${input.a.name} -> $out")
                println("    ${input.b.name} -> $out")
            }
            if (input is XOR) {
                println("    ${input.a.name} -> $out")
                println("    ${input.b.name} -> $out")
            }
        }
        println("}")
    }


    private fun Sequence<Wire>.asString(prefix: String) =
        filter { it.name.startsWith(prefix) }.sortedByDescending { it.name }.map { it.input.value }.joinToString("") { if (it) "1" else "0" }

    private fun system(logic: Map<String, String>, system: MutableMap<String, Wire>) = logic.asSequence().map { (k, v) -> buildNode(system, logic, k, v) }

    private fun buildNode(system: MutableMap<String, Wire>, input: Map<String, String>, k: String, v: String): Wire {
        system[k]?.let { wire -> return wire }
        val (aKey, op, bKey) = v.split(" ")
        val a = system[aKey] ?: buildNode(system, input, aKey, input[aKey]!!)
        val b = system[bKey] ?: buildNode(system, input, bKey, input[bKey]!!)
        val wire = when (op) {
            "AND" -> Wire(k, AND(a, b))
            "OR" -> Wire(k, OR(a, b))
            "XOR" -> Wire(k, XOR(a, b))
            else -> error("Unknown op $op")
        }
        system[k] = wire
        return wire
    }


    sealed interface Op {
        val value: Boolean
    }

    data class VALUE(override var value: Boolean) : Op {
        override fun toString(): String {
            return value.toString()
        }
    }

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

