package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 05/12/2023
// Fredrik Rødland 2023

class Day05(val input: List<String>) : Day<Long, Long, List<Day05.Converter>> {

    private val parsed = input.parse()

    override fun partOne(): Long {
        val seeds = input.take(1).first().substringAfter(": ").split(" ").map { it.toLong() }
        return seeds.minOf { seed ->
            parsed.fold(seed) { acc: Long, converter: Converter ->
                converter.convert(acc)
            }
        }
    }

    override fun partTwo(): Long {
        val seeds = input.take(1).first().substringAfter(": ").split(" ").map { it.toLong() }.chunked(2).map { it.first()..<(it.last() + it.first()) }

//        val hei = seeds.map { seed ->
//            parsed.map { converter ->
////                val applies = converter.applies(seed)
//                val applies2 = converter.applies2(seed)
//                println("${converter.source}-${converter.destination}: seed: $seed, conv: ${converter.range}, applies: applies, applies2: $applies2")
//            }
//        }
        return 2L
    }

    data class Converter(val source: String, val destination: String, val conversionRanges: List<ConversionRange>) {
        fun convert(input: Long) = conversionRanges.firstOrNull { it.inRange(input) }?.convert(input) ?: input
        val max = conversionRanges.maxOf { it.sourceStart + it.length }
        val min = conversionRanges.minOf { it.sourceStart }
        val range = min..max
        fun applies(seedRange: LongRange) = range.intersect(seedRange).isNotEmpty()
        fun applies2(seedRange: LongRange): Boolean {
            return !(range.last < seedRange.first || range.first > seedRange.last)
        }
    }


    data class ConversionRange(val destinationStart: Long, val sourceStart: Long, val length: Long) {
        private val sourceRange = sourceStart..(sourceStart + length)
        fun inRange(input: Long) = input in sourceRange
        fun convert(input: Long) = if (inRange(input)) {
            destinationStart + (input - sourceStart)
        } else {
            input
        }
    }


    override fun List<String>.parse(): List<Converter> {
        //"seeds: 79 14 55 13",
        val drop = drop(2)
        val joinToString = drop.joinToString("\n")
        val converters = joinToString.split("\n\n").map { it.split("\n") }
        return converters.map { input ->
            val header = input.take(1).first()
            val source = header.substringBefore("-to-")
            val destination = header.substringBefore(" map").substringAfter("-to-")
            val conversionRanges = input.drop(1).map { numbers ->
                val (dStart, sStart, length) = numbers.split(" ").map { it.trim().toLong() }
                ConversionRange(dStart, sStart, length)
            }
            Converter(source, destination, conversionRanges)
        }
    }

    override val day = "05".toInt()
}
