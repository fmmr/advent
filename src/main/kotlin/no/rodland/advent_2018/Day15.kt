package no.rodland.advent_2018

import no.rodland.advent_2018.Day15.Type.ELF
import no.rodland.advent_2018.Day15.Type.GOBLIN
import java.util.*

object Day15 {
    const val WALL: Char = '#'
    const val OPEN: Char = '.'


    fun partOne(list: List<String>): Int {
        val (creatures, map) = init(list)
        var count = 0
        repeat(10) {
            creatures.sorted().filter { !it.dead() }.forEach {
                if (!it.dead()) {
//                    it.move(map)
                    // end state - only one type is alive
                    val alive = creatures.partition { it.dead() }.second.groupBy { it.type }
                    if (alive[GOBLIN].isNullOrEmpty()) {
                        return stats(alive[ELF]!!, count)
                    }
                    if (alive[ELF].isNullOrEmpty()) {
                        return stats(alive[GOBLIN]!!, count)
                    }
                } else {
                    println("DEAD (cannot move): $it")
                }
            }
            count++
        }

        return 2
    }

    fun stats(creatures: List<Creature>, rounds: Int): Int = creatures.sumBy { it.hitPoints } * rounds

    fun init(list: List<String>): Pair<TreeSet<Creature>, List<MutableList<Char>>> {
        val creatures = sortedSetOf<Creature>()
        val map: List<MutableList<Char>> = list.mapIndexed { y, line ->
            creatures.addAll(line
                    .mapIndexed { x, c -> c to (x to y) }
                    .filter { it.first.isCreature() }
                    .map {
                        Creature(
                                it.first.toType(),
                                "${it.first}_${it.second.first}X${it.second.second}",
                                it.second.first,
                                it.second.second
                        )
                    })
            line.map { if (it.isCreature()) '.' else it }.toMutableList()
        }
        return Pair(creatures, map)
    }

    data class Creature(val type: Type, val name: String, var x: Int, var y: Int, val power: Int = 3, var hitPoints: Int = 200) : Comparable<Creature> {
        override fun compareTo(other: Creature): Int {
            val yComp = y.compareTo(other.y)
            return if (yComp == 0) {
                x.compareTo(other.x)
            } else yComp
        }

        fun dead() = hitPoints <= 0

        fun hit(other: Creature) {
            other.hitPoints -= power
        }
    }

    enum class Type {
        ELF, GOBLIN;

        fun other(): Type {
            return when (this) {
                ELF -> GOBLIN
                GOBLIN -> ELF
            }
        }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}

fun Char.isCreature(): Boolean = this in "GE"

fun Char.toType(): Day15.Type {
    return when (this) {
        'G' -> GOBLIN
        'E' -> ELF
        else -> error("not a vald creature type $this")
    }
}

