package no.rodland.advent_2018


object Day19 {
    fun partOne(list: List<String>, initialReg0: Int = 0): Int {
        val instructionPointer = list[initialReg0].split(" ")[1].toInt()
        val program: Array<Instruction> = parse(list.subList(1, list.size))
        val register = execute(instructionPointer, program, Register(initialReg0, 0, 0, 0, 0, 0))
        println("Ran ${program.size} line program with end result: $register")
        return register[initialReg0]
    }

    private fun execute(pointer: Int, program: Array<Instruction>, r: Register): Register {
        var register = r
        var ip = 0
        while (ip >= 0 && ip < program.size) {
            register[pointer] = ip


            val instruction = program[ip]
            val opCode = OpCode[instruction.i]

//            print("ip=$ip $register $opCode ${instruction.a} ${instruction.b} ${instruction.c} ")
            register = opCode.run(instruction, register)
//            println(" $register ")

            ip = register[pointer] + 1
        }
        println("program exited, ip: $ip, register: $register")
        return register
    }

    private fun parse(subList: List<String>): Array<Instruction> {
        return subList.map { it.split(" ") }
                .map { Instruction(OpCode.valueOf(it[0]).id, it[1].toInt(), it[2].toInt(), it[3].toInt()) }
                .toTypedArray()
    }

    fun partTwo(): Int {
        // https://github.com/chriswk/aoc/blob/master/src/main/kotlin/com/chriswk/aoc2018/Day19.kt#L13
        return 10551267.fatcorize().sum()
    }
}

private fun Int.fatcorize(): List<Int> = (1..(this / 2)).filter { this % it == 0 } + this
