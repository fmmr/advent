package no.rodland.advent_2019

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

object Day19 {
    fun partOne(program: List<String>): Long {
        return (0..49).flatMap { x ->
            (0..49).map { y ->
                getPos(program, x.toLong(), y.toLong())
            }
        }.sum()
    }

    private fun getPos(liprogramt: List<String>, x: Long, y: Long): Long {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val program = liprogramt.toMutableList()

        IntCodeComputer().launch(program, input, output)
        return runBlocking {
            input.send(x)
            input.send(y)
            output.receive()
        }
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }
}