package no.rodland.advent_2020

object Day03 {
    fun partOne(list: List<String>): Int {
        val maze = list.map { it.toCharArray() }.toTypedArray()
        val width = maze[0].size
        val height = maze.size
        return (1 until height).asSequence()
            .runningFold(Pos(0, 0), { acc, _ -> acc.next(width, 3, 1) })
            //.onEach { println(it) }
            .map { maze[it.y][it.x] }
            .count { it == '#' }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Pos(val x: Int, val y: Int) {
        fun next(maxX: Int, dX: Int, dY: Int): Pos {
            val potX = x + dX
            val newX = if (potX >= maxX) {
                potX - maxX
            } else {
                potX
            }
            return Pos(newX, y + dY)
        }
    }
}