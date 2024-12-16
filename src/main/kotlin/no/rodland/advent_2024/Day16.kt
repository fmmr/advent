package no.rodland.advent_2024

import no.rodland.advent.Cave
import no.rodland.advent.Day
import no.rodland.advent.toCave

// template generated: 15/12/2024
// Fredrik Rødland 2024

class Day16(val input: List<String>) : Day<Long, Long, Array<CharArray>> {

    private val maze = input.parse()

    override fun partOne(): Long {
        return 2
    }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Cave {
        return this.toCave()
    }

    override val day = "16".toInt()
}
