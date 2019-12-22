package no.rodland.advent_2019

object Day22 {
    fun partOne(list: List<String>): Int {
        return 2
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
