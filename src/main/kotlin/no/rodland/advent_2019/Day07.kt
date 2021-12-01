package no.rodland.advent_2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
object Day07 {
    fun partOne(program: List<String>): Long {
        return permute(0..4).map { runBlocking { runAmplifiersPart1(program, it) } }.maxOrNull()!!
    }

    fun partTwo(program: List<String>): Long {
        val map = permute(5..9).map { runBlocking { runAmplifiersPart2(program, it) } }
        return map.maxOrNull()!!
    }

    @ExperimentalCoroutinesApi
    @Suppress("DeferredResultUnused")
    private suspend fun runAmplifiersPart2(program: List<String>, phases: List<Long>): Long {

        val broadcast = BroadcastChannel<Long>(20)

        val sniffer = broadcast.openSubscription()
        val ea = broadcast.openSubscription()
        val ab = Channel<Long>(20)
        val bc = Channel<Long>(20)
        val cd = Channel<Long>(20)
        val de = Channel<Long>(20)

        // set ut channels initially
        broadcast.send(phases[0])
        broadcast.send(0)

        ab.send(phases[1])
        bc.send(phases[2])
        cd.send(phases[3])
        de.send(phases[4])


        // start each amplifier (justDoIt will do a launch)
        val intCodeComputer4 = IntCodeComputer()
        intCodeComputer4.launch(program, bc, cd)
        val intCodeComputer3 = IntCodeComputer()
        intCodeComputer3.launch(program, ea, ab)
        val intCodeComputer2 = IntCodeComputer()
        intCodeComputer2.launch(program, ab, bc)
        val intCodeComputer1 = IntCodeComputer()
        intCodeComputer1.launch(program, cd, de)
        val intCodeComputer = IntCodeComputer()
        intCodeComputer.launch(program, de, broadcast)

        return sniffer.toList().last()
    }

    @ExperimentalCoroutinesApi
    @Suppress("DeferredResultUnused")
    private suspend fun runAmplifiersPart1(program: List<String>, phases: List<Long>): Long {
        var tmp = runAmp(program, phases[0], 0)
        tmp = runAmp(program, phases[1], tmp)
        tmp = runAmp(program, phases[2], tmp)
        tmp = runAmp(program, phases[3], tmp)
        return runAmp(program, phases[4], tmp)
    }


    suspend fun runAmp(program: List<String>, first: Long, second: Long): Long {
        val inCh = Channel<Long>(20)
        inCh.send(first)
        inCh.send(second)
        val list = mutableListOf<Long>()
        IntCodeComputer().runSuspend(program, { inCh.receive() }, { list.add(it) })
        return list.last()
    }
}

