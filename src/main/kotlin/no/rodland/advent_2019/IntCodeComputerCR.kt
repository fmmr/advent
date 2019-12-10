package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch


class IntCodeComputerCR(program: List<String>, val input: ReceiveChannel<Long>, private val output: SendChannel<Long>) {
    private val prog = program.map { it.toLong() }.toMutableList().also { it.addAll(Array(100) { 0L }) }

    fun run() {
        GlobalScope.launch {
            try {
                runSuspend()
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    private suspend fun runSuspend() {
        var pos = 0
        var relBase = 0
        while (true) {
            val operator = Operation(prog[pos].toString().toInt())
            if (operator.operation == 99) {
                output.close()
                break
            }
            val val2 = get(operator.mode(2), prog.getOrElse(pos + 2) { 0L }, prog, relBase)
            val val1 = get(operator.mode(1), prog.getOrElse(pos + 1) { 0L }, prog, relBase)
            val setVal1 = prog.getOrElse(pos + 1) { 0L }
            val setVal3 = prog.getOrElse(pos + 3) { 0L }

            pos = when (operator.operation) {
                1 -> {
                    prog.set(setVal3.toInt(), val1 + val2, operator.mode(3), relBase)
                    pos + operator.steps
                }
                9 -> {
                    relBase += val1.toInt()
                    pos + operator.steps
                }
                2 -> {
                    prog.set(setVal3.toInt(), val1 * val2, operator.mode(3), relBase)
                    pos + operator.steps
                }
                3 -> {
                    prog.set(setVal1.toInt(), input.receive(), operator.mode(1), relBase)
                    pos + operator.steps
                }
                4 -> {
                    output.send(val1)
                    pos + operator.steps
                }
                5 -> if (val1 != 0L) {
                    val2.toInt()
                } else {
                    pos + operator.steps
                }
                6 -> if (val1 == 0L) {
                    val2.toInt()
                } else {
                    pos + operator.steps
                }
                7 -> {
                    prog.set(setVal3.toInt(), if (val1 < val2) {
                        1L
                    } else {
                        0L
                    }, operator.mode(3), relBase)
                    pos + operator.steps
                }
                8 -> {
                    prog.set(setVal3.toInt(), if (val1 == val2) {
                        1L
                    } else {
                        0L
                    }, operator.mode(3), relBase)
                    pos + operator.steps
                }
                else -> error("Unable to handle opcode $operator")
            }
        }
    }

    fun get(mode: Int, value: Long, prog: List<Long>, relBase: Int): Long {
        return when (mode) {
            1 -> value
            2 -> prog.getOrElse((value + relBase).toInt()) { 0L }
            else -> prog.getOrElse(value.toInt()) { 0L }
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
