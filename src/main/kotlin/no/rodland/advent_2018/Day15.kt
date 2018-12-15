package no.rodland.advent_2018

import no.rodland.advent_2018.Day15.Type.ELF
import no.rodland.advent_2018.Day15.Type.GOBLIN
import java.util.*

object Day15 {
    const val WALL: Char = '#'
    const val OPEN: Char = '.'


    fun partOne(list: List<String>): Int {
        val (creatures, map) = init(list)
        repeat(10) { round ->
            creatures.sorted().filter { !it.dead() }.forEach { creature ->
                if (!creature.dead()) {
                    creature.move(creatures.filter { !it.dead() }.filter { other -> creature.type != other.type }, map)
                    // end state - only one type is alive
                    val alive = creatures.partition { it.dead() }.second.groupBy { it.type }
                    if (alive[GOBLIN].isNullOrEmpty()) {
                        return stats(alive[ELF]!!, round)
                    }
                    if (alive[ELF].isNullOrEmpty()) {
                        return stats(alive[GOBLIN]!!, round)
                    }
                } else {
                    println("DEAD (cannot move), round $round: $creature")
                }
            }
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
                                Pos(it.second.first, it.second.second)
                        )
                    })
            line.map { if (it.isCreature()) '.' else it }.toMutableList()
        }
        return Pair(creatures, map)
    }

    data class Creature(val type: Type, val name: String, var pos: Pos, val power: Int = 3, var hitPoints: Int = 200) : Comparable<Creature> {
        override fun compareTo(other: Creature): Int = pos.compareTo(other.pos)

        fun dead() = hitPoints <= 0

        fun hit(other: Creature) {
            other.hitPoints -= power
        }

        fun move(enemies: List<Creature>, map: List<MutableList<Char>>) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    data class Pos(val x: Int, val y: Int) : Comparable<Pos> {
        override fun compareTo(other: Pos): Int {
            val yComp = y.compareTo(other.y)
            return if (yComp == 0) {
                x.compareTo(other.x)
            } else yComp
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

