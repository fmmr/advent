package no.rodland.advent_2020

typealias AccumulatorPointer = Pair<Int, Int>

object Day08 {
    fun partOne(list: List<String>): Int {
        val instructions = list.map { it.split(" ") }.map { Instruction(Op.valueOf(it[0]), it[1].toInt()) }
        return run(instructions).first
    }

    private fun run(instructions: List<Instruction>): AccumulatorPointer {
        val visited = mutableSetOf<Int>()
        // -3 is to ensure we get the correct exit code
        return run(instructions, visited).takeWhile { it.second > -3 }.last()
    }

    // will generate a sequence with the which should be stopped when pointer is == -3
    // the next to last element will contain an exit code:
    // -1: detected endless loop
    // -2: finished
    private fun run(instructions: List<Instruction>, visited: MutableSet<Int>) = generateSequence((0 to 0)) { (accum, pointer) ->
        if (pointer >= 0) {
            val instruction = instructions[pointer]
            val (newacc, newPointer) = instruction.op.exec(accum, pointer, instruction.arg)
            if (newPointer == instructions.size) { // program finished
                newacc to -2
            } else if (!visited.add(newPointer)) { // program finished
                accum to -1
            } else {
                newacc to newPointer
            }
        } else {
            accum to -3
        }
    }

    fun partTwo(list: List<String>): Int {
        val flips = setOf(Op.jmp, Op.nop)  // only need to switch these
        val instructions = list.map { it.split(" ") }.map { Instruction(Op.valueOf(it[0]), it[1].toInt()) }.toMutableList()
        val flipIndices = instructions
            .mapIndexed { index: Int, instruction: Instruction -> index to instruction }
            .filter { it.second.op in flips }
            .filterNot { it.second.op == Op.nop && it.second.arg == 0 }  // no need to have a jmp 0 (endless for sure)
            .map { it.first }
        println("num instructions: ${instructions.size} - have ${flipIndices.size} indices to switch")
        flipIndices.forEach { indexToFlip ->
            instructions.flip(indexToFlip)
            val result = run(instructions)
            if (result.second == -2) {
                return result.first
            }
            instructions.flip(indexToFlip)
        }
        error("No result found")
    }

    private fun MutableList<Instruction>.flip(indexToFlip: Int) {
        val newInstruction = Instruction(this[indexToFlip].op.flip(), this[indexToFlip].arg)
        this[indexToFlip] = newInstruction
    }

    data class Instruction(val op: Op, val arg: Int)

    @Suppress("EnumEntryName")
    enum class Op {
        acc,
        jmp,
        nop;

        fun exec(accum: Int, pointer: Int, arg: Int): AccumulatorPointer = when (this) {
            acc -> accum + arg to pointer + 1
            jmp -> accum to pointer + arg
            nop -> accum to pointer + 1
        }

        fun flip(): Op = when (this) {
            jmp -> nop
            nop -> jmp
            else -> this
        }
    }
}
