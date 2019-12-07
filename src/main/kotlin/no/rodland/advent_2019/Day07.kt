package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

object Day07 {
    fun partOne(program: List<Int>): Int {
        return permute(0..4).map { runAmplifiersPart1(program, it) }.max()!!
    }

    @ExperimentalCoroutinesApi
    fun partTwo(program: List<Int>): Int {
        return permute(5..9).map { runBlocking { partTwo(program, it) } }.max()!!
    }

    @Suppress("DeferredResultUnused")
    private suspend fun partTwo(program: List<Int>, phases: List<Int>): Int {
        val ea = Channel<Int>(20)
        val ab = Channel<Int>(20)
        val bc = Channel<Int>(20)
        val cd = Channel<Int>(20)
        val de = Channel<Int>(20)

        // set ut channels initially
        coroutineScope {
            ea.send(phases[0])
            ab.send(phases[1])
            bc.send(phases[2])
            cd.send(phases[3])
            de.send(phases[4])
            ea.send(0)
        }

        // start each amplifier (justDoIt will do a launch)
        IntCodeComputerCR(program, ea, ab).justDoIt()
        IntCodeComputerCR(program, ab, bc).justDoIt()
        IntCodeComputerCR(program, bc, cd).justDoIt()
        IntCodeComputerCR(program, cd, de).justDoIt()
        val deferred = IntCodeComputerCR(program, de, ea).justDoIt()
        return getValue(deferred)
    }

    private fun getValue(heisan: Deferred<Int>): Int {
        var value: Int = NO_OUTPUT_VALUE
        runBlocking { value = heisan.await() }
        return value
    }


    private fun permute(range: IntRange): List<List<Int>> {
        return range.flatMap { p0 ->
            range.flatMap { p1 ->
                range.flatMap { p2 ->
                    range.flatMap { p3 ->
                        range.map { p4 ->
                            listOf(p0, p1, p2, p3, p4)
                        }
                    }
                }
            }
        }
                .distinct()
                .filterNot { it.toSet().size != it.size }
    }

    fun runAmplifiersPart1(program: List<Int>, phases: List<Int>): Int {
        val resA = IntCodeComputer(program, mutableListOf(phases[0], 0)).runProgram()
        val resB = IntCodeComputer(program, mutableListOf(phases[1], resA)).runProgram()
        val resC = IntCodeComputer(program, mutableListOf(phases[2], resB)).runProgram()
        val resD = IntCodeComputer(program, mutableListOf(phases[3], resC)).runProgram()
        val resE = IntCodeComputer(program, mutableListOf(phases[4], resD)).runProgram()
        return resE
    }
}

