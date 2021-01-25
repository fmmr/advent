package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day16 {

    val regexS = """s(\d+)""".toRegex()
    val regexX = """x(\d+)/(\d+)""".toRegex()
    val regexP = """p(.)/(.)""".toRegex()

    // not correct: fdpjiomekhbcnlag
    fun partOne(start: String, instruction: String): String {
        val instructions = instruction.split(",")
        return danceSeq(start, instructions).first()
    }

    fun partTwo(start: String, instruction: String): String {
        val instructions = instruction.split(",")
        val seq = danceSeq(start, instructions)
        seq.take(10).forEach { println(it) }
        return "2"
    }

    private fun danceSeq(start: String, instructions: List<String>) = generateSequence(start) { s ->
        dance(instructions, s)
    }.drop(1)

    private fun dance(instructions: List<String>, start: String): String {
        return instructions.fold(start) { acc, s ->
            val newAcc = when {
                s.startsWith("s") -> spin(s, acc)
                s.startsWith("x") -> exchange(s, acc)
                s.startsWith("p") -> partner(s, acc)
                else -> error("not able to parse $acc, $s")
            }
            // println("$acc     $s     $newAcc")
            newAcc
        }
    }

    private fun partner(s: String, acc: String): String {
        val destructured = regexP.find(s)!!.destructured
        val char1 = destructured.component1()
        val char2 = destructured.component2()
        val num1 = acc.indexOf(char1)
        val num2 = acc.indexOf(char2)
        return swap(acc, num1, num2)
    }

    private fun spin(s: String, acc: String): String {
        val num = regexS.find(s)!!.destructured.component1().toInt()
        return acc.substring(acc.length - num, acc.length) + acc.substring(0, acc.length - num)
    }

    private fun exchange(s: String, acc: String): String {
        val destructured = regexX.find(s)!!.destructured
        val num1 = destructured.component1().toInt()
        val num2 = destructured.component2().toInt()
        return swap(acc, num1, num2)
    }

    private fun swap(acc: String, num1: Int, num2: Int): String {
        val ar = acc.toCharArray()
        val was1 = ar[num1]
        ar[num1] = ar[num2]
        ar[num2] = was1
        return ar.joinToString("")
    }

}
