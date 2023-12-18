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

    operator fun times(num: Int): List<Direction> = (0..<num).map { _ -> this }
    operator fun times(num: Long): List<Direction> = (0..<num).map { _ -> this }

    companion object{
        fun fromUDLR(c: Char): Direction = when (c) {
            'U' -> NORTH
            'D' -> SOUTH
            'L' -> WEST
            'R' -> EAST
            else -> throw IllegalArgumentException("Unknown direction: $c")
        }
    }
}