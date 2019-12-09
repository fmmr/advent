package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel


class IntCodeComputerCR(program: List<Int>, val input: ReceiveChannel<Int>, val output: SendChannel<Int>) {
    val programList = program.toMutableList()

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

    suspend fun runProgram(pos: Int = 0): Int {
        val operator = Operation(programList[pos])
        val newPos = when (operator.operation) {
            99 -> {
                output.close()
                return lastOut
            }
            1 -> {
                programList[programList[pos + 3]] = getValue(operator.mode(1), programList[pos + 1], programList) + getValue(operator.mode(2), programList[pos + 2], programList)
                pos + operator.steps
            }
            2 -> {
                programList[programList[pos + 3]] = getValue(operator.mode(1), programList[pos + 1], programList) * getValue(operator.mode(2), programList[pos + 2], programList)
                pos + operator.steps
            }
            3 -> {
                val v = input.receive()
//                println("${Thread.currentThread().name} GOT: $v")

                programList[programList[pos + 1]] = v
                pos + operator.steps
            }
            4 -> {
                lastOut = getValue(operator.mode(1), programList[pos + 1], programList)
//                println("${Thread.currentThread().name} SEND: $lastOut")
                output.send(lastOut)
                pos + operator.steps
            }
            5 -> if (getValue(operator.mode(1), programList[pos + 1], programList) != 0) {
                getValue(operator.mode(2), programList[pos + 2], programList)
            } else {
                pos + operator.steps
            }
            6 -> if (getValue(operator.mode(1), programList[pos + 1], programList) == 0) {
                getValue(operator.mode(2), programList[pos + 2], programList)
            } else {
                pos + operator.steps
            }
            7 -> {
                programList[programList[pos + 3]] = if (getValue(operator.mode(1), programList[pos + 1], programList) < getValue(operator.mode(2), programList[pos + 2], programList)) {
                    1
                } else {
                    0
                }
                pos + operator.steps
            }
            8 -> {
                programList[programList[pos + 3]] = if (getValue(operator.mode(1), programList[pos + 1], programList) == getValue(operator.mode(2), programList[pos + 2], programList)) {
                    1
                } else {
                    0
                }
                pos + operator.steps
            }
            else -> error("Unable to handle opcode $operator")
        }
        return runProgram(pos = newPos)
    }

    fun getValue(mode: Int, value: Int, list: List<Int>): Int {
        return if (mode == 1) {
            value
        } else {
            list[value]
        }
    }
}
