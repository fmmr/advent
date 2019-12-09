package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList

object Day09 {

    fun partOne(program: List<String>, seed: Long): List<Long> {
        val def = GlobalScope.async {
            runProgram(program, seed)
        }
        return getValueFromDeferredList(def)
    }


    @Suppress("DeferredResultUnused")
    private suspend fun runProgram(program: List<String>, seed: Long): List<Long> {
        val input = Channel<Long>(2000)
        val output = Channel<Long>(2000)
        // set ut channels initially
        input.send(seed)
        input.send(0L)  // only to support test with old program from day 7

        // start each computer (justDoIt will do a launch)
        IntCodeComputerCR(program, input, output).justDoIt()
        return output.toList()
    }
}