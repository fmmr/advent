package no.rodland.advent_2020

typealias Forrest = Array<CharArray>

@Suppress("UNUSED_PARAMETER")
object Day20 {
    fun partOne(list: String): Int {
        val tiles = list.split("\n\n").map { Tile(it) }
        return 2
    }

    fun partTwo(list: String): Int {
        return 2
    }

    val regex = "Tile (\\d+):".toRegex()

    data class Tile(val num: Int, val forrest: Forrest) {

        constructor(str: String, split: List<String> = str.split("\n")) : this(regex.find(split[0])?.groups?.get(1)?.value?.toInt() ?: -1, split.subList(1, split.size).map { it.toCharArray() }.toTypedArray())
    }
}

