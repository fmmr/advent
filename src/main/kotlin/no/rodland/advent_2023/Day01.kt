package no.rodland.advent_2023

// template generated: 01/12/2023
// Fredrik Rødland 2023

class Day01(val input: List<String>) {

    fun partOne(): Long {
        return input.sumOf { line -> calibrationValue(line, DIGIT_MAPPING.values) }.toLong()
    }

    fun partTwo(): Long {
        return input.sumOf { line -> calibrationValue(line, DIGIT_MAPPING.keys + DIGIT_MAPPING.values) }.toLong()
    }

    private fun calibrationValue(line: String, numbers: Collection<String>): Int {
        val (_, firstNumber) = line.findAnyOf(numbers)!!
        val (_, lastNumber) = line.findLastAnyOf(numbers)!!
        val first = DIGIT_MAPPING[firstNumber] ?: firstNumber
        val last = DIGIT_MAPPING[lastNumber] ?: lastNumber
        return "$first$last".toInt()
    }

    companion object {
        private val DIGIT_MAPPING = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )
    }
}
