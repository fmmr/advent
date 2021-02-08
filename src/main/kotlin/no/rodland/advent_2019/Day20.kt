package no.rodland.advent_2019

import no.rodland.advent.Pos

object Day20 {

    val letters = 'A'..'Z'
    fun partOne(list: List<String>): Int {
        val paths = list.flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, c ->
                if (c == '.') Pos(x, y) else null
            }
        }.toSet()
        val entrances = getPortals(list)
        val start = entrances["AA"]!!.first
        val end = entrances["ZZ"]!!.first
        val portals = entrances.filterNot { it.key == "AA" }.filterNot { it.key == "ZZ" }
        val portalsMap = portals.flatMap { (_, v) -> listOf(v.first to v.second, v.second to v.first) }.toMap()
        val state = bfs(start, end, paths, portalsMap)
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
            val neighboors = state.pos.neighboorCellsNDLR().filter { it in paths }.filter { seen.add(it) } + if (jump != null) listOf(jump) else emptyList()
            queue.addAll(neighboors.map { State(it, state.path + it) })
        }
        error("No path found")
    }

    data class State(val pos: Pos, val path: List<Pos> = emptyList())

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

    fun List<String>.get(pos: Pos) = if (pos.isInGrid(this[0].length, this.size)) this[pos.y][pos.x] else null

    fun partTwo(list: List<String>): Int {
        return 2
    }
}