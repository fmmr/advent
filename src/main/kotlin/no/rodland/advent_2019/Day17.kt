package no.rodland.advent_2019

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos

@DelicateCoroutinesApi
object Day17 {
    fun partOne(list: List<String>): Int {
        val map = runBlocking { runAmp(list) }
        val calibration = map.filter { cell -> cell.value != '.' && cell.key.neighboorCellsReadingOrder().all { nei -> map[nei] != '.' } }
                .map { it.key.x * it.key.y }
                .sum()
        return calibration
    }

    suspend fun runAmp(program: List<String>): Map<Pos, Char> {
        val list = mutableListOf<Long>()
        IntCodeComputer().runSuspend(program, { 0 }, {
            //            print(it.toChar())
            list.add(it)
        })
        return list.map { it.toInt().toChar() }
                .joinToString("").split(10.toChar())
                .mapIndexed { y, line -> line.mapIndexed { x, char -> Pos(x, y) to char } }
                .flatten()
                .toMap()
    }

    fun partTwo(list: List<String>): Long {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val program = list.toMutableList()
        program[0] = "2"

        val job = IntCodeComputer().launch(program, input, output)
        var sum = 0L
        val out = GlobalScope.launch {
            while (true) {
                try {
                    sum = output.receive()
                } catch (e: Exception) {
                    println("GOT E#XCEPTION ")
                    break
                }
            }
        }

        // see map etc in Day17Test.kt
        //   A,B,A,C,A,B,C,A,B,C
        // A R,12,R,4,R,10,R,12
        // B R,6,L,8,R,10
        // C L,8,R,4,R,4,R,6

        runBlocking {
            "A,B,A,C,A,B,C,A,B,C".forEach { input.send(it.code.toLong()) }
            input.send(10L)
            "R,12,R,4,R,10,R,12".forEach { input.send(it.code.toLong()) }
            input.send(10L)
            "R,6,L,8,R,10".forEach { input.send(it.code.toLong()) }
            input.send(10L)
            "L,8,R,4,R,4,R,6".forEach { input.send(it.code.toLong()) }
            input.send(10L)
            input.send('n'.code.toLong())
            input.send(10L)
        }

        runBlocking {
            job.join()
            out.join()
        }

        return sum
    }
}