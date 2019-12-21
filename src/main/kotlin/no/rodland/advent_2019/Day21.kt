package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object Day21 {
    fun partOne(program: List<String>): Long {
        // X A B C D
        //   . . . .  LOST
        //   . . . #  true
        //   . . # .  LOST
        //   . . # #  true
        //   . # . .  LOST
        //   . # . #  true
        //   . # # .  LOST
        //   . # # #  true

        //   # . . .  false
        //   # . # .  false
        //   # # . .  false
        //   # # # .  false

        //   # # . #  true
        //   # . # #  true
        //   # . . #  true

        //   # # # #  false

        // => (!A || !B || !C) && D

        val cmds = listOf(
                "NOT A T",
                "NOT B J",
                "OR T J",
                "NOT C T",
                "OR T J",
                "AND D J",
                "WALK"
        )
        return runSpringScript(program, cmds)
    }

    fun partTwo(program: List<String>): Long {
        // extends part 1

        // jump if part 1, but only if we can wither walk or jump when we land
        // Jump: H
        // Walk: E

        // => (!A || !B || !C) && D && (H || E)
        val cmds = listOf(
                "NOT A T",
                "NOT B J",
                "OR T J",
                "NOT C T",
                "OR T J",
                "AND D J",
                "NOT H T", // trix - double NOT => sets variable (H) to T
                "NOT T T",
                "OR E T",
                "AND T J",
                "RUN"
        )
        return runSpringScript(program, cmds)
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