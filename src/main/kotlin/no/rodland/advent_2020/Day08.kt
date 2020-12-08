package no.rodland.advent_2020

object Day08 {
    fun partOne(list: List<String>): Int {
        val instructions = list.map { it.split(" ") }.map { Instruction(Op.valueOf(it[0]), it[1].toInt()) }
        return run(instructions).first
    }

    private fun run(instructions: List<Instruction>): Pair<Int, Int> {
        val visited = mutableSetOf<Int>()
        return generateSequence((0 to 0)) { (accum, pointer) ->
            val instruction = instructions[pointer]
            val (newacc, newPointer) = instruction.op.exec(accum, pointer, instruction.arg)
            if (!visited.add(newPointer)) {
                accum to -1
            } else {
                newacc to newPointer
            }
        }.takeWhile { it.second >= 0 }.last()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    data class Instruction(val op: Op, val arg: Int)

    @Suppress("EnumEntryName")
    enum class Op {
        acc,
        jmp,
        nop;

        fun exec(accum: Int, pointer: Int, arg: Int): Pair<Int, Int> {
            return when (this) {
                acc -> accum + arg to pointer + 1
                jmp -> accum to pointer + arg
                nop -> accum to pointer + 1
            }
        }
    }
}
