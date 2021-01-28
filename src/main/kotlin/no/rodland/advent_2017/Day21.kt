package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day21 {
    fun partOne(list: List<String>): Int {
        val rules = list.map { it.split(" => ") }.map { Grid(it[0]) to Grid(it[1]) }
        return 2
    }

    data class Grid(val list: List<List<Char>>) : List<List<Char>> by list {
        constructor(s: String) : this(s.split("/").let { row -> row.map { it.toCharArray().toList() } })

        private fun column(col: Int) = this.map { it[col] }

        override fun toString() = list.joinToString(",", prefix = "[", postfix = "]") { it.joinToString("") }
        fun toScreen() = list.joinToString("\n") { it.joinToString("") }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
