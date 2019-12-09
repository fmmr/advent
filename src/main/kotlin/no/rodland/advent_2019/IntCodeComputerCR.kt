package no.rodland.advent_2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel


class IntCodeComputerCR(program: List<String>, val input: ReceiveChannel<Long>, private val output: SendChannel<Long>) {
    private val prog = program.map { it.toLong() }.toMutableList().also { it.addAll(Array(100) { 0L }) }

    private var lastOut = -999L

    fun justDoIt(): Deferred<Long> {
        return GlobalScope.async {
            try {
                runProgram()
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    private suspend fun runProgram(pos: Int = 0, relBase: Int = 0): Long {
        val operator = Operation(prog[pos].toString().toInt())
        if (operator.operation == 99) {
            output.close()
            return lastOut
        }

        var newRelBase = relBase
        val val2 = get(operator.mode(2), prog.getOrElse(pos + 2) { 0L }, prog, relBase)
        val val1 = get(operator.mode(1), prog.getOrElse(pos + 1) { 0L }, prog, relBase)
        val setVal1 = prog.getOrElse(pos + 1) { 0L }
        val setVal3 = prog.getOrElse(pos + 3) { 0L }

        val newPos = when (operator.operation) {
            1 -> {
                prog.set(setVal3.toIntExact(), val1 + val2, operator.mode(3), relBase)
                pos + operator.steps
            }
            9 -> {
                newRelBase += val1.toIntExact()
                pos + operator.steps
            }
            2 -> {
                prog.set(setVal3.toIntExact(), val1 * val2, operator.mode(3), relBase)
                pos + operator.steps
            }
            3 -> {
                prog.set(setVal1.toIntExact(), input.receive(), operator.mode(1), relBase)
                pos + operator.steps
            }
            4 -> {
                lastOut = val1
                output.send(lastOut)
                pos + operator.steps
            }
            5 -> if (val1 != 0L) {
                val2.toIntExact()
            } else {
                pos + operator.steps
            }
            6 -> if (val1 == 0L) {
                val2.toIntExact()
            } else {
                pos + operator.steps
            }
            7 -> {
                prog.set(setVal3.toIntExact(), if (val1 < val2) {
                    1L
                } else {
                    0L
                }, operator.mode(3), relBase)
                pos + operator.steps
            }
            8 -> {
                prog.set(setVal3.toIntExact(), if (val1 == val2) {
                    1L
                } else {
                    0L
                }, operator.mode(3), relBase)
                pos + operator.steps
            }
            else -> error("Unable to handle opcode $operator")
        }
        return runProgram(pos = newPos, relBase = newRelBase)
    }

    fun get(mode: Int, value: Long, prog: List<Long>, relBase: Int): Long {
        return when (mode) {
            1 -> value
            2 -> prog.getOrElse((value + relBase).toIntExact()) { 0L }
            else -> prog.getOrElse(value.toIntExact()) { 0L }
        }
    }

    private fun MutableList<Long>.set(idx: Int, value: Long, mode: Int, relBase: Int) {
        val index = when (mode) {
            2 -> idx + relBase
            0 -> idx
            else -> error("unsupported mode $mode for set-operations")
        }
        if (index < size) {
            set(index, value)
        } else {
            if (index > size) addAll(Array(index - size) { 0L })
            add(value)
        }
    }
}

private fun Long.toIntExact(): Int = Math.toIntExact(this)

