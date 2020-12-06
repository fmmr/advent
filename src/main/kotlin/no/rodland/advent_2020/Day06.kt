package no.rodland.advent_2020

object Day06 {
    fun partOne(list: String): Int {
        return list.split("\n\n")
            .map { it.toCharArray().filterNot { it == '\n' }.distinct().count() }
            .sum()
    }

    fun partTwo(list: String): Int {
        return list.split("\n\n").map { it.getGroupCount() }.sum()
    }

    private fun String.getGroupCount(): Int {
        val unique = toCharArray().filterNot { it == '\n' }.distinct()
        val persons = this.split("\n")
        return unique.count { ch -> persons.all { ch in it } }
    }
}

