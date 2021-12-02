package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day02 {
    fun partOne(list: List<String>): Int {
        return list
            .map { Command(it) }
            .fold(Pos()) { acc, command ->
                acc + command
            }
            .product()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    private data class Command(val direction: Dir, val num: Int) {
        constructor(input: String) : this(input.split(" ")[0].toDir(), input.split(" ")[1].toInt())
    }


    private data class Pos(val horisontal: Int = 0, val vertical: Int = 0) {
        operator fun plus(command: Command): Pos {
            return when (command.direction) {
                Dir.UP -> copy(vertical = vertical - command.num)
                Dir.DOWN -> copy(vertical = vertical + command.num)
                Dir.FORWARD -> copy(horisontal = horisontal + command.num)
            }
        }

        fun product() = horisontal * vertical
    }

    private fun String.toDir(): Dir = Dir.valueOf(this.uppercase())

    private enum class Dir {
        UP, DOWN, FORWARD;
    }
}
