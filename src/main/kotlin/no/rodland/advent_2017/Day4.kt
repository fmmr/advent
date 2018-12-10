package no.rodland.advent_2017

object Day4 {

    fun partOne(list: List<String>): Int {
        return list.count { isValidOne(it) { s1, s2 -> s1 == s2 } }
    }

    fun partTwo(list: List<String>): Int {
        return list.count { isValidOne(it) { s1, s2 -> s1.toList().sorted() == s2.toList().sorted() } }
    }

    private fun isValidOne(str: String, predicate: (String, String) -> Boolean): Boolean {
        val list1 = str.split(" ")
        return list1.map { tmp -> list1.count { predicate.invoke(it, tmp) } > 1 }.none { it }
    }


}