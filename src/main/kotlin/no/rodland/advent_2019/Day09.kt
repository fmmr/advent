package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import java.math.BigInteger
import java.math.BigInteger.ONE

object Day09 {

    fun partOne(program: List<String>): List<BigInteger> {
        val def = GlobalScope.async {
            runProgram(program)
        }
        return getValueFromDeferredList(def)
    }


    @Suppress("DeferredResultUnused")
    private suspend fun runProgram(program: List<String>): List<BigInteger> {
        val input = Channel<BigInteger>(20)
        val output = Channel<BigInteger>(20)
        // set ut channels initially
        input.send(ONE)
//        input.send(ZERO)

        // start each computer (justDoIt will do a launch)
        IntCodeComputerCR(program, input, output).justDoIt()
        return output.toList()
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Int {
        return 2
    }

}