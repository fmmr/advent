package no.rodland.advent_2015

import no.rodland.advent.Pos

typealias Maze = Array<CharArray>

object Day18 {
    fun partOne(list: List<String>, iterations: Int): Int {
        val grid: Maze = list.toMaze()
        val alwaysOn = emptyList<Pos>()
        return findLights(grid, iterations) { g: Maze -> iteration(g, grid[0].size, grid.size, alwaysOn) }
    }

    fun partTwo(list: List<String>, iterations: Int = 100): Int {
        val grid: Maze = list.toMaze()
        val alwaysOn = listOf(Pos(0, 0), Pos(0, grid.size - 1), Pos(grid.size - 1, 0), Pos(grid.size - 1, grid.size - 1))
        return findLights(grid, iterations) { g: Maze -> iteration(g, grid[0].size, grid.size, alwaysOn) }
    }

    private fun findLights(grid: Maze, iterations: Int, iterationFunction: (Maze) -> Maze): Int {
        val lastMaze = generateSequence(grid, iterationFunction).drop(iterations).first()
        return lastMaze.flatMap { it.map { c -> c.isLit() } }.count { it }
    }

    private fun iteration(maze: Maze, width: Int, height: Int, alwaysOn: List<Pos>): Maze {
        val newMaze = maze.copy()
        val add = alwaysOn.map { it to '#' }
        val changes = (sequence {
            (0 until height).forEach { y ->
                (0 until width).forEach { x ->
                    val p = Pos(x, y)
                    val light = newMaze[p]
                    val litNeighbours = p.neighbourCellsAllEight().filter { it.isInGrid(maze[0].size, maze.size) }.count { newMaze[it].isLit() }
                    yield(p to when (litNeighbours) {
                        2 -> if (light.isLit()) '#' else '.'
                        3 -> '#'
                        else -> '.'
                    })
                }
            }
        }.toList().filterNot { it.first in alwaysOn } + add)
        changes.forEach {
            newMaze[it.first] = it.second
        }
        return newMaze
    }

    private fun List<String>.toMaze() = map { it.toCharArray() }.toTypedArray()
    private fun Maze.copy() = Array(size) { idx -> this[idx].copyOf() }
    private operator fun Maze.set(pos: Pos, value: Char) = value.also { this[pos.y][pos.x] = it }
    private operator fun Maze.get(pos: Pos): Char = this[pos.y][pos.x]
    private fun Char.isLit(): Boolean = this == '#'
}
