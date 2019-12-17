package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day17 {
    fun partOne(list: List<String>): Int {
        getMap(list)
        return 2
    }

    private fun getMap(program: List<String>): Int {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val job = IntCodeComputer().launch(program, input, output)


        val game = GlobalScope.launch {
            while (true) {
                try {
                    val y = output.receive().toChar()
                    println("got: $y")
                } catch (e: Exception) {
                    println("Channel closed - computer stopped")
                    break
                }
            }
        }

        runBlocking {
            job.join()
            game.join()
        }
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}