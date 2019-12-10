package no.rodland.advent_2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
object Day07 {
    fun partOne(program: List<String>): Long {
        return permute(0..4).map { runBlocking { runAmplifiersPart1(program, it) } }.max()!!
    }

    fun partTwo(program: List<String>): Long {
        val map = permute(5..9).map { runBlocking { runAmplifiersPart2(program, it) } }
        return map.max()!!
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
        IntCodeComputer(program, ea, ab).run()
        IntCodeComputer(program, ab, bc).run()
        IntCodeComputer(program, bc, cd).run()
        IntCodeComputer(program, cd, de).run()
        IntCodeComputer(program, de, broadcast).run()

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
        val outCh = Channel<Long>(20)
        inCh.send(first)
        inCh.send(second)
        IntCodeComputer(program, inCh, outCh).run()
        return outCh.toList().last()
    }
}

