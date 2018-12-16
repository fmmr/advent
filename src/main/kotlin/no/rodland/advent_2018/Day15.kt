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
                    creature.round(map)
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

    fun init(list: List<String>): Pair<SortedSet<Creature>, List<MutableList<Point>>> {
        val map = list.mapIndexed { y, line ->
            line.mapIndexed { x: Int, c: Char ->
                if (c.isCreature()) {
                    Point(creature = Creature(c.toType(), "${c}_${x}X$y", Pos(x, y)))
                } else {
                    Point(char = c)
                }
            }.toMutableList()
        }

        val creatures = map.flatMap { line ->
            line.mapNotNull { it.creature }
        }.toSortedSet()
        return Pair(creatures, map)
    }

    data class Point(val char: Char? = null, val creature: Creature? = null) {
        fun isWall() = char == WALL
        fun isOpen() = char == OPEN
        fun isCreature(): Boolean = creature != null
    }

    data class Creature(val type: Type, val name: String, var pos: Pos, val power: Int = 3, var hitPoints: Int = 200) : Comparable<Creature> {
        override fun compareTo(other: Creature): Int = pos.compareTo(other.pos)

        fun dead() = hitPoints <= 0

        fun hit(other: Creature) {
            other.hitPoints -= power
        }

        fun neighboorCells(): List<Pos> {
            return listOf(Pos(pos.x + 1, pos.y), Pos(pos.x - 1, pos.y), Pos(pos.x, pos.y + 1), Pos(pos.x, pos.y - 1))
        }

        fun round(map: List<MutableList<Point>>) {
            val enemies = enemies(map)
            val enemyCells = enemies.flatMap { it.neighboorCells() }.distinct()

            val openEnemyCells = enemyCells.map { it to map[Pos(it.x, it.y)] }.filter { it.second.isOpen() }.map { it.first }
            val nearestPoint = openEnemyCells.minBy { pos.distanceTo(it) }!!
            val nextPos = getNextPointToward(nearestPoint)
            println("$this moving to $nextPos")
            move(nextPos, map)
        }

        private fun getNextPointToward(goal: Pos): Pos {

            // TODO nope!  - reading order!


            return if (goal.x > pos.x) {
                Pos(pos.x + 1, pos.y)
            } else if (goal.x < pos.x) {
                Pos(pos.x - 1, pos.y)
            } else if (goal.y > pos.y) {
                Pos(pos.x, pos.y + 1)
            } else if (goal.y < pos.y) {
                Pos(pos.x, pos.y - 1)
            } else error("already at point: $goal  (pos: $pos)")
        }

        fun enemies(map: List<MutableList<Point>>): List<Creature> {
            return map
                    .flatMap { line ->
                        line.mapNotNull { it.creature }
                    }
                    .filter { !it.dead() }
                    .filter { it.type == type.other() }
        }

        fun move(newPos: Pos, map: List<MutableList<Point>>) {
            map[pos] = Point(char = OPEN)
            map[newPos] = Point(creature = this)
            pos = newPos
        }
    }

    data class Pos(val x: Int, val y: Int) : Comparable<Pos> {
        override fun compareTo(other: Pos): Int {
            val yComp = y.compareTo(other.y)
            return if (yComp == 0) {
                x.compareTo(other.x)
            } else yComp
        }

        fun distanceTo(other: Pos): Int {
            // TODO does not account for walls
            return Math.abs(other.x - x) + Math.abs(other.y - y)
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

operator fun List<MutableList<Day15.Point>>.set(pos: Day15.Pos, value: Day15.Point) {
    this[pos.x][pos.y] = value
}

operator fun List<MutableList<Day15.Point>>.get(pos: Day15.Pos): Day15.Point {
    return this[pos.x][pos.y]
}

fun Char.isCreature(): Boolean = this in "GE"

fun Char.toType(): Day15.Type {
    return when (this) {
        'G' -> GOBLIN
        'E' -> ELF
        else -> error("not a vald creature type $this")
    }
}

