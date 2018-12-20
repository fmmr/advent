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
        var lastR0 = 0
        var count = 0
        while (ip >= 0 && ip < program.size) {
            register[pointer] = ip
            val instruction = program[ip]
            val opCode = OpCode[instruction.i]
            register = opCode.run(instruction, register)
            count++
            if (register[0] != lastR0) {
                val newFactor = register[0] - lastR0
                println("factor=$newFactor count=$count ip=$ip $register $opCode ${instruction.a} ${instruction.b} ${instruction.c} ")
                lastR0 = register[0]
            }
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
        // with a little from my friends:
        // https://github.com/chriswk/aoc/blob/master/src/main/kotlin/com/chriswk/aoc2018/Day19.kt#L13

        // debugging part 1 shows we're trying to find the sum of factors of 
        // reg 1 (B):

        // factor=1 count=6945 ip=7 Register(ar=[1, 867, 867, 1, 1, 7]) addr 3 0 0
        // factor=3 count=16201 ip=7 Register(ar=[4, 867, 289, 3, 1, 7]) addr 3 0 0
        // factor=17 count=111457 ip=7 Register(ar=[21, 867, 51, 17, 1, 7]) addr 3 0 0
        // factor=51 count=347145 ip=7 Register(ar=[72, 867, 17, 51, 1, 7]) addr 3 0 0
        // factor=289 count=1998753 ip=7 Register(ar=[361, 867, 3, 289, 1, 7]) addr 3 0 0
        // factor=867 count=6010057 ip=7 Register(ar=[1228, 867, 1, 867, 1, 7]) addr 3 0 0

        // after 
        return 10551267.fatcorize().sum()
    }
}

private fun Int.fatcorize(): List<Int> = (1..(this / 2)).filter { this % it == 0 } + this
