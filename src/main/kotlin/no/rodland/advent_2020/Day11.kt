package no.rodland.advent_2020

import no.rodland.advent.Pos

typealias Maze = Array<CharArray>

object Day11 {
    fun partOne(list: List<String>): Int {
        val grid: Maze = list.toMaze()
        val findNeighbours: (Pos, Maze /* = kotlin.Array<kotlin.CharArray> */) -> List<Pos> = { p, maze -> p.neighboorCellsAllEight().filter { it.isInGrid(maze[0].size, maze.size) } }
        return findSeatsTaken(grid) { g: Maze -> iteration(g, grid[0].size, grid.size, 4, findNeighbours) }
    }

    fun partTwo(list: List<String>): Int {
        val grid: Maze = list.toMaze()
        val findNeighbours: (Pos, Maze /* = kotlin.Array<kotlin.CharArray> */) -> List<Pos> = ::findNeighboursPart2
        return findSeatsTaken(grid) { g: Maze -> iteration(g, grid[0].size, grid.size, 5, findNeighbours) }
    }


    fun findNeighboursPart2(p: Pos, maze: Maze): List<Pos> {
        val directions = listOf("N", "S", "W", "E", "SE", "NE", "SW", "NW")
        return directions.mapNotNull { dir ->
            var found = false
            generateSequence(p.next(dir)) { next ->
                when {
                    !next.isInGrid(maze[0].size, maze.size) -> null
                    found -> null
                    maze.notFloor(next) -> {
                        found = true
                        next
                    }
                    else -> next.next(dir)
                }
            }.filter { it.isInGrid(maze[0].size, maze.size) }.filter { maze.notFloor(it) }.lastOrNull()
        }
    }

    private fun findSeatsTaken(grid: Maze, iterationFunction: (Maze) -> Maze): Int {
        val lastMaze = generateSequence(grid, iterationFunction)
            .zipWithNext()
            .takeWhile { p -> !p.first.contentDeepEquals(p.second) }
            .last()
        return lastMaze.second.flatMap { it.map { c -> c.isTaken() } }.count { it }
    }

    private fun iteration(maze: Maze, width: Int, height: Int, tolerant: Int, findNeighbours: (Pos, Maze) -> List<Pos>): Maze {
        val newMaze = maze.copy()
        val changes = sequence {
            (0 until height).forEach { y ->
                (0 until width).forEach { x ->
                    val p = Pos(x, y)
                    val seat = newMaze[p]
                    if (seat.notFloor()) {
                        val occupied = findNeighbours(p, maze).count { newMaze[it] == '#' }
                        if (seat.isTaken()) {
                            if (occupied >= tolerant) {
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

    private fun List<String>.toMaze() = map { it.toCharArray() }.toTypedArray()
    private fun Maze.notFloor(p: Pos) = this[p].notFloor()
    private fun Maze.copy() = Array(size) { idx -> this[idx].copyOf() }
    private operator fun Maze.set(pos: Pos, value: Char) = value.also { this[pos.y][pos.x] = it }
    private operator fun Maze.get(pos: Pos): Char = this[pos.y][pos.x]
    private fun Char.isTaken(): Boolean = this == '#'
    private fun Char.isFloor(): Boolean = this == '.'
    private fun Char.notFloor(): Boolean = !isFloor()
}




