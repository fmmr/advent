package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch


class IntCodeComputer(program: List<String>, val input: ReceiveChannel<Long>, private val output: SendChannel<Long>) {
    private val prog = program.map { it.toLong() }.toMutableList()

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
            val operator = Operation(prog[pos].toInt())
            if (operator.operation == 99) {
                break
            }
            val ret = next(operator, pos, relBase)
            pos = ret.first
            relBase = ret.second
        }
        output.close()
    }

    private suspend fun next(operator: Operation, pos: Int, relBase: Int): Pair<Int, Int> {
        var relBase1 = relBase
        val val1 = get(operator.mode(1), prog.getOrElse(pos + 1) { 0 }, prog, relBase1)
        val val2 = get(operator.mode(2), prog.getOrElse(pos + 2) { 0 }, prog, relBase1)
        val idx1 = prog.getOrElse(pos + 1) { 0 }.toInt()
        val idx3 = (prog.getOrElse(pos + 3) { 0 }).toInt()

        val newPos = when (operator.operation) {
            1 -> {
                prog.set(idx3, val1 + val2, operator.mode(3), relBase1)
                pos + operator.steps
            }
            2 -> {
                prog.set(idx3, val1 * val2, operator.mode(3), relBase1)
                pos + operator.steps
            }
            3 -> {
                prog.set(idx1, input.receive(), operator.mode(1), relBase1)
                pos + operator.steps
            }
            4 -> {
                output.send(val1)
                pos + operator.steps
            }
            5 -> if (val1 != 0L) val2.toInt() else pos + operator.steps
            6 -> if (val1 == 0L) val2.toInt() else pos + operator.steps
            7 -> {
                prog.set(idx3, if (val1 < val2) 1L else 0L, operator.mode(3), relBase1)
                pos + operator.steps
            }
            8 -> {
                prog.set(idx3, if (val1 == val2) 1L else 0L, operator.mode(3), relBase1)
                pos + operator.steps
            }
            9 -> {
                relBase1 += val1.toInt()
                pos + operator.steps
            }
            else -> error("Unable to handle opcode $operator")
        }
        return newPos to relBase1
    }

    fun get(mode: Int, value: Long, prog: List<Long>, relBase: Int): Long {
        return when (mode) {
            1 -> value
            2 -> prog.getOrElse((value + relBase).toInt()) { 0L }
            0 -> prog.getOrElse(value.toInt()) { 0L }
            else -> error("unsupported mode $mode for get-operations")
        }
    }

    private fun MutableList<Long>.set(idx: Int, value: Long, mode: Int, relBase: Int) {
        val index = when (mode) {
            0 -> idx
            2 -> idx + relBase
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
