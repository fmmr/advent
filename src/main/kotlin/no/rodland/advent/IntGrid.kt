package no.rodland.advent

import asInt

class IntGrid(val list: List<IntArray>) : List<IntArray> by list {

    fun getRegion(lowPos: Pos): Set<Pos> {
        val explore = ArrayDeque<Pos>()
        val visited = mutableSetOf<Pos>()
        explore.add(lowPos)
        while (!explore.isEmpty()) {
            explore.removeFirst().let { pos ->
                if (visited.add(pos)) {
                    explore.addAll(neighboors(pos).filter { this[it] < 9 })
                }
            }
        }
        return visited
    }

    operator fun IntGrid.get(pos: Pos): Int = this[pos.y][pos.x]

    fun lowPoints(): List<Pair<Pos, Int>> {
        return flatMapIndexed { y, line ->
            line.mapIndexed { x, value -> Pos(x, y) to value }
                .filter { (pos, value) -> neighboors(pos).all { neighboor -> this[neighboor] > value } }
        }
    }

    fun neighboors(p: Pos) = p.neighboorCellsUDLR().filter { it.isInGrid(this) }

    fun increase(i: Int = 1): IntGrid {
        return IntGrid(map { ar -> IntArray(ar.size) { value -> i + ar[value] } })
    }

    override fun toString(): String {
        return list.map {
            it.joinToString("")
        }.joinToString("\n")
    }

    companion object {
        fun fromInput(input: List<String>) = IntGrid(input.toGrid())
    }


}

fun List<String>.toGrid() = List(size) { row ->
    this[row].toCharArray().map { it.asInt() }.let { ints ->
        IntArray(ints.size) { value -> ints[value] }
    }
}
