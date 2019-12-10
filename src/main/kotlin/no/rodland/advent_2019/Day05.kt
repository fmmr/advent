package no.rodland.advent_2019

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.runBlocking

object Day05 {
    fun partOne(input: List<String>, start: Long = 1): Long {
        return runBlocking { runAmp(input, start) }
    }

    suspend fun runAmp(program: List<String>, first: Long): Long {
        val inCh = Channel<Long>(20)
        val outCh = Channel<Long>(20)
        inCh.send(first)
        IntCodeComputer(program, inCh, outCh).run()
        return outCh.toList().last()
    }
}