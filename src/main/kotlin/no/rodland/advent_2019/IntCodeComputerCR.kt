package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO


class IntCodeComputerCR(program: List<String>, val input: ReceiveChannel<BigInteger>, val output: SendChannel<BigInteger>) {
    val prog = program.mapIndexed { idx, str -> idx to BigInteger(str) }.toMap().toMutableMap()

    var lastOut = BigInteger("-999")

    fun justDoIt(): Deferred<BigInteger> {
        return GlobalScope.async {
            try {
                runProgram()
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    suspend fun runProgram(pos: Int = 0, relBase: Int = 0): BigInteger {
        val operator = Operation(prog[pos].toString().toInt())
        if (operator.operation == 99) {
            output.close()
            return lastOut
        }

        var newRelBase = relBase
        val val2 = getValue(operator.mode(2), prog.getOrDefault(pos + 2, ZERO), prog, relBase)
        val val1 = getValue(operator.mode(1), prog.getOrDefault(pos + 1, ZERO), prog, relBase)
        val setVal1 = prog.getOrDefault(pos + 1, ZERO)
        val setVal3 = prog.getOrDefault(pos + 3, ZERO)

        val newPos = when (operator.operation) {
            1 -> {
                prog.safeSet(setVal3.intValueExact(), val1 + val2, operator.mode(3), relBase)
                pos + operator.steps
            }
            9 -> {
                newRelBase += val1.intValueExact()
                pos + operator.steps
            }
            2 -> {
                prog.safeSet(setVal3.intValueExact(), val1 * val2, operator.mode(3), relBase)
                pos + operator.steps
            }
            3 -> {
                prog.safeSet(setVal1.intValueExact(), input.receive(), operator.mode(1), relBase)
                pos + operator.steps
            }
            4 -> {
                lastOut = val1
                output.send(lastOut)
                pos + operator.steps
            }
            5 -> if (val1 != ZERO) {
                val2.intValueExact()
            } else {
                pos + operator.steps
            }
            6 -> if (val1 == ZERO) {
                val2.intValueExact()
            } else {
                pos + operator.steps
            }
            7 -> {
                prog.safeSet(setVal3.intValueExact(), if (val1 < val2) {
                    ONE
                } else {
                    ZERO
                }, operator.mode(3), relBase)
                pos + operator.steps
            }
            8 -> {
                prog.safeSet(setVal3.intValueExact(), if (val1 == val2) {
                    ONE
                } else {
                    ZERO
                }, operator.mode(3), relBase)
                pos + operator.steps
            }
            else -> error("Unable to handle opcode $operator")
        }
        return runProgram(pos = newPos, relBase = newRelBase)
    }

    fun getValue(mode: Int, value: BigInteger, prog: Map<Int, BigInteger>, relBase: Int): BigInteger {
        return when (mode) {
            1 -> {
                value
            }
            2 -> {
                prog.getOrDefault((value.intValueExact() + relBase).toString().toInt(), ZERO)
            }
            else -> {
                prog.getOrDefault(value.toString().toInt(), ZERO)
            }
        }
    }

    private fun MutableMap<Int, BigInteger>.safeSet(idx: Int, value: BigInteger, mode: Int, relBase: Int) {
        when (mode) {
            2 -> this[(idx + relBase).toString().toInt()] = value
            0 -> this[idx] = value
            else -> error("unsupported mode $mode for set-operations")
        }
    }
}
