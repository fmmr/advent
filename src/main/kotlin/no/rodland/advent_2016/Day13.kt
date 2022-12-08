package no.rodland.advent_2016

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day13 {
    fun partOne(magicNumber: Int, destination: Pos): Int {
        val path = bfs(magicNumber, Pos(1, 1), destination).first!!
        return path.drop(1).size
    }

    fun partTwo(magicNumber: Int): Int {
        val seen = bfs(magicNumber, Pos(1, 1), null).second!!
        return seen.size
    }


    private fun bfs(magicNumber: Int, initial: Pos, destination: Pos?): Pair<Path?, Set<Pos>?> {
        val queue = ArrayDeque<Path>()
        val seen = mutableSetOf<Pos>(initial)
        queue.add(Path(initial))
        while (queue.isNotEmpty()) {
            val p = queue.removeFirst()
            val pos = p.last()
            if (destination == null && p.size > 50) {
                return null to seen
            }
            if (pos == destination) {
                return p to null
            }
            queue.addAll(pos.neighbourCellsReadingOrder().filter { it.isPostive() }.filterNot { it.isWall(magicNumber) }.filter { seen.add(it) }.map { p + it })
        }
        return null to null
    }

    private fun Pos.isPostive() = x >= 0 && y >= 0
    private data class Path(val path: List<Pos>) : List<Pos> by path {
        operator fun plus(pos: Pos): Path = Path(path + pos)

        constructor(p: Pos) : this(listOf(p))
    }

    private fun Pos.isWall(magicNumber: Int) = (x * x + 3 * x + 2 * x * y + y + y * y + magicNumber).countOneBits() % 2 == 1
}

