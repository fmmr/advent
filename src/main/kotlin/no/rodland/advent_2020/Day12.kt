package no.rodland.advent_2020

import no.rodland.advent.Pos

object Day12 {
    fun partOne(list: List<String>): Int {
        val instructions = list.map { it.substring(0, 1) to it.substring(1).toInt() }
        var currentHeading = "E"
        val endPosition = instructions.fold(Pos(0, 0)) { pos, (instr, times) ->
            when (instr) {
                "R", "L" -> {
                    currentHeading = currentHeading.turn(instr, times)
                    pos
                }
                "F" -> pos.next(currentHeading, times)
                "N", "S", "W", "E" -> pos.next(instr, times)
                else -> error("Unable to understand $pos, $instr $times")
            }
        }
        return endPosition.manhattan()
    }

    fun partTwo(list: List<String>): Int {
        var wayPointPosition = Pos(10, -1)
        var shipPosition = Pos(0, 0)
        list.map { it.substring(0, 1) to it.substring(1).toInt() }.forEach { (instr, times) ->
            when (instr) {
                "R", "L" -> wayPointPosition = wayPointPosition.rotateRight(rights(instr, times))
                "F" -> (1..times).forEach { shipPosition += wayPointPosition }
                "N", "S", "W", "E" -> wayPointPosition = wayPointPosition.next(instr, times)
                else -> error("Unable to understand $instr $times")
            }
        }
        return shipPosition.manhattan()
    }

    private val directions = listOf("E", "S", "W", "N")

    fun String.turn(dir: String, times: Int): String {
        val rights = rights(dir, times)
        return directions[(directions.indexOf(this) + rights) % 4]
    }

    private fun rights(dir: String, times: Int): Int {
        val turns = times / 90
        return when (dir) {
            "R" -> turns
            "L" -> (4 - turns)
            else -> error("unable to turn $dir")
        }
    }
}
