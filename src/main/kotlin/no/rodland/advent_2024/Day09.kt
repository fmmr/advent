package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 09/12/2024
// Fredrik Rødland 2024

class Day09(val input: List<String>) : Day<Long, Long, Pair<Int, List<Day09.Num?>>> {

    private val parsed = input.parse()
    private val sizePart1 = parsed.first
    private val parsedList = parsed.second

    override fun partOne(): Long {
        val list = parsedList.toMutableList()
        val memory = (0..<sizePart1).map { idx ->
            val num = list[idx]
            if (num.isFile) {
                num
            } else {
                list.removeLastNonNull()
            }
        }
        return memory.map { it.id }.checksum()
    }

    override fun partTwo(): Long {
//        val list = parsedList.toMutableList()
//        val map = mutableMapOf<Num, Int>()
//
//
//        parsedList.forEachIndexed { idx, id ->
//            map.putIfAbsent(id, idx)
//        }
//        val sizePart2 = map.map { (k, v) -> k.size }.sum()
//        val posMap = map.map { (k, v) -> v!! to k }.toMap()
//        val maxId = map.keys.last().id
//
//        map.keys.reversed().forEach {
//
//        }


        return 2
    }

    private fun MutableList<Num>.removeLastNonNull(): Num {
        var last = removeLast()
        while (last.isSpace) {
            last = removeLast()
        }
        return last
    }

    private fun Iterable<Int>.checksum(): Long = foldIndexed(0L) { i, acc, id -> acc + i * id }


    override fun List<String>.parse(): Pair<Int, List<Num>> {
        var id = 0
        var spaceId = -1
        var size = 0
        val chunked = first()
            .map { c -> c.digitToInt() }
            .chunked(2) {
                val num = Num(id++, it.first())
                val space = if (it.size == 2) {
                    Num(spaceId--, it.last())
                } else {
                    Num(spaceId--, 0)
                }
                size += it.first()
                num to space
            }
        val flatMap = chunked.flatMap { (num, space) ->
            (0..<num.size).map { num } + (0..<space.size).map { space }
        }
        return size to flatMap
    }

    data class Num(val id: Int, val size: Int) {
        val isSpace = id < 0
        val isFile = !isSpace
    }

    override val day = "09".toInt()
}
