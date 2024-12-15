package no.rodland.advent


typealias Cave = Array<CharArray>
typealias Path = List<Pos>

operator fun Cave.set(pos: Pos, value: Char) {
    this[pos.y][pos.x] = value
}

operator fun Cave.contains(pos: Pos): Boolean =
    pos.x >= 0 && pos.x < this[0].size && pos.y >= 0 && pos.y < this.size

operator fun Cave.get(pos: Pos): Char {
    return this[pos.y][pos.x]
}

fun Cave.getOrNull(pos: Pos): Char? = if (pos in this) this[pos] else null

fun Cave.copy() = this.map { row -> row.map { it }.toCharArray() }.toTypedArray<CharArray>()

fun fromMap(map: Map<Pos, Char>): Cave {
    val maxX = map.keys.maxOf { it.x }
    val maxY = map.keys.maxOf { it.y }
    return Array(maxY) { y ->
        CharArray(maxX) { x ->
            map[Pos(x, y)] ?: '.'
        }
    }
}

@Suppress("UNUSED_PARAMETER")
fun Cave.print(minX: Int = 0, minY: Int = 0, maxX: Int = first().size, maxY: Int = size) {
    forEach { ca ->
        ca.forEach {
            print(it)
        }
        println()
    }
    println()
}
