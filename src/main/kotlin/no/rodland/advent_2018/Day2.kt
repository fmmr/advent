object Day2 {
    fun partOne(list: List<String>): Int {
        var list2 = listOf(list).flatten()
        var list3 = listOf(list).flatten()
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
        return num2 * num3
    }

    fun partOne_take2(list: List<String>): Int {
        val (twos, threes) = list.fold(0 to 0) { (twos, threes), s ->
            val mapValues = s.groupBy { it }.mapValues { it.value.size }.filter { it.value == 2 || it.value == 3 }.values.toSet()
            Pair(twos + mapValues.count { it == 2 }, threes + mapValues.count { it == 3 })
        }
        println("$twos")
        println("$threes")
        println("${twos * threes}")
        return twos * threes
    }

    fun partOne_take3(list: List<String>): Int {
        val (twos, threes) = list.fold(0 to 0) { (twos, threes), s ->
            val mapValues = ('a'..'z').map { countChars(s, it) }.filter { it == 2 || it == 3 }.toSet()
            Pair(twos + mapValues.count { it == 2 }, threes + mapValues.count { it == 3 })
        }
        println("$twos")
        println("$threes")
        println("${twos * threes}")
        return twos * threes
    }

    fun partTwo(list1: List<String>): String {
        return list1.mapIndexed { i, s ->
            list1.subList(i, list1.size).map { getCommonChars(it, s) }.filter { it.length == s.length - 1 }
        }.flatten()[0]
    }

    fun partTwo_take2(list1: List<String>): String {

        return list1.mapIndexedNotNull { i, s ->
            list1.subList(i, list1.size).map { getCommonChars(it, s) }.firstOrNull { it.length == s.length - 1 }
        }[0]
    }


    private fun getCommonChars(str1: String, str2: String): String {
        return str1.filterIndexed { i, c -> c == str2[i] }
    }


    private fun numInList(list: List<String>, chr: Char, n: Int): List<String> {
        return list.filter { str -> !charsIsN(str, chr, n) }
    }

    private fun charsIsN(str: String, chr: Char, n: Int) = countChars(str, chr) == n

    private fun countChars(str: String, chr: Char) = str.count { it == chr }
}