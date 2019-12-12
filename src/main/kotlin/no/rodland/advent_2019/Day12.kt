package no.rodland.advent_2019

object Day12 {
    fun partOne(list: List<List<Int>>, steps: Int): Int {
        var io = Moon(list[0])
        var europa = Moon(list[1])
        var ganymede = Moon(list[2])
        var callisto = Moon(list[3])

        (1..steps).map {
            val newVelocities: List<Pos3D> = applyGravity(listOf(io, europa, ganymede, callisto))
            io = io.applyVelocity(newVelocities[0])
            europa = europa.applyVelocity(newVelocities[1])
            ganymede = ganymede.applyVelocity(newVelocities[2])
            callisto = callisto.applyVelocity(newVelocities[3])
        }
        return listOf(io, europa, ganymede, callisto).map { it.energy() }.sum()
    }


    fun partTwo(list: List<List<Int>>): Int {
        var io = Moon(list[0])
        var europa = Moon(list[1])
        var ganymede = Moon(list[2])
        var callisto = Moon(list[3])
        val previousSeen = mutableSetOf(listOf(io, europa, ganymede, callisto))
        var i = 0
        var found = false

        while (!found) {
            val newVelocities: List<Pos3D> = applyGravity(listOf(io, europa, ganymede, callisto))
            io = io.applyVelocity(newVelocities[0])
            europa = europa.applyVelocity(newVelocities[1])
            ganymede = ganymede.applyVelocity(newVelocities[2])
            callisto = callisto.applyVelocity(newVelocities[3])
            found = !previousSeen.add(listOf(io, europa, ganymede, callisto))
            i += 1
        }
        return i
    }

    private fun applyGravity(moons: List<Moon>): List<Pos3D> {
        return moons.map { moon -> moon.applyGravity(moons.filterNot { it == moon }) }
    }


    data class Moon(val pos: Pos3D, val velocity: Pos3D) {
        constructor(list: List<Int>) : this(Pos3D(list[0], list[1], list[2]), Pos3D(0, 0, 0))

        fun energy() = pos.energy() * velocity.energy()
        fun applyVelocity(newVelocity: Pos3D): Moon = Moon(Pos3D(pos.x + newVelocity.x, pos.y + newVelocity.y, pos.z + newVelocity.z), newVelocity)

        fun applyGravity(moons: List<Moon>): Pos3D {
            return moons
                    .map { this.applyGravity(it) }
                    .fold(velocity) { acc, it -> Pos3D(acc.x + it.x, acc.y + it.y, acc.z + it.z) }
        }

        fun applyGravity(other: Moon): Pos3D {
            val x = when {
                pos.x > other.pos.x -> -1
                pos.x < other.pos.x -> 1
                else -> 0
            }
            val y = when {
                pos.y > other.pos.y -> -1
                pos.y < other.pos.y -> 1
                else -> 0
            }
            val z = when {
                pos.z > other.pos.z -> -1
                pos.z < other.pos.z -> 1
                else -> 0
            }
            return Pos3D(x, y, z)
        }


    }

    data class Pos3D(val x: Int, val y: Int, val z: Int) {
        fun energy(): Int = Math.abs(x) + Math.abs(y) + Math.abs(z)
    }

}