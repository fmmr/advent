import java.io.File

class Day1 {
    private fun readFileLineByLineUsingForEachLine(fileName: String): List<Int> {
        val intList = mutableListOf<Int>()
        File(fileName).forEachLine { intList.add(it.toInt()) }
        return intList
    }

    private fun sequence(): Sequence<Int> {
        val input = readFileLineByLineUsingForEachLine("src/input_1.txt")
        var index = 0
        return generateSequence {
            if (index == input.size) {
                index = 0
            }
            input[index++]
        }
    }

    fun main(args: Array<String>) {
        (1 to 2).report(3) {
            day1_2_take3()
        }
        (1 to 2).report(4) {
            day1_2_take4()
        }
    }

    private fun day1_2_take4() {
        val seenAcc = mutableSetOf<Int>()
        var acc = 0
        sequence().first {
            acc += it
            !seenAcc.add(acc)
        }
        println(acc)
    }

    private fun day1_2_take3() {
        val seenAcc = mutableSetOf<Int>()
        var acc = 0
        sequence().takeWhile {
            acc += it
            seenAcc.add(acc)
        }.toList()
        println(acc)
    }

}