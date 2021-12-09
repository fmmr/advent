package no.rodland.advent_2019

import no.rodland.advent.Pos

object Day20 {

    val letters = 'A'..'Z'

    fun partOne(list: List<String>): Int {
        val paths = getPaths(list)
        val entrances = getPortals(list)
        val portals = entrances.filterNot { it.key == "AA" }.filterNot { it.key == "ZZ" }
        val portalsMap = portals.flatMap { (_, v) -> listOf(v.first to v.second, v.second to v.first) }.toMap()
        val start = entrances["AA"]!!.first
        val end = entrances["ZZ"]!!.first
        val state = bfs(start, end, paths, portalsMap)
        return state.path.size
    }

    fun partTwo(list: List<String>): Int {
        val paths = getPaths(list)
        val boundX = setOf(2, list.first().length - 3)
        val boundY = setOf(2, list.size - 3)

        val entrances = getPortals(list)
        val portals = entrances.filterNot { it.key == "AA" }.filterNot { it.key == "ZZ" }
        val portalsMap = portals.flatMap { (_, v) -> listOf(v.first to v.second, v.second to v.first) }.toMap()
        val start = entrances["AA"]!!.first
        val end = entrances["ZZ"]!!.first
        val state = bfs2(start, end, paths, boundX, boundY, portalsMap)
        return state.path.size
    }

    private fun bfs(start: Pos, end: Pos, paths: Set<Pos>, portalsMap: Map<Pos, Pos>): State {
        val queue = ArrayDeque(listOf(State(start)))
        val seen = mutableSetOf<Pos>()
        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            if (state.pos == end) {
                return state
            }
            val jump = portalsMap[state.pos]
            val neighboors = state.pos.neighboorCellsUDLR().filter { it in paths }.filter { seen.add(it) } + if (jump != null) listOf(jump) else emptyList()
            queue.addAll(neighboors.map { State(it, state.path + it) })
        }
        error("No path found")
    }


    private fun bfs2(start: Pos, end: Pos, paths: Set<Pos>, outerX: Set<Int>, outerY: Set<Int>, portalsMap: Map<Pos, Pos>): State2 {
        val queue = ArrayDeque(listOf(State2(PosLevel(start, 0))))
        val seen = mutableSetOf<PosLevel>()
        val endPos = PosLevel(end, 0)
        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            if (state.pos == endPos) {
                return state
            }

            val jump = portalsMap[state.pos.pos]
            val neighboors = (state.pos.pos.neighboorCellsUDLR().filter { it in paths }.map { PosLevel(it, state.pos.level) }.filter { seen.add(it) } + if (jump != null) listOf(PosLevel(state.pos, jump, outerX, outerY)) else emptyList())
                .filter { it.level >= 0 }

            queue.addAll(neighboors.map { State2(it, state.path + it) })
        }
        error("No path found")
    }

    data class State(val pos: Pos, val path: List<Pos> = emptyList())
    data class State2(val pos: PosLevel, val path: List<PosLevel> = emptyList())
    data class PosLevel(val pos: Pos, val level: Int) {
        constructor(from: PosLevel, to: Pos, outerX: Set<Int>, outerY: Set<Int>) : this(to, from.level + if (from.pos.x in outerX || from.pos.y in outerY) -1 else 1)
    }

    private fun getPortals(list: List<String>): Map<String, Pair<Pos, Pos>> {
        val handled = mutableSetOf<Pos>()
        val portals = mutableListOf<Pair<String, Pos>>()
        list.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                val pos = Pos(x, y)
                if (pos !in handled) {
                    if (c in letters) {
                        val above = list.get(pos.above())
                        val below = list.get(pos.below())
                        val belowbelow = list.get(pos.below().below())
                        val right = list.get(pos.right())
                        val rightright = list.get(pos.right().right())
                        val left = list.get(pos.left())
                        if (below in letters && belowbelow == '.') {
                            handled.add(pos)
                            handled.add(pos.below())
                            portals.add("$c$below" to pos.below().below())
                        }
                        if (below in letters && above == '.') {
                            handled.add(pos)
                            handled.add(pos.below())
                            portals.add("$c$below" to pos.above())
                        }
                        if (right in letters && rightright == '.') {
                            handled.add(pos)
                            handled.add(pos.right())
                            portals.add("$c$right" to pos.right().right())
                        }
                        if (right in letters && left == '.') {
                            handled.add(pos)
                            handled.add(pos.right())
                            portals.add("$c$right" to pos.left())
                        }
                    }
                }
            }
        }
        return portals.groupBy { it.first }.mapValues { (_, v) -> v.first().second to v.last().second }
    }

    private fun getPaths(list: List<String>): Set<Pos> {
        val paths = list.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, c ->
                if (c == '.') Pos(x, y) else null
            }
        }.toSet()
        return paths
    }

    fun List<String>.get(pos: Pos) = if (pos.isInGrid(this[0].length, this.size)) this[pos.y][pos.x] else null

}