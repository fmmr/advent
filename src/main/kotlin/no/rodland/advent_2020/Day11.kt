package no.rodland.advent_2020

import no.rodland.advent.Pos

typealias Maze = Array<CharArray>

object Day11 {
    fun partOne(list: List<String>): Int {
        val grid: Maze = list.map { it.toCharArray() }.toTypedArray()
        return findSeatsTaken(grid) { g: Maze -> part1Run(g, grid[0].size, grid.size) }
    }

    fun partTwo(list: List<String>): Int {
        val grid: Maze = list.map { it.toCharArray() }.toTypedArray()
        return findSeatsTaken(grid) { g: Maze -> part1Run(g, grid[0].size, grid.size) }
    }

    private fun findSeatsTaken(grid: Maze, iterationFunction: (Maze) -> Maze): Int {
        val lastMaze = generateSequence(grid, iterationFunction)
            .zipWithNext()
            .takeWhile { p -> !p.first.contentDeepEquals(p.second) }
            .last()
        return lastMaze.second.flatMap { it.map { it.isTaken() } }.filter { it }.count()
    }

//    fun above(): Pos = Pos(x, y - 1)
//    fun below(): Pos = Pos(x, y + 1)
//    fun left(): Pos = Pos(x - 1, y)
//    fun right(): Pos = Pos(x + 1, y)
//    fun nw(): Pos = Pos(x - 1, y - 1)
//    fun ne(): Pos = Pos(x + 1, y - 1)
//    fun sw(): Pos = Pos(x - 1, y + 1)
//    fun se(): Pos = Pos(x + 1, y + 1)

    private fun part1Run(maze: Maze, width: Int, height: Int): Maze {
        val newMaze = maze.copy()
        val changes = sequence {
            (0 until height).forEach { y ->
                (0 until width).forEach { x ->
                    val p = Pos(x, y)
                    val seat = newMaze[p]
                    if (seat.notFloor()) {
                        val occupied = p.neighboorCellsAllEight().filter { it.isInGrid(width, height) }.count { newMaze[it] == '#' }
                        if (seat.isTaken()) {
                            if (occupied >= 4) {
                                yield(p to 'L')
                            }
                        } else if (occupied == 0) {
                            yield(p to '#')
                        }
                    }
                }
            }
        }.toList()
        changes.forEach {
            newMaze[it.first] = it.second
        }
        return newMaze
    }

    private fun Maze.copy() = Array(size) { idx -> this[idx].copyOf() }
    private operator fun Maze.set(pos: Pos, value: Char) = value.also { this[pos.y][pos.x] = it }
    private operator fun Maze.get(pos: Pos): Char = this[pos.y][pos.x]
    private fun Char.isTaken(): Boolean = this == '#'
    private fun Char.isSeat(): Boolean = this == 'L'
    private fun Char.isFloor(): Boolean = this == '.'
    private fun Char.notFloor(): Boolean = !isFloor()

}



