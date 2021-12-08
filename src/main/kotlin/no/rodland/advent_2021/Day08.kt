package no.rodland.advent_2021

object Day08 {
    fun partTwo(list: List<String>): Int {
        return parseInput(list).sumOf { it.outputAsint() }
    }

    fun partOne(list: List<String>): Int {
        val entries = parseInput(list)
        return entries
            .map { it.output }
            .flatMap { output -> output.filter { it.length in setOf(2, 4, 3, 7) } }
            .count()
    }

    private fun parseInput(list: List<String>) = list.map { it.split(" | ") }.map { Entry(it.first().split(" ").map { str -> str.trim() }, it.last().split(" ").map { str -> str.trim() }) }

    class Entry(signalPattern: List<String>, val output: List<String>) {
        private val signalPatternSorted = signalPattern.map { str -> str.normalize() }
        private val decoding = run {
            val four = signalPatternSorted.decoding(length = 4)
            val one = signalPatternSorted.decoding(length = 2)
            val seven = signalPatternSorted.decoding(length = 3)
            val eight = signalPatternSorted.decoding(length = 7)
            val three = signalPatternSorted.decoding(length = 5, containsAll = one)
            val nine = signalPatternSorted.decoding(length = 6, containsAll = four)
            val zero = signalPatternSorted.decoding(length = 6, others = setOf(nine), containsAll = one)
            val six = signalPatternSorted.decoding(length = 6, others = setOf(nine, zero))
            val five = signalPatternSorted.decoding(length = 5, others = setOf(three), containedIn = nine)
            val two = signalPatternSorted.decoding(length = 5, others = setOf(five, three))
            mapOf(zero to 0, one to 1, two to 2, three to 3, four to 4, five to 5, six to 6, seven to 7, eight to 8, nine to 9)
        }

        fun outputAsint(): Int {
            return output.map { it.normalize() }.map { decoding[it]!! }.joinToString("").toInt()
        }

        private fun String.normalize() = toList().sorted().joinToString("")

        private fun List<String>.decoding(length: Int, others: Set<String> = emptySet(), containsAll: String = "", containedIn: String = "abcdefg") = asSequence()
            .filter { it.length == length }
            .filter { potential -> containsAll.all { it in potential } }
            .filter { potential -> potential.all { it in containedIn } }
            .filterNot { it in others }
            .single()


    }
}
