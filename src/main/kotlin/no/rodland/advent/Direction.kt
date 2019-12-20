package no.rodland.advent

enum class Direction(val c: Char, val num: Long) {
    NORTH('N', 1L), SOUTH('S', 2), WEST('W', 3), EAST('E', 4);

    fun goBack(): Direction = when (this) {
        NORTH -> SOUTH
        SOUTH -> NORTH
        WEST -> EAST
        EAST -> WEST
    }
}