package no.rodland.advent_2023

import no.rodland.advent.Day
import no.rodland.advent.Pos3D

// template generated: 22/12/2023
// Fredrik Rødland 2023

// https://todd.ginsberg.com/post/advent-of-code/2023/day22/

class Day22(val input: List<String>) : Day<Int, Int, List<Day22.Brick>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        return parsed.size - parsed.structurallySignificant().size
    }

    override fun partTwo(): Int {
        return parsed.structurallySignificant().sumOf { it.topple().size - 1 }
    }

    private fun Brick.topple(): Set<Brick> = buildSet {
        add(this@topple)
        val untoppled = (parsed - this).toMutableSet()
        do {
            val willFall = untoppled
                .filter { it.supportedBy.isNotEmpty() }
                .filter { it.supportedBy.all { brick -> brick in this } }
                .also {
                    untoppled.removeAll(it)
                    addAll(it)
                }
        } while (willFall.isNotEmpty())
    }

    private fun List<Brick>.structurallySignificant(): List<Brick> = filter { brick -> brick.supporting.any { it.supportedBy.size == 1 } }
    private fun List<Brick>.settle(): List<Brick> = buildList {
        this@settle.forEach { brick ->
            var current = brick
            do {
                var settled = false
                val supporters = filter { below -> below.canSupport(current) }
                if (supporters.isEmpty() && !current.onGround()) {
                    val restingPlace = filter { it.z.last < current.z.first - 1 }
                        .maxOfOrNull { it.z.last }?.let { it + 1 } ?: GROUND
                    current = current.fall(restingPlace)
                } else {
                    settled = true
                    supporters.forEach { below -> below.supports(current) }
                    add(current)
                }
            } while (!settled)
        }
    }

    data class Brick(val id: Int, val from: Pos3D, val to: Pos3D) : Comparable<Brick> {
        val x = from.x..to.x
        val y = from.y..to.y
        val z = from.z..to.z

        val supporting = mutableSetOf<Brick>()
        val supportedBy = mutableSetOf<Brick>()


        override fun compareTo(other: Brick): Int =
            z.first - other.z.first

        fun supports(other: Brick) {
            supporting += other
            other.supportedBy += this
        }

        fun canSupport(other: Brick): Boolean = x intersects other.x && y intersects other.y && z.last + 1 == other.z.first
        fun onGround(): Boolean = z.first == GROUND
        fun fall(restingPlace: Int): Brick =
            copy(
                from = Pos3D(from.x, from.y, restingPlace),
                to = Pos3D(to.x, to.y, (restingPlace + (z.last - z.first)))
            )

        private infix fun IntRange.intersects(other: IntRange): Boolean = first <= other.last && last >= other.first
    }


    override fun List<String>.parse(): List<Brick> {
        return mapIndexed { idx, line ->
            val (from, to) = line.split('~')
            Brick(idx, Pos3D(from), Pos3D(to))
        }.sorted().settle()
    }

    override val day = "22".toInt()

    companion object {
        const val GROUND = 1
    }
}
