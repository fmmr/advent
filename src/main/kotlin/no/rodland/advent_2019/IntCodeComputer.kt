package no.rodland.advent_2019

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch


class IntCodeComputer {

    @DelicateCoroutinesApi
    fun launch(program: List<String>, receiveChannel: ReceiveChannel<Long>, sendChannel: SendChannel<Long>): Job {
        return GlobalScope.launch {
            try {
                runSuspend(program, receiveChannel, sendChannel)
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }

    suspend fun runSuspend(program: List<String>, input: ReceiveChannel<Long>, output: SendChannel<Long>) {
        runSuspend(program, { input.receive() }, { output.send(it) })
        output.close()
    }

    suspend fun runSuspend(programInput: List<String>, input: suspend () -> Long, output: suspend (Long) -> Unit) {
        val program = programInput.map { it.toLong() }.toMutableList()
        var instructionPointer = 0
        var relativePointer = 0
        while (true) {
            val operator = Operation(program[instructionPointer].toInt())
            if (operator.operation == 99) {
                break
            }
            var relBase1 = relativePointer
            val val1 = get(operator.mode(1), program.getOrElse(instructionPointer + 1) { 0 }, program, relBase1)
            val val2 = get(operator.mode(2), program.getOrElse(instructionPointer + 2) { 0 }, program, relBase1)
            val idx1 = program.getOrElse(instructionPointer + 1) { 0 }.toInt()
            val idx3 = (program.getOrElse(instructionPointer + 3) { 0 }).toInt()
            val newPos = when (operator.operation) {
                1 -> {
                    program.set(idx3, val1 + val2, operator.mode(3), relBase1)
                    instructionPointer + operator.steps
                }
                2 -> {
                    program.set(idx3, val1 * val2, operator.mode(3), relBase1)
                    instructionPointer + operator.steps
                }
                3 -> {
                    program.set(idx1, input(), operator.mode(1), relBase1)
                    instructionPointer + operator.steps
                }
                4 -> {
                    output(val1)
                    instructionPointer + operator.steps
                }
                5 -> if (val1 != 0L) val2.toInt() else instructionPointer + operator.steps
                6 -> if (val1 == 0L) val2.toInt() else instructionPointer + operator.steps
                7 -> {
                    program.set(idx3, if (val1 < val2) 1L else 0L, operator.mode(3), relBase1)
                    instructionPointer + operator.steps
                }
                8 -> {
                    program.set(idx3, if (val1 == val2) 1L else 0L, operator.mode(3), relBase1)
                    instructionPointer + operator.steps
                }
                9 -> {
                    relBase1 += val1.toInt()
                    instructionPointer + operator.steps
                }
                else -> error("Unable to handle opcode $operator")
            }
            val ret = newPos to relBase1
            instructionPointer = ret.first
            relativePointer = ret.second
        }
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
