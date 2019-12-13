package no.rodland.advent_2019

import kotlinx.coroutines.runBlocking

object Day09 {

    fun partOne(program: List<String>, seed: Long): List<Long> {
        return runBlocking {
            runProgram(program, seed)
        }
    }

    @Suppress("DeferredResultUnused")
    private suspend fun runProgram(program: List<String>, seed: Long): List<Long> {
        val list = mutableListOf<Long>()
        IntCodeComputer().runSuspend(program, { seed }, { list.add(it) })
        return list
    }
}