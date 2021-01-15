package no.rodland.advent_2016

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day13 {
    fun partOne(magicNumber: Int, destination: Pos): Int {
        val path = bfs(magicNumber, Pos(1, 1), destination)
        return path!!.drop(1).size
    }


    private fun bfs(magicNumber: Int, initial: Pos, destination: Pos): Path? {
        val queue = ArrayDeque<Path>()
        val seen = mutableSetOf<Pos>()
        queue.add(Path(initial))
        while (queue.isNotEmpty()) {
            val p = queue.removeFirst()
            val pos = p.last()

            if (pos == destination) {
                return p
            }
            queue.addAll(pos.neighboorCellsReadingOrder().filterNot { it.isWall(magicNumber) }.filter { seen.add(it) }.map { p + it })
        }
        return null
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private data class Path(val path: List<Pos>) : List<Pos> by path {
        operator fun plus(pos: Pos): Path = Path(path + pos)

        constructor(p: Pos) : this(listOf(p))
    }

    private fun Pos.isWall(magicNumber: Int) = (x * x + 3 * x + 2 * x * y + y + y * y + magicNumber).countOneBits() % 2 == 1

}
