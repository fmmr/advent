package no.rodland.advent_2019

object Day05 {
    fun partOne(input: List<Int>, start: Int = 1): Int {
        val list = input.toMutableList()
        val endState = runProgram(list, start)
        return endState
    }

    var lastValuePrinted = -1

    private fun runProgram(input: List<Int>, start: Int = 1, pos: Int = 0): Int {
        val list = input.toMutableList()
        val operator = Operation(list[pos])
        val newPos = when (operator.operation) {
            99 -> return lastValuePrinted
            1 -> {
                list[list[pos + 3]] = getValue(operator.mode(1), list[pos + 1], list) + getValue(operator.mode(2), list[pos + 2], list)
                pos + operator.steps
            }
            2 -> {
                list[list[pos + 3]] = getValue(operator.mode(1), list[pos + 1], list) * getValue(operator.mode(2), list[pos + 2], list)
                pos + operator.steps
            }
            6 -> if (getValue(operator.mode(1), list[pos + 1], list) == 0) {
                getValue(operator.mode(2), list[pos + 2], list)
            } else {
                pos + operator.steps
            }
            5 -> if (getValue(operator.mode(1), list[pos + 1], list) != 0) {
                getValue(operator.mode(2), list[pos + 2], list)
            } else {
                pos + operator.steps
            }
            8 -> {
                list[list[pos + 3]] = if (getValue(operator.mode(1), list[pos + 1], list) == getValue(operator.mode(2), list[pos + 2], list)) {
                    1
                } else {
                    0
                }
                pos + operator.steps
            }
            7 -> {
                list[list[pos + 3]] = if (getValue(operator.mode(1), list[pos + 1], list) < getValue(operator.mode(2), list[pos + 2], list)) {
                    1
                } else {
                    0
                }
                pos + operator.steps
            }
            3 -> {
                list[list[pos + 1]] = start
                pos + operator.steps
            }
            4 -> {
                lastValuePrinted = list[list[pos + 1]]
                println(lastValuePrinted)
                pos + operator.steps
            }
            else -> error("Unable to handle opcode $operator")
        }
        return runProgram(list, pos = newPos)
    }

    fun getValue(mode: Int, value: Int, list: List<Int>): Int {
        return if (mode == 1) {
            value
        } else {
            list[value]
        }
    }
}


class Operation(val code: Int) {
    val operation = code % 100

    val steps = when (operation) {
        1 -> 4
        2 -> 4
        3 -> 2
        4 -> 2
        5 -> 3
        6 -> 3
        7 -> 4
        8 -> 4
        99 -> 0
        else -> error("Unable to find steps for operation $operation")
    }

    fun mode(pos: Int): Int {
        return when (pos) {
            1 -> (code % 1000) / 100
            2 -> (code % 10000) / 1000
            3 -> (code % 100000) / 10000
            else -> error("Unable to find mode@ for operation $operation")
        }
    }

    override fun toString(): String {
        return "Operation(code=$code, operation=$operation, steps=$steps)"
    }


}
