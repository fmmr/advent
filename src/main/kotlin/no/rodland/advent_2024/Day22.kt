package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 22/12/2024
// Fredrik Rødland 2024


class Day22(val input: List<String>) : Day<Long, Long, List<Long>> {

    override fun partOne(): Long {
        return orgSequence().sumOf(Sequence<Long>::last)

    }

    override fun partTwo(): Long {

        // XXX F TODO re-write

        val map = orgSequence()
            .map { it.map { n -> n % 10 } }
            .map { seq ->
                seq
                    .zipWithNext { a, b -> b to b - a }
                    .windowed(4) { list -> list.map { it.second } to list.last().first }

            }
        val prices = map.map {
                it.groupingBy { (key, value) -> key }
                    .fold({ _, value -> value.second }, { _, a, _ -> a })
            }

        return prices.flatMap { it.keys }.toSet().maxOf { key ->
            prices.sumOf { it[key] ?: 0 }
        }


    }

//    private fun part2Seq(seq: Sequence<Long>) = seq.map { it % 10 }
//        .zipWithNext { a, b -> b to b - a }
//        .windowed(4) { (a, b, c, d) -> listOf(a.second, b.second, c.second, d.second) to d.first }
//        .groupingBy { (key, value) -> key }
//        .fold({ _, value -> value.second }, { _, a, _ -> a })

    private fun orgSequence() = input.parse().map { input -> generateSequence(input) { it.calc() }.take(2001) }

    private fun Long.mix(secret: Long) = this xor secret
    private fun Long.prune() = this % 16777216L
    private fun Long.step(calc: (Long) -> Long) = calc(this).mix(this).prune()
    private fun Long.calc() = step { it * 64 }.step { it / 32 }.step { it * 2048 }

    override fun List<String>.parse(): List<Long> = map { it.toLong() }

    override val day = "22".toInt()
}
