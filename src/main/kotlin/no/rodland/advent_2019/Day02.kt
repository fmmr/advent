package no.rodland.advent_2019

object Day02 {

    fun partOne(list: MutableList<Int>): List<Int> {
        return runProgram(list, 0)
    }

    fun partOneMod(list: MutableList<Int>, noun: Int = 12, verb: Int = 2): Int {
        list[1] = noun
        list[2] = verb
        return runProgram(list, 0)[0]
    }

    fun runProgram(list: MutableList<Int>, pos: Int): MutableList<Int> {
        val opcode = list[pos]
        if (opcode == 99) {
            return list
        }
        val adr1 = list[pos + 1]
        val adr2 = list[pos + 2]
        val adr3 = list[pos + 3]
        if (opcode == 1) {
            list[adr3] = list[adr1] + list[adr2]
        } else if (opcode == 2) {
            list[adr3] = list[adr1] * list[adr2]
        } else {
            error("Unable to handle opcode $opcode")
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
