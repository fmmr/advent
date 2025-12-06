package no.rodland.advent_2025

import no.rodland.advent.Day

// template generated: 06/12/2025
// Fredrik Rødland 2025

class Day05(val input: List<String>) : Day<Long, Long, Pair<List<LongRange>, List<Long>>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val (ranges, numbers) = parsed
        return numbers.count { number -> ranges.any { range -> number in range } }.toLong()

    }

    override fun partTwo(): Long {
        val (ranges, numbers) = parsed
        
        // hey!
        // sorting - merging is actually simple and we don't need the older result - only the last one!!
        // https://kotlinlang.slack.com/archives/C87V9MQFK/p1764914061769139?thread_ts=1764910807.815869&cid=C87V9MQFK
        return ranges
            .sortedBy(LongRange::first)
            .fold(0L to LongRange.EMPTY) { (count, range), next ->
                when (next.first) {
                    in range if next.last in range -> count to range
                    in range -> count + (next.last - range.last) to range.first..next.last
                    else -> count + (next.last + 1 - next.first) to next
                }
            }
            .first
    }

    override fun List<String>.parse(): Pair<List<LongRange>, List<Long>> {
        val values = mapNotNull {
            if (it.contains("-")) {
                val (a, b) = it.split("-")
                LongRange(a.toLong(), b.toLong())
            } else if (it.isEmpty()) {
                null
            } else {
                it.toLong()
            }
        }
        val (r, l) = values.partition { it is LongRange }
        return r as List<LongRange> to l as List<Long>
    }

    override val day = "05".toInt()
}
