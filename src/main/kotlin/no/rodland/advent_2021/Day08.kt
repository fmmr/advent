package no.rodland.advent_2021

//     aaaa  
//    b    c 
//    b    c 
//     dddd  
//    e    f 
//    e    f 
//     gggg  

@Suppress("UNUSED_PARAMETER")
object Day08 {

    private val digits = mapOf(
        0 to listOf("a", "b", "c", "e", "f", "g"),
        1 to listOf("c", "f"),
        2 to listOf("a", "c", "d", "e", "g"),
        3 to listOf("a", "c", "d", "f", "g"),
        4 to listOf("b", "c", "d", "f"),
        5 to listOf("a", "b", "d", "f", "g"),
        6 to listOf("a", "b", "d", "e", "f", "g"),
        7 to listOf("a", "c", "f"),
        8 to listOf("a", "b", "c", "d", "e", "f", "g"),
        9 to listOf("a", "b", "c", "d", "f", "g"),
    )
    private val numDigits = digits.mapValues { (_, v) -> v.size }


    fun partOne(list: List<String>): Int {
        val entries = parseInput(list)
        val lengths = listOf(1, 4, 7, 8).map { numDigits[it]!! }.toSet()
        return entries.map { it.output }.flatMap { output -> output.filter { it.length in lengths } }.count()
    }

    private fun parseInput(list: List<String>) = list.map { it.split(" | ") }.map { Entry(it.first().split(" ").map { it.trim() }, it.last().split(" ").map { it.trim() }) }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Entry(val signalPattern: List<String>, val output: List<String>)
}
