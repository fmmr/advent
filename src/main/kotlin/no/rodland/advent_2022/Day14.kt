package no.rodland.advent_2022

import no.rodland.advent.Pos
import no.rodland.advent.get

// template generated: 28/11/2022
// Fredrik Rødland 2022


object Day14 {

    // 498,4 -> 498,6 -> 496,6
    // 503,4 -> 502,4 -> 502,9 -> 494,9

    fun partOne(input: List<String>): Int {
        val hei = input.parse()
        val maxX = hei.maxOf { it.maxOf { it.x } }
        val maxY = hei.maxOf { it.maxOf { it.y } }
        val minX = hei.minOf { it.minOf { it.x } }
        val minY = hei.minOf { it.minOf { it.y } }


        return 2
    }

    fun partTwo(input: List<String>): Long {
        return 2
    }

    fun List<String>.parse(): List<List<Pos>> = map { line -> line.split("->").map { it.trim() }.map { Pos(it) } }

}

