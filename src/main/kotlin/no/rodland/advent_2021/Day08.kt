package no.rodland.advent_2021

private typealias Code = Set<Char>

object Day08 {
    fun partOne(list: List<String>): Int {
        val sizes = setOf(2, 4, 3, 7)
        return list
            .toEntries()
            .flatMap { it.output }
            .count { it.size in sizes }
    }

    fun partTwo(list: List<String>): Int {
        return list.toEntries().sumOf { it.outputAsint() }
    }

    class Entry(input: List<Code>, val output: List<Code>) {
        private val decoding = run {
            val one = input.decoding(2)
            val seven = input.decoding(3)
            val four = input.decoding(4)
            val eight = input.decoding(7)
            val nine = input.decoding(6, containsAll = four)
            val zero = input.decoding(6, setOf(nine), containsAll = one)
            val six = input.decoding(6, setOf(nine, zero))
            val three = input.decoding(5, containsAll = one)
            val five = input.decoding(5, setOf(three), containedIn = nine)
            val two = input.decoding(5, setOf(five, three))
            mapOf(zero to 0, one to 1, two to 2, three to 3, four to 4, five to 5, six to 6, seven to 7, eight to 8, nine to 9)
        }

        fun outputAsint(): Int {
            return output.map { decoding[it] }.joinToString("").toInt()
        }

        private fun List<Code>.decoding(length: Int, others: Set<Code> = emptySet(), containsAll: Code = emptySet(), containedIn: Code = setOf('a', 'b', 'c', 'd', 'e', 'f', 'g')) = asSequence()
            .filter { it.size == length }
            .filter { it.containsAll(containsAll) }
            .filter { containedIn.containsAll(it) }
            .filterNot { it in others }
            .single()
    }

    private fun List<String>.toEntries() = map { it.split(" | ") }.map { Entry(it.first().split(" ").map { str -> str.trim().toSet() }, it.last().split(" ").map { str -> str.trim().toSet() }) }
}
