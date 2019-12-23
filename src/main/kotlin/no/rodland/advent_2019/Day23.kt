package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day23 {
    fun partOne(program: List<String>): Long {
        val inputs = mutableListOf<Channel<Long>>()
        val outputs = mutableListOf<Channel<Long>>()
        val computers = mutableListOf<IntCodeComputer>()
        val jobs = mutableListOf<Job>()

        (0..49).forEach { networkAddress ->
            val input = Channel<Long>(20)
            val output = Channel<Long>(20)
            inputs.add(input)
            outputs.add(output)
            val computer = IntCodeComputer()
            computers.add(computer)
            jobs.add(GlobalScope.launch {
                computer.runSuspend(
                        program,
                        {
                            if (input.isEmpty) {
                                -1L
                            } else {
                                input.receive()
                            }
                        },
                        {
                            // first value is network address
                            // second value is X
                            // third value is Y
                            output.send(it)
                        }
                )

            })
        }
        return 2
    }

    fun partTwo(program: List<String>): Long {
        return 2
    }

    private fun runSpringScript(program: List<String>, cmds: List<String>): Long {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        var count = 0L
        var h = 0L
        val job = IntCodeComputer().launch(program, input, output)
        val out = GlobalScope.launch {
            while (true) {
                try {
                    h = output.receive()
                    val hei = h.toChar()
                    count++
                    print("$hei")
                } catch (e: Exception) {
                    println("GOT EXCEPTION ")
                    break
                }
            }
        }
        println()


        runBlocking {
            cmds.forEach { line ->
                line.forEach {
                    input.send(it.toLong())
                }
                input.send(10L)
            }
        }

        runBlocking {
            job.join()
            out.join()
        }

        return h
    }


}