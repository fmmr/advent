package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 22/12/2024
// Fredrik Rødland 2024


class Day22(val input: List<String>) : Day<Long, Long, List<Long>> {

    val parse = input.parse()

    private fun Long.sequence() = generateSequence(this) { it.calc() }.take(2001)
    private val orgSequence = parse.map { input -> input.sequence() }

    override fun partOne(): Long {
        return orgSequence.sumOf(Sequence<Long>::last)
    }

    override fun partTwo(): Long {
        val allSeq = orgSequence.map { seq ->
            seq.map { it % 10 }
                .zipWithNext()
                .map { (a, b) -> b - a to b }
                .windowed(4)
                .map { list -> list.map { it.first } to list.last().second }
                .distinctBy { it.first }
                .toMap()
        }
        val prices = allSeq
            .fold(mutableMapOf<List<Long>, Long>()) { acc, map ->
                acc.apply {
                    map.forEach { (key, value) -> acc.merge(key, value, Long::plus) }
                }
            }
        return prices.values.max()
    }

    private fun Long.mix(secret: Long) = this xor secret
    private fun Long.prune() = this % 16777216L
    private fun Long.step(calc: (Long) -> Long) = calc(this).mix(this).prune()
    private fun Long.calc() = step { it shl 6 }.step { it shr 5 }.step { it shl 11 }

    override fun List<String>.parse(): List<Long> = map { it.toLong() }

    override val day = "22".toInt()
}
