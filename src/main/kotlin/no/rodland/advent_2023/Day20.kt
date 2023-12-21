package no.rodland.advent_2023

import lcm
import no.rodland.advent.Day
import java.math.BigInteger

// template generated: 20/12/2023
// Fredrik Rødland 2023

class Day20(val input: List<String>) : Day<Long, Long, Map<String, Day20.Mod>> {

    val parsed = input.parse()

    override fun partOne(): Long {
        parsed.values.forEach { it.reset() }
        repeat(1000) { sendPulse("button", Pulse.LOW) }
        val high = parsed.values.sumOf { it.high }
        val low = parsed.values.sumOf { it.low }
        return high * low.toLong()
    }


    override fun partTwo(): Long {
        parsed.values.forEach { it.reset() }
        // rx gets a low pulse when nextToLast sends a low pulse
        // nextToLast sends a low pulse when all beforeNextToLast are high
        val nextToLast = parsed.values.single { it.destinations.contains("rx") }
        val beforeNextToLast = parsed.values.filter { it.destinations.contains(nextToLast.id) }.map { it.id }
        val periods = beforeNextToLast.associateWith { -1L }.toMutableMap()

        var count = 0L
        while (true) {
            val result = sendPulse("button", Pulse.LOW, periods, ++count)
            if (result != 0L) {
                return result
            }
        }
    }

    private fun checkPart2(source: String, periods: MutableMap<String, Long>, pulse: Pulse, count: Long): Boolean {
        if (source in periods) {
            if (pulse == Pulse.HIGH && periods[source]!! == -1L) {
                periods[source] = count
            }
            if (periods.values.all { it != -1L }) {
                println("all found: $periods")
                return true
            }
        }
        return false
    }


    private fun sendPulse(mod: String, pulse: Pulse, periods: MutableMap<String, Long> = mutableMapOf(), count: Long = 0L): Long {
        val deque = ArrayDeque<QueueElement>()
        deque.addAll(parsed[mod]!!.pulse("button", pulse).map { QueueElement(mod, it.second, it.first) })
        while (deque.isNotEmpty()) {
            deque.removeFirst().let { (source, pulse, destination) ->
                if (checkPart2(source, periods, pulse, count)) return periods.values.map { it.toBigInteger() }.fold(BigInteger.ONE) { acc, i -> lcm(acc, i) }.toLong()
                val dest = parsed[destination]!!
                val addToQueue = dest.pulseWithCount(source, pulse)
                deque.addAll(addToQueue.map { QueueElement(destination, it.second, it.first) })
            }
        }
        return 0L
    }


    data class QueueElement(val source: String, val pulse: Pulse, val destination: String)
    enum class Pulse { HIGH, LOW }

    sealed class Mod(val id: String, val destinations: List<String>) {
        var high = 0
        var low = 0
        override fun toString(): String {
            return "$id, $low, $high"
        }

        fun reset() {
            high = 0
            low = 0
            resetSub()
        }

        fun pulseWithCount(source: String, pulse: Pulse): List<Pair<String, Pulse>> {
            if (pulse == Pulse.HIGH) {
                high++
            } else {
                low++
            }
            return pulse(source, pulse)
        }

        abstract fun resetSub()
        abstract fun pulse(source: String, pulse: Pulse): List<Pair<String, Pulse>>
    }

    class FlipFlop(id: String, destinations: List<String>) : Mod(id, destinations) {
        private var memory = false
        override fun resetSub() {
            memory = false
        }

        override fun pulse(source: String, pulse: Pulse): List<Pair<String, Pulse>> {
            return if (pulse == Pulse.LOW) {
                val pulseToSend = if (!memory) Pulse.HIGH else Pulse.LOW
                memory = !memory
                destinations.map { it to pulseToSend }
            } else {
                emptyList()
            }

        }
    }

    data class Conjunction(val iD: String, val dest: List<String>, val sources: List<String> = emptyList()) : Mod(iD, dest) {
        private val memory = sources.associateWith { Pulse.LOW }.toMutableMap()
        override fun resetSub() {
            memory.replaceAll { _, _ -> Pulse.LOW }
        }

        override fun pulse(source: String, pulse: Pulse): List<Pair<String, Pulse>> {
            memory[source] = pulse
            val pulseToSend = if (memory.values.all { it == Pulse.HIGH }) {
                Pulse.LOW
            } else {
                Pulse.HIGH
            }
            return destinations.map { it to pulseToSend }
        }
    }

    class Forwarder(iD: String, destinations: List<String>) : Mod(iD, destinations) {
        override fun resetSub() {}

        override fun pulse(source: String, pulse: Pulse): List<Pair<String, Pulse>> = destinations.map { it to pulse }
    }

    class NA(iD: String) : Mod(iD, emptyList()) {
        override fun resetSub() {}
        override fun pulse(source: String, pulse: Pulse): List<Pair<String, Pulse>> = emptyList()
    }


    override fun List<String>.parse(): Map<String, Mod> {
        val mods = map { line ->
            val (id, list) = line.split(" -> ")
            val destinations = list.split(";", " ", ",").filterNot { it.isEmpty() }

            val mod = when {
                id.startsWith("&") -> Conjunction(id.drop(1), destinations)
                id.startsWith("%") -> FlipFlop(id.drop(1), destinations)
                id == "broadcaster" -> Forwarder("broadcaster", destinations)
                else -> error("unknown mod: $id")
            }
            mod
        }
        val na = mods.flatMap { it.destinations }.distinct().filterNot { it in mods.map { it.id } }.map {
            NA(it)
        }
        return (mods + na + Forwarder("button", listOf("broadcaster"))).map { mod ->
            if (mod is Conjunction) {
                mod.copy(sources = mods.filter { mod.id in it.destinations }.map { it.id })
            } else {
                mod
            }
        }.associateBy { it.id }
    }


    override val day = "20".toInt()
}
