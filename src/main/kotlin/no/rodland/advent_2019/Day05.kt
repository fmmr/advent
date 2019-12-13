package no.rodland.advent_2019

import kotlinx.coroutines.runBlocking

object Day05 {
    fun partOne(input: List<String>, start: Long = 1): Long {
        return runBlocking { runAmp(input, start) }
    }

    suspend fun runAmp(program: List<String>, first: Long): Long {
        val list = mutableListOf<Long>()
        IntCodeComputer().runSuspend(program, { first }, { list.add(it) })
        return list.last()
    }
}