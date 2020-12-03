package no.rodland.advent_2020

object Day03 {
    fun partOne(list: List<String>): Int {
        val maze = list.map { it.toCharArray() }.toTypedArray()
        return numberTreesPart2(maze, 3, 1)
    }

    fun partTwo(list: List<String>): Long {
        val maze = list.map { it.toCharArray() }.toTypedArray()
        return listOf(3 to 1, 1 to 1, 5 to 1, 7 to 1, 1 to 2).map { (dx, dy) -> numberTreesPart2(maze, dx, dy) }.fold(1) { acc, n -> acc * n }
    }

    private fun numberTreesPart2(maze: Array<CharArray>, dx: Int, dy: Int): Int {
        val width = maze[0].size
        val height = maze.size
        val count = generateSequence(Pos(0, 0)) { p -> p.next(width, dx, dy) }
            .takeWhile { it.y < height }
            //.onEach { println("$it, dx: $dx, dy: $dy, width: $width, height: $height") }
            .count { maze[it.y][it.x] == '#' }

        println("NUM TREES: $count  dx: $dx, dy: $dy, width: $width, height: $height")
        return count
    }

    data class Pos(val x: Int, val y: Int) {
        fun next(maxX: Int, dX: Int, dY: Int) = Pos((x + dX) % maxX, y + dY)
    }
}

//    private fun numberTreesPart1(maze: Array<CharArray>, dx: Int, dy: Int): Int {
//        val width = maze[0].size
//        val height = maze.size
//        val count = (1 until height).asSequence()
//            .runningFold(Pos(0, 0), { acc, _ -> acc.next(width, dx, dy) })
//            .filter { it != Pos(-1, -1) }
//            //.onEach { println("$it, dx: $dx, dy: $dy, width: $width, height: $height") }
//            .map { maze[it.y][it.x] }
//            .count { it == '#' }
//        return count
//    }

