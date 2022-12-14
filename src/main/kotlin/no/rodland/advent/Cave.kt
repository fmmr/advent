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
