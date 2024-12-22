package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 22/12/2024
// Fredrik Rødland 2024





class Day22(val input: List<String>) : Day<Long, Long, List<Long>> {

    override fun partOne(): Long {
        return input.parse().map { input -> generateSequence(input) { it.calc() }.take(2001) }.sumOf(Sequence<Long>::last)

    }

    override fun partTwo(): Long {
        return 2
    }

    private fun Long.mix(secret: Long) = this xor secret
    private fun Long.prune() = this % 16777216L
    private fun Long.step(calc: (Long) -> Long) = calc(this).mix(this).prune()
    private fun Long.calc() = step { it * 64 }.step { it / 32 }.step { it * 2048 }

    override fun List<String>.parse(): List<Long> = map { it.toLong() }

    override val day = "22".toInt()
}
