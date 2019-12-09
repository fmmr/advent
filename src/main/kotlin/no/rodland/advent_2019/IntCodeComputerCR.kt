package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel


class IntCodeComputerCR(program: List<Int>, val input: ReceiveChannel<Int>, val output: SendChannel<Int>) {
    val prog = program.toMutableList().also { list -> (1..1000).forEach { list.add(0) } }

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
        val operator = Operation(prog[pos])

        var newRelBase = relBase
        val val2 = getValue(operator.mode(2), prog.getOrZero(pos + 2), prog, relBase)
        val val1 = getValue(operator.mode(1), prog.getOrZero(pos + 1), prog, relBase)
        val setVal1 = prog.getOrZero(pos + 1)
        val setVal3 = prog.getOrZero(pos + 3)

        val newPos = when (operator.operation) {
            99 -> {
                output.close()
                return lastOut
            }
            1 -> {
                prog.safeSet(setVal3, val1 + val2)
                pos + operator.steps
            }
            9 -> {
                newRelBase += val1
                pos + operator.steps
            }
            2 -> {
                prog.safeSet(setVal3, val1 * val2)
                pos + operator.steps
            }
            3 -> {
                prog.safeSet(setVal1, input.receive())
                pos + operator.steps
            }
            4 -> {
                lastOut = val1
                output.send(lastOut)
                pos + operator.steps
            }
            5 -> if (val1 != 0) {
                val2
            } else {
                pos + operator.steps
            }
            6 -> if (val1 == 0) {
                val2
            } else {
                pos + operator.steps
            }
            7 -> {
                prog.safeSet(setVal3, if (val1 < val2) {
                    1
                } else {
                    0
                })
                pos + operator.steps
            }
            8 -> {
                prog.safeSet(setVal3, if (val1 == val2) {
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

    fun getValue(mode: Int, value: Int, prog: List<Int>, relBase: Int): Int {
        return when (mode) {
            1 -> {
                value
            }
            2 -> {
                prog.getOrZero(value + relBase)
            }
            else -> {
                prog.getOrZero(value)
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
