package no.rodland.advent_2019

object Day22 {
    fun partOne(list: List<String>, deck: List<Int>): List<Int> {
        return list.fold(deck) { acc, cmd -> doit(cmd, acc) }
    }

    private fun doit(cmd: String, list: List<Int>): List<Int> {
        if (cmd == "deal into new stack") {
            return list.deal_new()
        }
        "cut -(\\d+)".toRegex().matchEntire(cmd)?.let { match ->
            val (precision) = match.destructured
            return list.cutNegative(precision.toInt())
        }
        "cut (\\d+)".toRegex().matchEntire(cmd)?.let { match ->
            val (precision) = match.destructured
            return list.cut(precision.toInt())
        }
        "deal with increment (\\d+)".toRegex().matchEntire(cmd)?.let { match ->
            val (precision) = match.destructured
            return list.deal_increment(precision.toInt())
        }
        error("unable to parse $cmd")
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}

fun List<Int>.deal_new(): List<Int> = reversed()
fun List<Int>.deal_increment(i: Int): List<Int> {
    val newList = Array(size) { 0 }
    var count = 0
    forEachIndexed { idx, v ->
        newList[count] = v
        count += i
        if (count > size) {
            count -= size
        }
    }
    return newList.toList()
}

fun List<Int>.cut(i: Int): List<Int> = subList(i, size) + subList(0, i)
fun List<Int>.cutNegative(i: Int): List<Int> = subList(size - i, size) + subList(0, size - i)
