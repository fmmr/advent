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
    const val TURN_LEFT = 0L

    fun partOne(program: List<String>): Int {
        val map = mutableMapOf<Pos, Long>()
        // start each amplifier (justDoIt will do a launch)
        var currentPos = RobDir(Pos(0, 0), Dir.UP)
        // set ut channels initially

        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val job = IntCodeComputer(program, input, output).run()

        GlobalScope.launch {
            input.send(BLACK)
            while (job.isActive) {
                try {
                    val newColour = output.receive()
                    val newDir = output.receive()
                    map[currentPos.pos] = newColour
                    currentPos = currentPos.next(newDir)
                    input.send(map.getOrDefault(currentPos.pos, BLACK))
                } catch (e: Exception) {
                    println("Channel closed - computer stopped")
                }
            }
        }
        runBlocking {
            job.join()
        }
        return map.size
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

    fun partTwo(list: List<String>): Int {
        return 2
    }
}