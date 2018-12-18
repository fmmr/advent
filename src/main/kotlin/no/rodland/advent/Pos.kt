package no.rodland.advent

data class Pos(val x: Int, val y: Int) : Comparable<Pos> {
    override fun compareTo(other: Pos): Int {
        val yComp = y.compareTo(other.y)
        return if (yComp == 0) {
            x.compareTo(other.x)
        } else yComp
    }

    fun distanceTo(other: Pos): Int {
        return Math.abs(other.x - x) + Math.abs(other.y - y)
    }

    fun neighboorCellsReadingOrder(): List<Pos> {
        return listOf(
                Pos(x, y - 1),
                Pos(x - 1, y),
                Pos(x + 1, y),
                Pos(x, y + 1)
        )
    }

    fun neighboorCellsAllEight(): List<Pos> {
        return listOf(
                Pos(x, y - 1),
                Pos(x - 1, y),
                Pos(x + 1, y),
                Pos(x, y + 1),
                Pos(x - 1, y - 1),
                Pos(x + 1, y - 1),
                Pos(x - 1, y + 1),
                Pos(x + 1, y + 1)
        )
    }

    fun getDownNeighbors(): List<Pos> {
        return listOf(
                Pos(x - 1, y),
                Pos(x + 1, y),
                Pos(x, y + 1)
        )
    }

    fun isPositive(): Boolean = x >= 0 && y >= 0
    fun above(): Pos = Pos(x, y - 1)
    fun below(): Pos = Pos(x, y + 1)
    fun left(): Pos = Pos(x - 1, y)
    fun right(): Pos = Pos(x + 1, y)
}