package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day13 {
    fun partOne(list: List<String>): Int {
        val map = list.filterNot { it.isEmpty() }.chunked(2).map { it.first() to it.last() }
        val size = map.filter { it.rightOrder() }.size
        return 2
    }


    private fun Pair<String, String>.rightOrder():Boolean {
        return true
    }


    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }
}

