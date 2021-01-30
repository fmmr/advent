package no.rodland.advent

enum class Direction(val c: Char, val num: Long) {
    NORTH('N', 1L), SOUTH('S', 2), WEST('W', 3), EAST('E', 4);

    fun goBack(): Direction = when (this) {
        NORTH -> SOUTH
        SOUTH -> NORTH
        WEST -> EAST
        EAST -> WEST
    }

    fun left(): Direction = when (this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }

    fun right(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
}