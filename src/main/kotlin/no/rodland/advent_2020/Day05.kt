package no.rodland.advent_2020

object Day05 {
    fun partOne(list: List<String>): Int {
        return list.map { seatId(it) }.maxByOrNull { it }!!
    }

    fun partTwo(list: List<String>): Int {
        val ids = list.map { seatId(it) }
        val allSeats = (ids.min()!!..ids.max()!!).toList()
        return (allSeats - ids).first()
    }

    private fun seatId(boarding: String): Int {
        val row = find(0..127, boarding.substring(0..6)).first
        val col = find(0..7, boarding.substring(7)).first
        return row * 8 + col
    }

    private tailrec fun find(intRange: IntRange, commands: String): IntRange {
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
}