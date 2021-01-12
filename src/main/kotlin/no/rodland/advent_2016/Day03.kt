package no.rodland.advent_2016

object Day03 {
    fun partOne(list: List<String>): Int {
        return list
                .map { line -> line.trim().split(" +".toRegex()).map { it.toInt() } }
                .count { it.isTriangle() }
    }

    private fun List<Int>.isTriangle(): Boolean {
        return this.size == 3 && maxOrNull()!!.let { (this - it).sum() > it }
    }

    fun partTwo(list: List<String>): Int {
        return list
                .map { line -> line.trim().split(" +".toRegex()).map { it.toInt() } }
                .let { intList ->
                    intList.map { it[0] } + intList.map { it[1] } + intList.map { it[2] }
                }
                .chunked(3)
                .count { it.isTriangle() }
    }
}
