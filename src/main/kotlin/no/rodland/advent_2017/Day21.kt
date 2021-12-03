package no.rodland.advent_2017

typealias GridArray = List<List<Day21.Grid>>

@Suppress("UNUSED_PARAMETER")
object Day21 {
    fun partOne(list: List<String>, iterations: Int): Int {
        val start = Grid(".#./..#/###")
        val rules = list
                .map { it.split(" => ") }
                .map { Grid(it[0]) to Grid(it[1]) }
                .flatMap { pair -> pair.first.variants.map { it to pair.second } }
                .toMap()
        return sequence(start, rules)
                .drop(1) // first is start
                .take(iterations)
                .last()
                .on
    }

    private fun sequence(start: Grid, rules: Map<Grid, Grid>) = generateSequence(start) { grid ->
        grid
                .split()
                .map { rowOfGrids -> rowOfGrids.map { g -> rules[g] ?: error("no replacement found for:\n${g.toScreen()}") } }
                .concat()
    }

    fun GridArray.concat(): Grid = Grid(flatMap { row -> row.reduce { acc, grid -> acc + grid } })

    data class Grid(val list: List<List<Char>>) : List<List<Char>> by list {
        constructor(s: String) : this(s.split("/").let { row -> row.map { it.toCharArray().toList() } })

        val on by lazy { sumOf { row -> row.count { it == '#' } } }

        val variants by lazy {
            (0..2).runningFold(this@Grid) { acc, _ -> acc.rotate() }
                    .let { rotations -> rotations + rotations.map { it.flip() } }
        }

        operator fun plus(other: Grid): Grid = Grid(mapIndexed { index: Int, list: List<Char> -> list + other[index] })

        fun split(): GridArray {
            listOf(2, 3).first { size % it == 0 }.let { s ->
                val rows = chunked(s)
                return rows.map { gridRow ->
                    val list = gridRow.map { it.chunked(s) }
                    list[0].indices.map { idx -> Grid(list.map { it[idx] }) }
                }
            }
        }

        private fun rotate() = Grid((0 until size).map { column(it).reversed() })
        private fun flip() = Grid(map { it.reversed() })
        private fun column(col: Int) = this.map { it[col] }
        // not used in 2017 Day 21 but could be handy - private fun rotateL() = rotate().rotate().rotate()
        // not used in 2017 Day 21 but could be handy - private fun rotate2() = rotate().rotate()
        // not used in 2017 Day 21 but could be handy - private fun flipV() = Grid(reversed())

        override fun toString() = list.joinToString(",", prefix = "[", postfix = "]") { it.joinToString("") }
        fun toScreen() = list.joinToString("\n") { it.joinToString("") } + "\n"
    }
}
