package no.rodland.advent

class IntGrid(val list: List<IntArray>) : List<IntArray> by list {

    fun getRegion(lowPos: Pos): Set<Pos> {
        val explore = ArrayDeque<Pos>()
        val visited = mutableSetOf<Pos>()
        explore.add(lowPos)
        while (!explore.isEmpty()) {
            explore.removeFirst().let { pos ->
                if (visited.add(pos)) {
                    explore.addAll(neighbours(pos).filter { this[it] < 9 })
                }
            }
        }
        return visited
    }

    private operator fun IntGrid.get(pos: Pos): Int = this[pos.y][pos.x]

    fun lowPoints(): List<Pair<Pos, Int>> {
        return flatMapIndexed { y, line ->
            line.mapIndexed { x, value -> Pos(x, y) to value }
                .filter { (pos, value) -> neighbours(pos).all { neighboor -> this[neighboor] > value } }
        }
    }

    fun neighbours(p: Pos) = p.neighbourCellsUDLR().filter { it.isInGrid(this) }
    fun neighbourCellsAllEight(p: Pos) = p.neighbourCellsAllEight().filter { it.isInGrid(this) }

    fun increase(i: Int = 1): IntGrid {
        return IntGrid(map { ar -> IntArray(ar.size) { value -> i + ar[value] } })
    }

    override fun toString(): String {
        return list.map {
            it.joinToString("")
        }.joinToString("\n")
    }

    companion object {
        private fun toGrid(strings: List<String>) = List(strings.size) { row ->
            strings[row].toCharArray().map { it.digitToInt() }.let { ints ->
                IntArray(ints.size) { value -> ints[value] }
            }
        }

        fun fromInput(input: List<String>) = IntGrid(toGrid(input))
    }

}