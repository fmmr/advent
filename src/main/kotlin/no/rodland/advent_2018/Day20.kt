package no.rodland.advent_2018

import no.rodland.advent.Pos

object Day20 {
    fun partOne(re: String): Int {
        val map = mutableMapOf<Pos, Char>(Pos(0, 0) to 'X')
        traverse(re.substring(1..(re.length - 2)), map, Pos(0, 0))
        val numDoors = map.count { it.value in "-|" }
        return numDoors
    }

    fun traverse(re: String, visited: MutableMap<Pos, Char>, pos: Pos): Pos {
        if (re.startsWith("^") && re.endsWith("$")) {
            return traverse(re.substring(1, re.length), visited, pos)
        }
        var taken = 0
        val oki = re.takeWhile { it.isDirection() }.fold(pos) { p, c ->
            taken++
            val tmpP = p.getNext(c)
            tmpP.getSidesAfterMoving(c).forEach { visited[it] = '#' }
            visited[tmpP] = c.getDoor()
            val next = tmpP.getNext(c)
            visited[next] = '.'
            next
        }
//        val rest = re.substring(taken)

        return oki
    }

    fun partTwo(re: String): Int {
        return 2
    }
}

fun Map<Pos, Char>.toMap(): List<CharArray> {
    val minMax = Pos.getMinMax(this.keys)
    val xDelta = 0 - minMax.first.first
    val yDelta = 0 - minMax.second.first
    val maxX = minMax.first.second + xDelta
    val maxY = minMax.second.second + yDelta
    return (0..maxY).map { y ->
        (0..maxX).map { x ->
            this[Pos(x - xDelta, y - yDelta)] ?: '?'
        }.toCharArray()
    }

}

private fun Char.getDoor(): Char = when (this) {
    in "WE" -> '|'
    in "NS" -> '-'
    else -> error("not a valid dir: $this")
}

private fun String.isDirection(): Boolean = this.all { it.isDirection() }

private fun Char.isDirection(): Boolean = this in "NWES"
