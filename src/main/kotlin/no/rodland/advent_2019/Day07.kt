package no.rodland.advent_2019

object Day07 {
    fun partOne(program: List<Int>, start: Int = 1): Int {
        return phaseCombos().map { runAmplifiers(program, it) }.max()!!
    }

    private fun phaseCombos(): List<List<Int>> {
        return (0..4).flatMap { p0 ->
            (0..4).flatMap { p1 ->
                (0..4).flatMap { p2 ->
                    (0..4).flatMap { p3 ->
                        (0..4).map { p4 ->
                            listOf(p0, p1, p2, p3, p4)
                        }
                    }
                }
            }
        }
                .distinct()
                .filterNot { it.toSet().size != it.size }
    }

    fun runAmplifiers(program: List<Int>, phases: List<Int>): Int {
        val resA = IntCodeComputer(program, mutableListOf(phases[0], 0)).runProgram()
        val resB = IntCodeComputer(program, mutableListOf(phases[1], resA)).runProgram()
        val resC = IntCodeComputer(program, mutableListOf(phases[2], resB)).runProgram()
        val resD = IntCodeComputer(program, mutableListOf(phases[3], resC)).runProgram()
        val resE = IntCodeComputer(program, mutableListOf(phases[4], resD)).runProgram()
        return resE
    }


    fun partTwo(program: List<Int>): Int {
        return 2
    }
}

