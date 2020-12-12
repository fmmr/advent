package no.rodland.advent_2020

import no.rodland.advent.Pos
import no.rodland.advent_2020.Day12.turn
import kotlin.math.abs

object Day12 {
    fun partOne(list: List<String>): Int {
        val instructions = list.map { it.substring(0, 1) to it.substring(1).toInt() }
        var currentHeading = "E"
        val endPosition = instructions.fold(Pos(0, 0)) { pos, (instr, times) ->
            when (instr) {
                "R", "L" -> {
                    currentHeading = currentHeading.turn(instr, times);
                    pos
                }
                "F" -> pos.next(currentHeading, times, true)
                "N", "S", "W", "E" -> pos.next(instr, times)
                else -> error("Unable to understand $pos, $instr $times")
            }
        }
        return endPosition.manhattan()
    }

    fun partTwo(list: List<String>): Int {
        val instructions = list.map { it.substring(0, 1) to it.substring(1).toInt() }
        var wayPointPosition = Pos(10, 1)
        var shipPosition = Pos(0, 0)

        var iteration = 0
        println("${iteration++}        ship: $shipPosition   wp: $wayPointPosition")
        instructions.forEach { (instr, times) ->
            when (instr) {
                "R", "L" -> {
                    val numberRights = rights(instr, times)
                    wayPointPosition = wayPointPosition.rotateRight(numberRights)
                }
                "F" -> (1..times).forEach { shipPosition += wayPointPosition }
                "N", "S", "W", "E" -> wayPointPosition = wayPointPosition.next(instr, times, false)
                else -> error("Unable to understand $instr $times")
            }

            println("${iteration++}  $instr$times   ship: $shipPosition   wp: $wayPointPosition")
        }

        return shipPosition.manhattan()
    }


    // 10 units east and 4 units north
    // R 90
    // 4 units east and 10 units south

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
