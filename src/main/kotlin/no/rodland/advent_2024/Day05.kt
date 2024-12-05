package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 05/12/2024
// Fredrik Rødland 2024

class Day05(val input: List<String>) : Day<Int, Int, Pair<List<Pair<Int, Int>>, List<List<Int>>>> {

    private val parsed = input.parse()
    private val rules = parsed.first
    private val pages = parsed.second

    override fun partOne(): Int {
        return pages
            .filter { it.valid() }
            .sumOf { it.middle() }
    }

    override fun partTwo(): Int {
        return pages
            .filterNot { it.valid() }
            .map { it.reorder() }
            .sumOf { it.middle() }
    }

    private fun List<Int>.valid() = pairs().all { it in rules }

    private fun List<Int>.reorder(): List<Int> {
        val breaking = pairs()
            .filterNot { it in rules }
            .groupBy({ it.first }, { it.second })

        val fold = breaking.keys.fold(this) { acc, b ->
            acc.move(b, breaking[b]!!)
        }
        return if (fold.valid()){
            // println("yeah.       $this    $fold    $breaking")
            fold
        }else{
            // still not right - just run it again
            // println("reordering. $this    $fold    $breaking")
            fold.reorder()
        }
    }

    private fun List<Int>.move(element: Int, after: List<Int>): List<Int> {
        val indexElement = indexOf(element)
        val moveAfter = indexOfLast { it in after }
        return subList(0, indexElement) + subList(indexElement + 1, moveAfter + 1) + element + subList(moveAfter + 1, size)
    }

    private fun List<Int>.middle(): Int = get(size / 2)

    private fun List<Int>.pairs(): List<Pair<Int, Int>> {
        return flatMapIndexed { index: Int, i: Int ->
            subList(index + 1, size).map { i to it }
        }
    }


    override fun List<String>.parse(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val (rulesStr, pagesStr) = this.joinToString("\n").split("\n\n").map { it.split("\n") }

        val pages = pagesStr.map { line ->
            line.split(",").map { it.toInt() }
        }
        val rules = rulesStr.map { line ->
            line.split("|").map { it.toInt() }.let { it[0] to it[1] }
        }
        return rules to pages
    }

    override val day = "05".toInt()
}




