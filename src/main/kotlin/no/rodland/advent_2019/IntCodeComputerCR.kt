package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel


class IntCodeComputerCR(program: List<Int>, val input: ReceiveChannel<Int>, val output: SendChannel<Int>) {
    val programList = program.toMutableList().also { list -> (1..10000).forEach { list.add(0) } }

    var lastOut = NO_OUTPUT_VALUE

    fun justDoIt(): Deferred<Int> {
        return GlobalScope.async {
            try {
                runProgram()
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    suspend fun runProgram(pos: Int = 0, relBase: Int = 0): Int {
        val operator = Operation(programList[pos])

        var newRelBase = relBase
        val newPos = when (operator.operation) {
            99 -> {
                output.close()
                return lastOut
            }
            1 -> {
                programList.safeSet(programList.getOrZero(pos + 3), getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase) + getValue(operator.mode(2), programList.getOrZero(pos + 2), programList, relBase))
                pos + operator.steps
            }
            9 -> {
                newRelBase += getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase)
                pos + operator.steps
            }
            2 -> {
                programList.safeSet(programList.getOrZero(pos + 3), getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase) * getValue(operator.mode(2), programList.getOrZero(pos + 2), programList, relBase))
                pos + operator.steps
            }
            3 -> {
                val v = input.receive()
//                println("${Thread.currentThread().name} GOT: $v")

                programList.safeSet(programList.getOrZero(pos + 1), v)
                pos + operator.steps
            }
            4 -> {
                lastOut = getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase)
//                println("${Thread.currentThread().name} SEND: $lastOut")
                output.send(lastOut)
                pos + operator.steps
            }
            5 -> if (getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase) != 0) {
                getValue(operator.mode(2), programList.getOrZero(pos + 2), programList, relBase)
            } else {
                pos + operator.steps
            }
            6 -> if (getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase) == 0) {
                getValue(operator.mode(2), programList.getOrZero(pos + 2), programList, relBase)
            } else {
                pos + operator.steps
            }
            7 -> {


                programList.safeSet(programList.getOrZero(pos + 3),
                        if (getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase) < getValue(operator.mode(2), programList.getOrZero(pos + 2), programList, relBase)) {
                            1
                        } else {
                            0
                        })
                pos + operator.steps
            }
            8 -> {
                programList.safeSet(programList.getOrZero(pos + 3),
                        if (getValue(operator.mode(1), programList.getOrZero(pos + 1), programList, relBase) == getValue(operator.mode(2), programList.getOrZero(pos + 2), programList, relBase)) {
                            1
                        } else {
                            0
                        })
                pos + operator.steps
            }
            else -> error("Unable to handle opcode $operator")
        }
        return runProgram(pos = newPos, relBase = newRelBase)
    }

    fun getValue(mode: Int, value: Int, list: List<Int>, relBase: Int): Int {
        return when (mode) {
            1 -> {
                value
            }
            2 -> {
                list.getOrZero(value + relBase)
            }
            else -> {
                list.getOrZero(value)
            }
        }
    }

    private fun List<Int>.getOrZero(idx: Int) = if (idx < size) {
        this[idx]
    } else {
        0
    }

    private fun MutableList<Int>.safeSet(idx: Int, value: Int) = if (idx < size) {
        this[idx] = value
    } else {
        error("unable to set $idx for size $size")
    }
}
