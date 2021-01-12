package no.rodland.advent_2016

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day01 {
    fun partOne(list: List<String>): Int {
        val start = Pos(0, 0)
        val end = list
                .map { Cmd(it.trim()) }
                .fold(Dir.N to start) { p, command ->
                    val dir = p.first.turn(command.dir)
                    dir to p.second.next(dir.toString(), command.length)
                }
        return end.second.distanceTo(start)
    }
    // 269 too high

    fun partTwo(list: List<String>): Int {
        val map = mutableSetOf<Pos>()
        val start = Pos(0, 0)
        val travel = list
                .asSequence()
                .map { Cmd(it.trim()) }
                .runningFold(Dir.N to listOf(start)) { p, command ->
                    val dir = p.first.turn(command.dir)
                    val path = p.second.last().path(dir.toString(), command.length)
                    dir to path
                }
                .flatMap { it.second }
                .first { !map.add(it) }
        return travel.distanceTo(start)
    }

    data class Cmd(val dir: String, val length: Int) {
        constructor(str: String) : this(str.substring(0, 1), str.substring(1).toInt())
    }

    enum class Dir {
        N, S, W, E;

        fun turn(d: String): Dir {
            return when (this) {
                N -> when (d) {
                    "R" -> E
                    "L" -> W
                    else -> error("cannot turn $d")
                }
                S -> when (d) {
                    "R" -> W
                    "L" -> E
                    else -> error("cannot turn $d")
                }
                W -> when (d) {
                    "R" -> N
                    "L" -> S
                    else -> error("cannot turn $d")
                }
                E -> when (d) {
                    "R" -> S
                    "L" -> N
                    else -> error("cannot turn $d")
                }
            }
        }
    }

}


