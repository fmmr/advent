package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 09/12/2024
// Fredrik Rødland 2024

class Day09(val input: List<String>) : Day<Long, Long, Pair<Int, List<Day09.Num?>>> {

    private val parsed = input.parse()
    val size = parsed.first
    val parsedList = parsed.second

    override fun partOne(): Long {
        val list = parsedList.toMutableList()
        val memory = (0..<size).map { idx ->
            list[idx] ?: list.removeLastNonNull()
        }
        return memory.map { it.id }.checksum()
    }

    private fun MutableList<Num?>.removeLastNonNull(): Num {
        var last = removeLast()
        while (last == null) {
            last = removeLast()
        }
        return last
    }

    private fun Iterable<Int>.checksum(): Long = foldIndexed(0L) { i, acc, id -> acc + i * id }

    override fun partTwo(): Long {
        return 2
    }

    override fun List<String>.parse(): Pair<Int, List<Num?>> {
        var id = 0
        var size = 0
        val chunked = first()
            .map { c -> c.digitToInt() }
            .chunked(2) {
                val num = Num(id++, it.first())
                size += it.first()
                if (it.size == 2) {
                    num to it.last()
                } else {
                    num to null
                }
            }
        val flatMap = chunked.flatMap { (num, space) ->
            (0..<num.size).map { num } + (space?.let { (0..<space).map { null } } ?: emptyList())
        }
        return size to flatMap
    }

    data class Num(val id: Int, val size: Int)

    override val day = "09".toInt()
}
