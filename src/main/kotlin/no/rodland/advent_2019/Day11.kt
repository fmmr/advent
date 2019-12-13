package no.rodland.advent_2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos

@ExperimentalCoroutinesApi
object Day11 {
    const val BLACK = 0L
    const val WHITE = 1L
    const val TURN_LEFT = 0L

    fun part1(program: List<String>): Int {
        return paint(program, BLACK).size
    }

    fun partTwo(program: List<String>): Int {
        val paint = paint(program, WHITE).filter { it.value == BLACK }
        val minX = paint.map { it.key.x }.min()!!
        val minY = paint.map { it.key.y }.min()!!
        val maxX = paint.map { it.key.x }.max()!!
        val maxY = paint.map { it.key.y }.max()!!

        (minY..maxY).forEach { y ->
            (0..(maxX - minX)).forEach { x -> print(printable(paint.getOrDefault(Pos(x, y), WHITE))) }
            println()
        }
        return 2
    }

    private fun printable(it: Long): Char = if (it == BLACK) ' ' else '\u2588'

    private fun paint(program: List<String>, background: Long): MutableMap<Pos, Long> {
        val map = mutableMapOf<Pos, Long>()
        // start each amplifier (justDoIt will do a launch)
        var currentPos = RobDir(Pos(0, 0), Dir.UP)
        // set ut channels initially

        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val intCodeComputer = IntCodeComputer()
        val job = intCodeComputer.launch(program, input, output)

        val receiver = GlobalScope.launch {
            input.send(background)
            while (job.isActive) {
                try {
                    val newColour = output.receive()
                    val newDir = output.receive()
                    map[currentPos.pos] = newColour
                    currentPos = currentPos.next(newDir)
                    input.send(map.getOrDefault(currentPos.pos, background))
                } catch (e: Exception) {
                    println("Channel closed - computer stopped")
                }
            }
        }
        runBlocking {
            job.join()
            receiver.join()
        }
        return map
    }

    data class RobDir(val pos: Pos, val dir: Dir) {
        fun next(turn: Long): RobDir {
            val newDir = dir.next(turn)
            return RobDir(nextPos(newDir), newDir)
        }

        private fun nextPos(newDir: Dir): Pos {
            return when (newDir) {
                Dir.UP -> Pos(pos.x, pos.y - 1)
                Dir.DOWN -> Pos(pos.x, pos.y + 1)
                Dir.LEFT -> Pos(pos.x - 1, pos.y)
                Dir.RIGHT -> Pos(pos.x + 1, pos.y)
            }
        }
    }

    enum class Dir(val c: Char) {
        //< ^ > v
        UP('^'),
        DOWN('v'), LEFT('<'), RIGHT('>');

        fun next(turn: Long): Dir {
            return when (this) {
                UP -> if (turn == TURN_LEFT) LEFT else RIGHT
                DOWN -> if (turn == TURN_LEFT) RIGHT else LEFT
                LEFT -> if (turn == TURN_LEFT) DOWN else UP
                RIGHT -> if (turn == TURN_LEFT) UP else DOWN
            }
        }
    }
}