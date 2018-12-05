class Day2 {
    fun main(args: Array<String>) {
        (2 to 1).report {
            partOne()
        }
        (2 to 1).report(2) {
            partOne_take2()
        }
        (2 to 1).report(3) {
            partOne_take3()
        }
        (2 to 2).report {
            partTwo()
        }
        (2 to 2).report(2) {
            partTwo_take2()
        }
    }

    private val list = "src/input_2.txt".readFile()

    private fun partTwo() {
        val hei = list.mapIndexed { i, s ->
            list.subList(i, list.size).map { getCommonChars(it, s) }.filter { it.length == s.length - 1 }
        }.flatten()
        println("part2 = $hei")
    }

    private fun partTwo_take2() {

        val hei = list.mapIndexedNotNull { i, s ->
            list.subList(i, list.size).map { getCommonChars(it, s) }.firstOrNull { it.length == s.length - 1 }
        }
        println("part2 = $hei")
    }


    private fun getCommonChars(str1: String, str2: String): String {
        return str1.filterIndexed { i, c -> c == str2[i] }
    }

    private fun partOne_take2() {
        val list = "src/input_2.txt".readFile()
        val (twos, threes) = list.fold(0 to 0) { (twos, threes), s ->
            val mapValues = s.groupBy { it }.mapValues { it.value.size }.filter { it.value == 2 || it.value == 3 }.values.toSet()
            Pair(twos + mapValues.count { it == 2 }, threes + mapValues.count { it == 3 })
        }
        println("$twos")
        println("$threes")
        println("${twos * threes}")
    }

    private fun partOne_take3() {
        val list = "src/input_2.txt".readFile()
        val (twos, threes) = list.fold(0 to 0) { (twos, threes), s ->
            val mapValues = ('a'..'z').map { countChars(s, it) }.filter { it == 2 || it == 3 }.toSet()
            Pair(twos + mapValues.count { it == 2 }, threes + mapValues.count { it == 3 })
        }
        println("$twos")
        println("$threes")
        println("${twos * threes}")
    }


    private fun partOne() {
        var list2 = "src/input_2.txt".readFile()
        var list3 = "src/input_2.txt".readFile()
        val origSize = list2.size
        val charRange = 'a'..'z'
        charRange.forEach { c ->
            list2 = numInList(list2, c, 2)
        }
        charRange.forEach { c ->
            list3 = numInList(list3, c, 3)
        }

        val num2 = origSize - list2.size
        println("sumBy2 = $num2")
        val num3 = origSize - list3.size
        println("sumBy3 = $num3")
        println("part1 = ${num2 * num3}")
    }

    private fun numInList(list: List<String>, chr: Char, n: Int): List<String> {
        return list.filter { str -> !charsIsN(str, chr, n) }
    }

    private fun charsIsN(str: String, chr: Char, n: Int) = countChars(str, chr) == n

    private fun countChars(str: String, chr: Char) = str.count { it == chr }
}