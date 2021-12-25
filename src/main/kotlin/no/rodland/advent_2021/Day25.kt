package no.rodland.advent_2021

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day25 {
    fun partOne(list: List<String>): Int {
        val map = list.mapIndexed { y: Int, s: String ->
            s.mapIndexed { x, c ->
                val type = Type.fromChar(c)
                Cucumber(Pos(x, y), type)
            }
        }
        val twoEqualMaps = generateSequence(0 to map) { acc ->
            val (counter, map1) = acc
            counter + 1 to step(map1)
        }.zipWithNext().first { it.first.second == it.second.second }
        return twoEqualMaps.second.first
    }

    private fun step(map: List<List<Cucumber>>): List<List<Cucumber>> {
        return stepOneDir(stepOneDir(map, Type.EAST), Type.SOUTH)
    }

    private fun stepOneDir(map: List<List<Cucumber>>, type: Type): List<List<Cucumber>> {
        val east = map.get(type)
        val toMove = east.flatMap {
            val next = it.next(map)
            when (map[next.y][next.x].type) {
                Type.EMPTY -> listOf(it.pos to it.copy(type = Type.EMPTY), next to it.copy(pos = next))
                else -> emptyList()
            }
        }.toMap()
        return map.move(toMove)
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun List<List<Cucumber>>.get(index: Type): List<Cucumber> {
        return flatMap { l -> l.mapNotNull { c -> if (c.type == index) c else null } }
    }

    private fun List<List<Cucumber>>.move(toMove: Map<Pos, Cucumber>): List<List<Cucumber>> {
        return mapIndexed { y, l ->
            l.mapIndexed { x, c ->
                toMove[Pos(x, y)] ?: c
            }
        }
    }

    fun Pos.next(type: Type, map: List<List<*>>): Pos = when (type) {
        Type.SOUTH -> if (y == map.size - 1) Pos(x, 0) else Pos(x, y + 1)
        Type.EAST -> if (x == map[0].size - 1) Pos(0, y) else Pos(x + 1, y)
        Type.EMPTY -> error("no need to ask for next for empty")
    }

    data class Cucumber(val pos: Pos, val type: Type) {
        fun next(map: List<List<*>>): Pos {
            val next = pos.next(type, map)
            return next
        }
    }

    enum class Type {
        SOUTH, EAST, EMPTY;

        companion object {
            fun fromChar(c: Char) = when (c) {
                '.' -> EMPTY
                '>' -> EAST
                'v' -> SOUTH
                else -> error("unknown cucumber $c")
            }
        }
    }
}


