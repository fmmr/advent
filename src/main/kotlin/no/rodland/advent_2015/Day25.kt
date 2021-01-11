package no.rodland.advent_2015

object Day25 {
    fun partOne(): Long {
        val row = 2947
        val col = 3029
        val rowOfFirstNumber = row + col - 2
        var i = 1
        val position = generateSequence(1) { it + i++ }.drop(rowOfFirstNumber).first() + col - 2
        return generateSequence(20151125L) { it.calc() }.drop(position).first()
    }

    private fun Long.calc(): Long = (this * 252533) % 33554393
}
