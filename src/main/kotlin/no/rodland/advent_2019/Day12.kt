package no.rodland.advent_2019

object Day12 {
    fun partOne(list: List<List<Int>>, steps: Int): Int {
        val moons = runSim(listOf(Moon(list[0]), Moon(list[1]), Moon(list[2]), Moon(list[3])))
        return moons.take(steps).last().map { it.energy() }.sum()
    }

    fun partTwoSimple(list: List<List<Int>>): Int {
        val moons = runSim(listOf(Moon(list[0]), Moon(list[1]), Moon(list[2]), Moon(list[3])))
        val seen: MutableSet<List<Moon>> = mutableSetOf()
        for (state in moons) {
            if (!seen.add(state)) {
                break
            }
        }
        return seen.size
    }

    fun partTwo(list: List<List<Int>>): Int {
        val moons = runSim(listOf(Moon(list[0]), Moon(list[1]), Moon(list[2]), Moon(list[3])))
        val seen: MutableSet<List<Moon>> = mutableSetOf()
        for (state in moons) {
            if (!seen.add(state)) {
                break
            }
        }
        return seen.size
    }

    private fun runSim(moons: List<Moon>): Sequence<List<Moon>> {
        var moonsTmp = moons
        return sequence {
            while (true) {
                moonsTmp = applygravityAndVelocity(moonsTmp)
                yield(moonsTmp)
            }
        }
    }

    private fun applygravityAndVelocity(moons: List<Moon>): List<Moon> {
        val newVelocities: List<Pos3D> = applyGravity(moons)
        return moons.zip(newVelocities) { moon, velocity -> moon.applyVelocity(velocity) }
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
                    .fold(velocity) { vel, moon -> Pos3D(vel.x + moon.x, vel.y + moon.y, vel.z + moon.z) }
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