package no.rodland.advent_2019

import kotlinx.coroutines.runBlocking

object Day07 {
    fun partOne(program: List<Int>): Int {
        // TODO re-write to Use IntCodeComputerCR

        return permute(0..4).map { runAmplifiersPart1(program, it) }.max()!!
    }

    fun partTwo(program: List<Int>): Int {
        return permute(5..9).map { runBlocking { runAmplifiersPart2(program, it) } }.max()!!
    }

    @Suppress("DeferredResultUnused")
    private suspend fun runAmplifiersPart2(program: List<Int>, phases: List<Int>): Int {
// TODO re-write to Use IntCodeComputerCR (and enable tests)

//        val ea = Channel<Int>(20)
//        val ab = Channel<Int>(20)
//        val bc = Channel<Int>(20)
//        val cd = Channel<Int>(20)
//        val de = Channel<Int>(20)
//
//        // set ut channels initially
//        ea.send(phases[0])
//        ab.send(phases[1])
//        bc.send(phases[2])
//        cd.send(phases[3])
//        de.send(phases[4])
//
//        // send initial value
//        ea.send(0)
//
//        // start each amplifier (justDoIt will do a launch)
//        IntCodeComputerCR(program, ea, ab).justDoIt()
//        IntCodeComputerCR(program, ab, bc).justDoIt()
//        IntCodeComputerCR(program, bc, cd).justDoIt()
//        IntCodeComputerCR(program, cd, de).justDoIt()
//
//        // the value could also be fetched by:
//        // 1. adding an extra channel to the last computer and receive until channel is closed and emitt last value
//        // 2. Using the experimental BroadcastChannel which allows multiple subscriptions and do the same as 1.
//        val deferred = IntCodeComputerCR(program, de, ea).justDoIt()
//        return getValueFromDeferred(deferred)
        return 2
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

