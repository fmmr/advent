package no.rodland.advent

import kotlin.math.min

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

    fun all(): List<Pair<Pos, Int>> {
        return flatMapIndexed { y, line ->
            line.mapIndexed { x, value -> Pos(x, y) to value }
        }
    }

    fun neighbours(p: Pos) = p.neighbourCellsUDLR().filter { it.isInGrid(this) }
    fun neighbourCellsAllEight(p: Pos) = p.neighbourCellsAllEight().filter { it.isInGrid(this) }

    fun increase(i: Int = 1): IntGrid {
        return IntGrid(map { ar -> IntArray(ar.size) { value -> i + ar[value] } })
    }

    override fun toString(): String {
        return list.joinToString("\n") { it.joinToString("") }
    }

    // colour: given a pos - which colour on background (0.0 - 1.0)
    // char: given a pos - which char to print
    fun print(colour: (Pos) -> Double = { -1.0 }, char: (Pos) -> Char = { p -> Char(get(p)) }, foreground: String = "\u001B[30m\u001B[1m") {
        val ansiReset = "\u001b[0m"
        val defaultBackground = "\u001b[47m"
        val ansiBgColors = listOf(
            "\u001b[44m", "\u001b[104m", "\u001b[46m", "\u001b[106m",
            "\u001b[42m", "\u001b[102m", "\u001b[43m", "\u001b[103m",
            "\u001b[45m", "\u001b[105m", "\u001b[41m", "\u001b[101m",
        )

        fun getAnsiBgColor(p: Pos): String = colour(p).let {
            if (it < 0.0) {
                defaultBackground
            } else {
                ansiBgColors[min((colour(p) * ansiBgColors.size).toInt(), ansiBgColors.size - 1)]
            }
        }

        print(foreground)
        mapIndexed { y, arr ->
            arr.forEachIndexed() { x, _ ->
                val p = Pos(x, y)
                print(getAnsiBgColor(p) + char(p))
            }
            println()
        }
        print(ansiReset)
    }

    companion object {
        private fun toGrid(strings: List<String>) = List(strings.size) { row ->
            strings[row].toCharArray().map { it.digitToInt() }.let { ints ->
                IntArray(ints.size) { value -> ints[value] }
            }
        }

        private fun toGridFromChar(strings: List<String>) = List(strings.size) { row ->
            strings[row].toCharArray().map { it.code }.let { ints ->
                IntArray(ints.size) { value -> ints[value] }
            }
        }

        fun fromInput(input: List<String>) = IntGrid(toGrid(input))
        fun fromChar(input: List<String>) = IntGrid(toGridFromChar(input))
    }
}