package no.rodland.advent_2023

import no.rodland.advent.Cave
import no.rodland.advent.Day
import no.rodland.advent.Pos
import kotlin.math.max

// template generated: 23/12/2023
// Fredrik Rødland 2023

// inspired: https://www.youtube.com/watch?v=NTLYL7Mg2jU
class Day23(val input: List<String>) : Day<Int, Int, Cave> {

    private val cave = input.parse()
    private val start = Pos(input[0].indexOf('.'), 0)
    private val end = Pos(input.last().indexOf('.'), input.size - 1)


    override fun partOne(): Int {
        return solve { p -> p.neighbourCellsWithSlope() }
    }

    override fun partTwo(): Int {
        return solve { p -> p.neighbourCellsUDLR().filter { it in cave } }
    }

    private fun solve(walkingFunction: (Pos) -> List<Pos>): Int {
        val crossroads = findCrossroads() + start + end
        val graph = constructGraph(crossroads, walkingFunction)
        return dfs(start, graph, mutableSetOf())
    }

    private fun dfs(pos: Pos, graph: Map<Pos, MutableList<Pair<Pos, Int>>>, seen: MutableSet<Pos>): Int {
        if (pos == end) {
            return 0
        }
        val list = graph[pos]!!
        seen.add(pos)
        val m = list.fold(Integer.MIN_VALUE) { acc: Int, pair: Pair<Pos, Int> ->
            val (neighbour, length) = pair
            if (neighbour !in seen) {
                max(acc, dfs(neighbour, graph, seen) + length)
            } else {
                acc
            }
        }
        seen.remove(pos)
        return m

    }

    private fun constructGraph(crossroads: List<Pos>, walkingFunction: (Pos) -> List<Pos>): Map<Pos, MutableList<Pair<Pos, Int>>> {
        return crossroads.associateWith { mutableListOf<Pair<Pos, Int>>() }.also { graph ->
            crossroads.forEach { crossroad ->
                val stack = ArrayDeque<Pair<Pos, Int>>()
                stack.addFirst(crossroad to 0)
                val seen = mutableSetOf(crossroad)
                while (stack.isNotEmpty()) {
                    val (checkPos, checkLength) = stack.removeLast()
                    if (checkLength > 0 && checkPos in crossroads) {
                        graph[crossroad]!!.add(checkPos to checkLength)
                        continue
                    }
                    walkingFunction(checkPos).forEach { neighbour ->
                        if (neighbour !in seen && cave[neighbour] != '#') {
                            stack.addFirst(neighbour to checkLength + 1)
                            seen.add(neighbour)
                        }
                    }
                }
            }
        }
    }


    private fun Pos.neighbourCellsWithSlope(): List<Pos> {
        return when (cave[this]) {
            '<' -> listOf(Pos(x - 1, y))
            '>' -> listOf(Pos(x + 1, y))
            '^' -> listOf(Pos(x, y - 1))
            'v' -> listOf(Pos(x, y + 1))
            '.' -> neighbourCellsUDLR()
            else -> error("unable to find neighbour for ${cave[this]}")
        }.filter { it in cave }
    }


    private fun findCrossroads(): List<Pos> {
        return cave.flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                val pos = Pos(x, y)
                when {
                    c == '#' -> null
                    pos.neighbourCellsUDLR().filter { it in cave }.count { cave[it] != '#' } >= 3 -> pos
                    else -> null
                }
            }
        }.filterNotNull()
    }

    operator fun Cave.contains(pos: Pos): Boolean = pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size
    operator fun Cave.get(pos: Pos): Char = this[pos.y][pos.x]


    override fun List<String>.parse(): Cave {
        return map { line ->
            line.toCharArray()
        }.toTypedArray()
    }

    override val day = "23".toInt()
}

