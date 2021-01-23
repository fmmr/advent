package no.rodland.advent_2020

object Day03 {
    fun partOne(list: List<String>): Int {
        val maze = list.map { it.toCharArray() }.toTypedArray()
        return countTrees(maze, 3, 1)
    }

    fun partTwo(list: List<String>): Long {
        val maze = list.map { it.toCharArray() }.toTypedArray()
        val slopes = listOf(3 to 1, 1 to 1, 5 to 1, 7 to 1, 1 to 2)
        return slopes.map { (dx, dy) -> countTrees(maze, dx, dy).toLong() }.reduce { n1, n2 -> n1 * n2 }
    }

    private fun countTrees(maze: Array<CharArray>, dx: Int, dy: Int): Int {
        val width = maze[0].size
        val height = maze.size
        val count = generateSequence(Pos(0, 0)) { it.next(dx, dy, width) }
            .takeWhile { it.y < height }
            //.onEach { println("$it, dx: $dx, dy: $dy, width: $width, height: $height") }
            .count { maze.isTree(it) }
        println("NUM TREES: $count  dx: $dx, dy: $dy, width: $width, height: $height")
        return count
    }

    private fun Array<CharArray>.isTree(pos: Pos) = this[pos.y][pos.x] == '#'

    private data class Pos(val x: Int, val y: Int) {
        fun next(dX: Int, dY: Int, maxX: Int) = Pos((x + dX) % maxX, y + dY)
    }
}
