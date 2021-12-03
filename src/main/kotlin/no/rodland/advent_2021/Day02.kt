package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day02 {
    fun partOne(list: List<String>): Int {
        return list.getFinalPosition().productPartOne()
    }

    fun partTwo(list: List<String>): Long {
        return list.getFinalPosition().productPartTwo()
    }

    private fun List<String>.getFinalPosition() = map { Command(it) }
        .fold(Position()) { acc, command ->
            acc + command
        }


    private data class Command(val direction: Dir, val num: Int) {
        constructor(input: String) : this(input.split(" ")[0].toDir(), input.split(" ")[1].toInt())
    }

    private data class Position(val horisontal: Int = 0, val aim: Int = 0, val depth: Long = 0L) {
        operator fun plus(command: Command): Position {
            return when (command.direction) {
                Dir.UP -> copy(aim = aim - command.num)
                Dir.DOWN -> copy(aim = aim + command.num)
                Dir.FORWARD -> copy(horisontal = horisontal + command.num, depth = depth + aim * command.num)
            }
        }
        fun productPartTwo() = horisontal * depth
        fun productPartOne() = horisontal * aim
    }


    private enum class Dir {
        UP, DOWN, FORWARD;
    }

    private fun String.toDir(): Dir = Dir.valueOf(this.uppercase())
}
