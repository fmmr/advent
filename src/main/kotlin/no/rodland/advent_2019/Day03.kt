package no.rodland.advent_2019


object Day03 {
    val origo = Pos(0, 0)

    fun partOne(wire1: List<String>, wire2: List<String>): Int {
        val pos1: List<Pos> = path(wire1)
        val pos2: List<Pos> = path(wire2)
        val intersects = pos1.intersect(pos2).filterNot { it == Pos(0, 0) }
        return intersects.map { origo.distanceTo(it) }.min()!!
    }

    private fun path(wire: List<String>): List<Pos> {
        return wire.fold(listOf(origo), { acc, op ->
            val last = acc.last()
            acc.dropLast(1) + (last..(last.pos(Op(op))))
        })
    }

    fun partTwo(wire1: List<String>, wire2: List<String>): Int {
        val pos1: List<Pos> = path(wire1)
        val pos2: List<Pos> = path(wire2)
        val intersects = pos1.intersect(pos2).filterNot { it == Pos(0, 0) }
        return intersects.map { pos1.indexOf(it) + pos2.indexOf(it) }.min()!!
    }
}


data class Pos(val x: Int, val y: Int) {
    fun distanceTo(pos: Pos): Int = Math.abs(x - pos.x) + Math.abs(y - pos.y)

    fun pos(op: Op): Pos = when (op.dir) {
        'L' -> Pos(x - op.len, y)
        'R' -> Pos(x + op.len, y)
        'D' -> Pos(x, y - op.len)
        'U' -> Pos(x, y + op.len)
        else -> error("Unknown direction: ${op.dir}")
    }

    operator fun rangeTo(pos: Pos): List<Pos> {
        return if (x != pos.x && y != pos.y) {
            TODO("rangeTo Not implemented for bi-directional movement")
        } else if (x != pos.x) {
            if (x < pos.x) {
                (x..pos.x).map { Pos(it, y) }
            } else {
                (x downTo pos.x).map { Pos(it, y) }
            }
        } else {
            if (y < pos.y) {
                (y..pos.y).map { Pos(x, it) }
            } else {
                (y downTo pos.y).map { Pos(x, it) }
            }
        }
    }

}

data class Op(val dir: Char, val len: Int) {
    constructor(code: String) : this(code.first(), code.substring(1).toInt())
}
