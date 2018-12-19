package no.rodland.advent_2018

@Suppress("EnumEntryName", "SpellCheckingInspection")
enum class OpCode(val id: Int) {
    addr(8), addi(12), mulr(0), muli(5), banr(9), bani(7), borr(6), bori(15), setr(2), seti(14), gtir(11), gtri(13), gtrr(4), eqir(10), eqri(1), eqrr(3);

    fun run(instruction: Day16.Instruction, register: Day16.Register): Day16.Register {
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