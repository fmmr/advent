package no.rodland.advent_2015

object Day06 {
    fun partOne(instructions: List<String>): Int {
        val grid = Array(1000) { BooleanArray(1000) { false } }
        instructions.map { Instruction.fromString(it) }.forEach {
            grid.part1(it)
        }
        return grid.countLights()
    }


    fun partTwo(instructions: List<String>): Int {
        val grid = Array(1000) { IntArray(1000) { 0 } }
        instructions.map { Instruction.fromString(it) }.forEach {
            grid.part2(it)
        }
        return grid.countBrightness()
    }

    private fun Array<BooleanArray>.part1(instr: Instruction) {
        ((instr.from.first)..(instr.to.first)).map { x ->
            ((instr.from.second)..(instr.to.second)).map { y ->
                this[x][y] = when (instr.command) {
                    Cmd.ON -> true
                    Cmd.OFF -> false
                    Cmd.TOGGLE -> !this[x][y]
                }
            }
        }
    }

    private fun Array<IntArray>.part2(instr: Instruction) {
        ((instr.from.first)..(instr.to.first)).map { x ->
            ((instr.from.second)..(instr.to.second)).map { y ->
                this[x][y] = when (instr.command) {
                    Cmd.ON -> this[x][y] + 1
                    Cmd.OFF -> maxOf(this[x][y] - 1, 0)
                    Cmd.TOGGLE -> this[x][y] + 2
                }
            }
        }
    }

    private fun Array<BooleanArray>.countLights(): Int = map { list -> list.count { it } }.sum()
    private fun Array<IntArray>.countBrightness(): Int = map { list -> list.sum() }.sum()

    private fun String.toPair() = split(",").let { it[0].toInt() to it[1].toInt() }

    data class Instruction(val command: Cmd, val from: Pair<Int, Int>, val to: Pair<Int, Int>) {
        companion object {
            fun fromString(str: String): Instruction {
                val (cmdFrom, to) = str.split(" through ")
                val lastSpace = cmdFrom.lastIndexOf(" ")
                val from = cmdFrom.substring(lastSpace + 1)
                val cmd = when {
                    str.contains("off") -> Cmd.OFF
                    str.contains("on") -> Cmd.ON
                    str.contains("toggle") -> Cmd.TOGGLE
                    else -> error("not able to parse command from $str")
                }
                return Instruction(cmd, from.toPair(), to.toPair())
            }
        }
    }

    enum class Cmd {
        ON, OFF, TOGGLE
    }
}


