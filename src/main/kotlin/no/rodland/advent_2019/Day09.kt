package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList

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

        // start each computer (justDoIt will do a launch)
        IntCodeComputerCR(program, input, output).justDoIt()
        return output.toList()
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<Int>): Int {
        return 2
    }

}