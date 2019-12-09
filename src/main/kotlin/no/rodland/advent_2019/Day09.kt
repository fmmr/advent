package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.runBlocking

object Day09 {

    fun partOne(program: List<Int>): List<Int> {
        val def = GlobalScope.async {
            runProgram(program)
        }
        return getValueFromDeferredList(def)
    }


    @Suppress("DeferredResultUnused")
    private suspend fun runProgram(program: List<Int>): List<Int> {
        val input = Channel<Int>(20)
        val output = Channel<Int>(20)
        // set ut channels initially
        input.send(0)
        input.send(0)

        // start each amplifier (justDoIt will do a launch)
        IntCodeComputerCR(program, input, output).justDoIt()
        val outputList = mutableListOf<Int>()
        while (true) {
            try {
                val value = output.receive()
                outputList.add(value)
                println("GOT OUT: $value")
            } catch (e: ClosedReceiveChannelException) {
                break
            }
        }

        return outputList
    }

    // hacky - i guess
    private fun getValue(deferred: Deferred<Int>): Int {
        var value: Int = NO_OUTPUT_VALUE
        runBlocking { value = deferred.await() }
        return value
    }


    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<Int>): Int {
        return 2
    }

}