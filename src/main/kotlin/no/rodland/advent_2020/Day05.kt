package no.rodland.advent_2020

object Day05 {
    fun partOne(list: List<String>): Int {
        return list.map { rowAndColum(it) }.map { seatId(it.first, it.second) }.maxByOrNull { it } ?: -1
    }

    fun partTwo(list: List<String>): Int {
        val ids = list.map { rowAndColum(it) }.map { seatId(it.first, it.second) }
        val allSeats = (ids.min()!!..ids.max()!!).toList()
        return (allSeats - ids).first()
    }

    private fun rowAndColum(boarding: String): Pair<Int, Int> {
        val rowCommands = boarding.substring(0..6)
        val colCommands = boarding.substring(7)
        val row = find(0..127, rowCommands).first
        val col = find(0..7, colCommands).first
        return row to col
    }

    private tailrec fun find(intRange: IntRange, rowCommands: String): IntRange {
        return if (rowCommands.isEmpty()) {
            intRange
        } else {
            val command = rowCommands[0]
            val range = when (command) {
                'F', 'L' -> intRange.first..((intRange.last + intRange.first) / 2)
                'B', 'R' -> ((intRange.last + intRange.first) / 2 + 1)..intRange.last
                else -> error("unknown command: $command")
            }
            find(range, rowCommands.substring(1))
        }
    }

    fun seatId(row: Int, column: Int) = row * 8 + column
}