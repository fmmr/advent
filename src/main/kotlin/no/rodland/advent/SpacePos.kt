package no.rodland.advent

internal const val ACTIVE = '#'
private fun Char.active() = this == ACTIVE

sealed class SpacePos {
    abstract fun neighbors(): List<SpacePos>
    fun activeNeighbors(space: Map<out SpacePos, Char>): Int = neighbors().mapNotNull { space[it] }.count { it.active() }
}

data class Pos3D(val x: Int, val y: Int, val z: Int) : SpacePos() {
    constructor(triple: Triple<Int, Int, Int>) : this(triple.first, triple.second, triple.third)

    override fun neighbors(): List<SpacePos> {
        return listOf(0, 1, -1)
            .flatMap { dx ->
                listOf(0, 1, -1).flatMap { dy ->
                    listOf(0, 1, -1).map { dz ->
                        Triple(x + dx, y + dy, z + dz)
                    }
                }
            }
            .map { Pos3D(it) }
            .filterNot { it == this }
    }
}

data class Pos4D(val x: Int, val y: Int, val z: Int, val w: Int) : SpacePos() {
    override fun neighbors(): List<SpacePos> {
        return listOf(0, 1, -1)
            .flatMap { dx ->
                listOf(0, 1, -1).flatMap { dy ->
                    listOf(0, 1, -1).flatMap { dz ->
                        listOf(0, 1, -1).map { dw ->
                            Pos4D(x + dx, y + dy, z + dz, w + dw)
                        }
                    }
                }
            }
            .filterNot { it == this }
    }
}
