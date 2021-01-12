package no.rodland.advent_2016

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day01 {
    fun partOne(list: List<String>): Int {
        val start = Pos(0, 0)
        var dir = Dir.N
        val end = list
                .map { Cmd(it.trim()) }
                .fold(start) { pos, command ->
                    dir = dir.turn(command.dir)
                    pos.next(dir.toString(), command.length)
                }
        return end.distanceTo(start)
    }

    fun partTwo(list: List<String>): Int {
        return 2
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


