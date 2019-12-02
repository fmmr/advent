package no.rodland.advent_2019

object Day02 {

    fun partOne(input: MutableList<Int>): List<Int> {
        val list = input.toMutableList()
        return runProgram(list, 0)
    }

    fun partOneMod(input: List<Int>, noun: Int = 12, verb: Int = 2): Int {
        val list = input.toMutableList()
        list[1] = noun
        list[2] = verb
        return runProgram(list, 0)[0]
    }

    private fun runProgram(input: List<Int>, pos: Int): MutableList<Int> {
        val list = input.toMutableList()
        when (list[pos]) {
            99 -> return list
            1 -> list[list[pos + 3]] = list[list[pos + 1]] + list[list[pos + 2]]
            2 -> list[list[pos + 3]] = list[list[pos + 1]] * list[list[pos + 2]]
            else -> error("Unable to handle opcode ${list[pos]}")
        }
        return runProgram(list, pos + 4)
    }

    fun partTwo(list: List<Int>): Int {
        for (i in 0..50) {
            for (j in 0..50) {
                val result = partOneMod(list.toMutableList(), i, j)
                if (result == 19690720) {
                    println(100 * i + j)
                    return 100 * i + j
                }
            }
        }
        return -1
    }
}
