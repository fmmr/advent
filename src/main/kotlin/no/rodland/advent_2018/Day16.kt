package no.rodland.advent_2018

object Day16 {
    fun partOne(command: IntArray, before: IntArray, after: IntArray): Int {
//        val matches = Register.values().map {
//            val result = it.run(command, before)
//            matches(result.first, after, result.second)
//        }.count { it }
        return 2
    }

    private fun matches(result: IntArray, after: IntArray, ignoreB: Boolean): Boolean {
        return result[0] == after[0] && result[1] == after[1] && result[3] == after[3] && (ignoreB || result[2] == after[2])
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    @Suppress("EnumEntryName", "SpellCheckingInspection")
    enum class Register(private val ignoreB: Boolean = false) {
        addr, addi, mulr, muli, banr, bani, borr, bori, setr(true), seti(true), gtir, gtri, gtrr, eqir, eqri, eqrr;

        fun run(instruction: IntArray, register: IntArray): Pair<IntArray, Boolean> {
            println("Running $this on $instruction")
            return (when (this) {
                addr -> {
                    register
                }
                addi -> {
                    register
                }
                mulr -> {
                    register
                }
                muli -> {
                    register
                }
                banr -> {
                    register
                }
                bani -> {
                    register
                }
                borr -> {
                    register
                }
                bori -> {
                    register
                }
                setr -> {
                    register
                }
                seti -> {
                    register
                }
                gtir -> {
                    register
                }
                gtri -> {
                    register
                }
                gtrr -> {
                    register
                }
                eqir -> {
                    register
                }
                eqri -> {
                    register
                }
                eqrr -> {
                    register
                }
            } to ignoreB)
        }
    }


}