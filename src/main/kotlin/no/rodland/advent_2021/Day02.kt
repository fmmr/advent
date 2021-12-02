package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day02 {
    fun partOne(list: List<String>): Int {
        return list.getFinalPosition().product1()
    }

    fun partTwo(list: List<String>): Long {
        return list.getFinalPosition().product()
    }

    private fun List<String>.getFinalPosition() = map { Command(it) }
        .fold(Pos2()) { acc, command ->
            acc + command
        }


    private data class Command(val direction: Dir, val num: Int) {
        constructor(input: String) : this(input.split(" ")[0].toDir(), input.split(" ")[1].toInt())
    }

    private data class Pos2(val horisontal: Int = 0, val aim: Int = 0, val depth: Long = 0L) {
        operator fun plus(command: Command): Pos2 {
            return when (command.direction) {
                Dir.UP -> copy(aim = aim - command.num)
                Dir.DOWN -> copy(aim = aim + command.num)
                Dir.FORWARD -> copy(horisontal = horisontal + command.num, depth = depth + aim * command.num)
            }
        }

        fun product() = horisontal * depth
        fun product1() = horisontal * aim
    }


    private enum class Dir {
        UP, DOWN, FORWARD;
    }

    private fun String.toDir(): Dir = Dir.valueOf(this.uppercase())
}
