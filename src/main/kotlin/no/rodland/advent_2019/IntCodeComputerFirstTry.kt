package no.rodland.advent_2019

const val NO_OUTPUT_VALUE = -999

class IntCodeComputerFirstTry(program: List<Int>, inputs: List<Int>) {
    val inputMut = inputs.toMutableList()
    val programList = program.toMutableList()
    val outputs = mutableListOf<Int>()
    fun runProgram(): Int {
        return runProgram(0)
    }

    private fun runProgram(pos: Int = 0): Int {
        val operator = Operation(programList[pos])
        val newPos = when (operator.operation) {
            99 -> return outputs.lastOrNull() ?: NO_OUTPUT_VALUE
            1 -> {
                programList[programList[pos + 3]] = getValue(operator.mode(1), programList[pos + 1], programList) + getValue(operator.mode(2), programList[pos + 2], programList)
                pos + operator.steps
            }
            2 -> {
                programList[programList[pos + 3]] = getValue(operator.mode(1), programList[pos + 1], programList) * getValue(operator.mode(2), programList[pos + 2], programList)
                pos + operator.steps
            }
            3 -> {
                programList[programList[pos + 1]] = inputMut.removeAt(0)
                pos + operator.steps
            }
            4 -> {
                val printValue = getValue(operator.mode(1), programList[pos + 1], programList)
                outputs.add(printValue)
                println(printValue)
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
