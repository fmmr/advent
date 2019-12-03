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
        (0..100).forEach { noun ->
            (0..100).forEach { verb ->
                val result = partOneMod(list.toMutableList(), noun, verb)
                if (result == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        error("noun and verb not found")
    }
}
