package no.rodland.advent_2018

import get

object Day16 {
    private val re = """.*?(\d+),? (\d+),? (\d+),? (\d+).*""".toRegex()

    fun partOne(list: List<List<String>>, num: Int): Int {
        return list.map { sample ->
            val (instructions, registers) = parse(sample)
            numberOfMatchingOpcodes(instructions, registers.first, registers.second)
        }.count { it >= num }
    }

    fun numberOfMatchingOpcodes(command: Instruction, before: Register, after: Register): Int {
        return OpCode.values().map {
            val result = it.run(command, before)
            result == after
        }.count { it }
    }

    fun partTwo(list: List<String>): Register {
        val result = list.fold(Register(0, 0, 0, 0)) { input, instruction ->
            val i = Instruction(instruction)
            OpCode[i.i].run(i, input)
        }
        return result
    }

    // boo _ ugly code but kinda works. 
    fun findOpCodes(input: List<List<String>>): List<Pair<OpCode, Int>> {
        val list = input.toMutableList()
        val opcodesWithId: MutableList<Pair<OpCode, Int>> = mutableListOf()

        val count = list
                .map { sample ->
                    val (instructions, registers) = parse(sample)
                    if (opcodesWithId.none { it.second == instructions.i }) {
                        val opCodesWithMatch = OpCode.values()
                                .filter { opCode -> opcodesWithId.none { it.first == opCode } }
                                .map { opCode ->
                                    val result = opCode.run(instructions, registers.first)
                                    if (result == registers.second) {
                                        opCode
                                    } else {
                                        null
                                    }
                                }.filterNotNull()
                        if (opCodesWithMatch.size == 1) {
                            val hei = opCodesWithMatch[0] to instructions.i
                            opcodesWithId.add(hei)
                            hei
                        } else {
                            null
                        }
                    } else {
                        null
                    }
                }
                .filterNotNull()
                .distinct()
        println("opcodes: $count ${opcodesWithId.size} ${OpCode.values().size}")

        return count
    }

    fun parse(sample: List<String>): Pair<Instruction, Pair<Register, Register>> {
        return Instruction(sample[1]) to (Register(sample[0]) to Register(sample[2]))
    }

    data class Register(var r0: Int, var r1: Int, var r2: Int, var r3: Int) {
        constructor(str: String) : this(re.get(str), re.get(str, 2), re.get(str, 3), re.get(str, 4))

        operator fun get(idx: Int): Int {
            return when (idx) {
                0 -> r0
                1 -> r1
                2 -> r2
                3 -> r3
                else -> error("register $idx does not exist only 0-3 is allowed")
            }
        }

        operator fun set(idx: Int, value: Int): Register {
            when (idx) {
                0 -> r0 = value
                1 -> r1 = value
                2 -> r2 = value
                3 -> r3 = value
                else -> error("register $idx does not exist only 0-3 is allowed")
            }
            return this
        }

        fun copyWithValue(idx: Int, value: Int): Register {
            val copy = this.copy()
            copy[idx] = value
            return copy
        }
    }

    data class Instruction(val i: Int, val a: Int, val b: Int, val c: Int) {
        constructor(str: String) : this(re.get(str), re.get(str, 2), re.get(str, 3), re.get(str, 4))
    }


    @Suppress("EnumEntryName", "SpellCheckingInspection")
    enum class OpCode(val id: Int) {
        addr(8), addi(12), mulr(0), muli(5), banr(9), bani(7), borr(6), bori(15), setr(2), seti(14), gtir(11), gtri(13), gtrr(4), eqir(10), eqri(1), eqrr(3);

        fun run(instruction: Instruction, register: Register): Register {
            return when (this) {
                addr -> {
                    // (add register) stores into register C the result of adding register A and register B.
                    val value = register[instruction.a] + register[instruction.b]
                    register.copyWithValue(instruction.c, value)
                }
                addi -> {
                    // (add immediate) stores into register C the result of adding register A and value B.
                    val value = register[instruction.a] + instruction.b
                    register.copyWithValue(instruction.c, value)
                }
                mulr -> {
                    // (multiply register) stores into register C the result of multiplying register A and register B.
                    val value = register[instruction.a] * register[instruction.b]
                    register.copyWithValue(instruction.c, value)
                }
                muli -> {
                    // (multiply immediate) stores into register C the result of multiplying register A and value B.
                    val value = register[instruction.a] * instruction.b
                    register.copyWithValue(instruction.c, value)
                }
                banr -> {
                    // (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
                    val value = register[instruction.a] and register[instruction.b]
                    register.copyWithValue(instruction.c, value)
                }
                bani -> {
                    // (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
                    val value = register[instruction.a] and instruction.b
                    register.copyWithValue(instruction.c, value)
                }
                borr -> {
                    // (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
                    val value = register[instruction.a] or register[instruction.b]
                    register.copyWithValue(instruction.c, value)
                }
                bori -> {
                    // (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
                    val value = register[instruction.a] or instruction.b
                    register.copyWithValue(instruction.c, value)
                }
                setr -> {
                    // (set register) copies the contents of register A into register C. (Input B is ignored.)
                    val value = register[instruction.a]
                    register.copyWithValue(instruction.c, value)
                }
                seti -> {
                    // (set immediate) stores value A into register C. (Input B is ignored.)
                    val value = instruction.a
                    register.copyWithValue(instruction.c, value)
                }
                gtir -> {
                    // (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
                    val tmpValue = instruction.a > register[instruction.b]
                    val value = if (tmpValue) 1 else 0
                    register.copyWithValue(instruction.c, value)
                }
                gtri -> {
                    // (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
                    val tmpValue = register[instruction.a] > instruction.b
                    val value = if (tmpValue) 1 else 0
                    register.copyWithValue(instruction.c, value)
                }
                gtrr -> {
                    // (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
                    val tmpValue = register[instruction.a] > register[instruction.b]
                    val value = if (tmpValue) 1 else 0
                    register.copyWithValue(instruction.c, value)
                }
                eqir -> {
                    // (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
                    val tmpValue = instruction.a == register[instruction.b]
                    val value = if (tmpValue) 1 else 0
                    register.copyWithValue(instruction.c, value)
                }
                eqri -> {
                    // (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
                    val tmpValue = register[instruction.a] == instruction.b
                    val value = if (tmpValue) 1 else 0
                    register.copyWithValue(instruction.c, value)
                }
                eqrr -> {
                    // (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
                    val tmpValue = register[instruction.a] == register[instruction.b]
                    val value = if (tmpValue) 1 else 0
                    register.copyWithValue(instruction.c, value)
                }
            }
        }

        companion object {
            operator fun get(i: Int): OpCode {
                return OpCode.values().first { it.id == i }
            }

        }
    }
}