package no.rodland.advent_2020

object Day05 {
    fun partOne(list: List<String>): Int {
        return list.map { seatIdSimple(it) }.maxByOrNull { it }!!
    }

    fun partTwo(list: List<String>): Int {
        val ids = list.map { seatIdSimple(it) }.toSet()
        val min = ids.minOrNull()!!
        val max = ids.maxOrNull()!!
        return ((min..max) - ids).first()
    }

    @Suppress("unused")
    private fun seatIdSimple(boarding: String): Int {
        return boarding
            .replace("[BR]".toRegex(), "1")
            .replace("[FL]".toRegex(), "0")
            .toInt(2)
    }

    @Suppress("unused")
    private fun seatId(boarding: String): Int {
        val row = find(0..127, boarding.substring(0..6)).first
        val col = find(0..7, boarding.substring(7)).first
        return row * 8 + col
    }

    private fun find(intRange: IntRange, commands: String): IntRange {
        return if (commands.isEmpty()) {
            intRange
        } else {
            find(
                when (commands[0]) {
                    'F', 'L' -> intRange.first..((intRange.last + intRange.first) / 2)
                    'B', 'R' -> ((intRange.last + intRange.first) / 2 + 1)..intRange.last
                    else -> error("unknown command: ${commands[0]}")
                },
                commands.substring(1)
            )
        }
    }


    // from chriswk - with his elegant binaryParser
    // https://github.com/chriswk/adventofcode/blob/main/src/main/kotlin/com/chriswk/aoc/advent2020/Day5.kt
    private fun seatIdChriswk(placement: String): Int {
        return binaryParser(placement, setOf('F', 'L'), setOf('B', 'R'))
    }

    private fun binaryParser(input: String, zeros: Set<Char>, ones: Set<Char>): Int {
        return input.fold(0) { a, c ->
            when (c) {
                in ones -> 2 * a + 1
                in zeros -> 2 * a
                else -> throw IllegalArgumentException("Unexpected")
            }
        }
    }
}