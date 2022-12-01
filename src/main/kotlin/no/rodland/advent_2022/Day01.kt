package no.rodland.advent_2022

import chunckedInts

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day01 {
    fun partOne(list: List<String>): Int {
        return list.chunckedInts().maxOf { it.sum() }
    }

    fun partTwo(list: List<String>): Int {
        return list.chunckedInts().map { it.sum() }.sorted().takeLast(3).sum()
    }
}
