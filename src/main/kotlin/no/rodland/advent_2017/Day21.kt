package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day21 {
    fun partOne(list: List<String>, iterations: Int): Int {
        val start = Grid(".#./..#/###")
        val rules = list
                .map { it.split(" => ") }
                .map { Grid(it[0]) to Grid(it[1]) }
                .flatMap { pair -> pair.first.allVariations().map { it to pair.second } }
                .toMap()

        return generateSequence(start) {
            it.split().map { row ->
                row.map { g ->
                    val replacement = rules[g]
                    replacement ?: error("no replacement found for:\n${g.toScreen()}")
                }
            }.concat()
        }.drop(1)
                .take(iterations)
//                .onEach { println(it.toScreen()) }
                .last()
                .on
    }

    fun List<List<Grid>>.concat(): Grid {
        return Grid(flatMap { row ->
            row.reduce { acc, grid ->
//                println("$acc    +   $grid")
                acc + grid
            }
        })
    }

    data class Grid(val list: List<List<Char>>) : List<List<Char>> by list {
        constructor(s: String) : this(s.split("/").let { row -> row.map { it.toCharArray().toList() } })

        val on by lazy { map { it.count { it == '#' } }.sum() }

        operator fun plus(other: Grid): Grid = Grid(mapIndexed { index: Int, list: List<Char> -> list + other[index] })

        fun split(): List<List<Grid>> {
            return if (size <= 3) {
                listOf(listOf(this))
            } else if (size % 2 == 0) {
                val rows = chunked(2)
                val cols = rows.map { it[0].chunked(2).zip(it[1].chunked(2)) }
                cols.map { it.map { g -> Grid(listOf(g.first, g.second)) } }
            } else {
                val rows = chunked(3)
                return rows.map { gridRow ->
                    val row1 = gridRow[0].chunked(3)
                    val row2 = gridRow[1].chunked(3)
                    val row3 = gridRow[2].chunked(3)
                    row1.mapIndexed { index: Int, list: List<Char> -> Grid(listOf(row1[index], row2[index], row3[index])) }
                }
            }
        }

        fun allVariations() = sequence {
            yield(this@Grid)
            yield(flipV())
            yield(flipH())
            rotateR().let { rotated ->
                yield(rotated)
                yield(rotated.flipV())
                yield(rotated.flipH())
            }
            yield(rotate2())
            yield(rotateL())
        }

        fun rotateR() = Grid((0 until size).map { column(it).reversed() })
        private fun rotateL() = rotateR().rotateR().rotateR()
        private fun rotate2() = rotateR().rotateR()
        private fun flipH() = Grid(map { it.reversed() })
        private fun flipV() = Grid(reversed())
        private fun column(col: Int) = this.map { it[col] }

        override fun toString() = list.joinToString(",", prefix = "[", postfix = "]") { it.joinToString("") }
        fun toScreen() = list.joinToString("\n") { it.joinToString("") } + "\n"
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
