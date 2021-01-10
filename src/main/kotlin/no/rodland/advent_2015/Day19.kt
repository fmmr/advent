package no.rodland.advent_2015

@Suppress("UNUSED_PARAMETER")
object Day19 {
    fun partOne(list: List<String>): Int {
        val (map, code) = parseList(list)
        return map.flatMap {
            replacements(it, code)
        }.toSet().size
    }

    private fun replacements(replacement: Pair<String, String>, code: String): List<String> {
        val regex = ("(${replacement.first})").toRegex()
        val matches = regex.findAll(code)

        val ranges = matches.mapNotNull { it.groups[1]?.range }.toList()

        return ranges.map { code.substring(0, it.first) + replacement.second + code.substring(it.last + 1, code.length) }
    }

    private fun parseList(list: List<String>): Pair<List<Pair<String, String>>, String> {
        val code = list.last()
        val rest = list.subList(0, list.size - 2).map { it.split(" => ") }.map { it.first() to it.last() }
        return rest to code
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
