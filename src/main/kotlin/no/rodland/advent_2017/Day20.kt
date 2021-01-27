package no.rodland.advent_2017

import no.rodland.advent.Pos3D

@Suppress("UNUSED_PARAMETER")
object Day20 {

// 299 too high

    fun partOne(list: List<String>): Int {
        val particles = list.mapIndexed { idx, str -> Particle(str, idx.toString().padStart(4, '0')) }
        val acc = particles.sortedWith(compareBy { it.acc.manhattan() })
        val lowestAcc = acc[0].acc.manhattan()
        val particlesWithLowestAcc = acc.filter { it.acc.manhattan() == lowestAcc }
        return particlesWithLowestAcc[0].name.toInt()
    }

    fun partTwo(list: List<String>): Int {
        val particles = list.mapIndexed { idx, str -> Particle(str, idx.toString().padStart(4, '0')) }
        return generateSequence(particles) {
            it.move().filterCollisions()
        }.map { it.size }.take(50).last()
    }

    private fun List<Particle>.filterCollisions(): List<Particle> {
        return this.groupBy { it.pos }.filterValues { it.size == 1 }.flatMap { it.value }
    }

    private fun List<Particle>.move(): List<Particle> {
        return map { it.move() }
    }


    val regex = """p=<(.+),(.+),(.+)>, v=<(.+),(.+),(.+)>, a=<(.+),(.+),(.+)>""".toRegex()

    data class Particle(val name: String, val pos: Pos3D, val velocity: Pos3D, val acc: Pos3D) {
        fun move(): Particle {
            val newVel = velocity + acc
            val newPos = pos + newVel
            return Particle(name, newPos, newVel, acc)
        }

        constructor(str: String, name: String, match: MatchResult.Destructured = regex.find(str)!!.destructured) : this(name,
                Pos3D(match.component1().trim().toInt(), match.component2().trim().toInt(), match.component3().trim().toInt()),
                Pos3D(match.component4().trim().toInt(), match.component5().trim().toInt(), match.component6().trim().toInt()),
                Pos3D(match.component7().trim().toInt(), match.component8().trim().toInt(), match.component9().trim().toInt())
        )
    }
}

