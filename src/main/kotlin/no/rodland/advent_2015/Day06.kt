package no.rodland.advent_2015

object Day06 {
    fun partOne(instructions: List<String>): Int {
        val grid = Array(1000) { BooleanArray(1000) { false } }
        instructions.map { Instruction.fromString(it) }.forEach { grid.part1(it) }
        return grid.countLights()
    }


    fun partTwo(instructions: List<String>): Int {
        val grid = Array(1000) { IntArray(1000) { 0 } }
        instructions.map { Instruction.fromString(it) }.forEach { grid.part2(it) }
        return grid.countBrightness()
    }

    private fun Array<BooleanArray>.part1(instr: Instruction) {
        ((instr.from.first)..(instr.to.first)).map { x ->
            ((instr.from.second)..(instr.to.second)).map { y ->
                this[x][y] = instr.command.part1(this[x][y])
            }
        }
    }

    private fun Array<IntArray>.part2(instr: Instruction) {
        ((instr.from.first)..(instr.to.first)).map { x ->
            ((instr.from.second)..(instr.to.second)).map { y ->
                this[x][y] = instr.command.part2(this[x][y])
            }
        }
    }

    private fun Array<BooleanArray>.countLights(): Int = map { list -> list.count { it } }.sum()
    private fun Array<IntArray>.countBrightness(): Int = map { list -> list.sum() }.sum()

    private fun String.toPair() = split(",").let { it[0].toInt() to it[1].toInt() }

    data class Instruction(val command: Cmd, val from: Pair<Int, Int>, val to: Pair<Int, Int>) {
        companion object {
            val regex = """(.*) (\d+),(\d+) through (\d+),(\d+)""".toRegex()
            fun fromString(str: String): Instruction {
                val cmd = when {
                    str.contains("off") -> Cmd.OFF
                    str.contains("on") -> Cmd.ON
                    str.contains("toggle") -> Cmd.TOGGLE
                    else -> error("not able to parse command from $str")
                }
                val find = regex.find(str)!!
                return Instruction(cmd, find.toInt(2) to find.toInt(3), find.toInt(4) to find.toInt(5))
            }

            private fun MatchResult.toInt(i: Int) = groups[i]!!.value.toInt()
        }
    }

    enum class Cmd(val part1: (Boolean) -> Boolean, val part2: (Int) -> Int) {
        ON({ true }, { i -> i + 1 }),
        OFF({ false }, { i -> maxOf(i - 1, 0) }),
        TOGGLE({ b -> !b }, { i -> i + 2 });
    }
}


