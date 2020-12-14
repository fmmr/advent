package no.rodland.advent_2015

object Day13 {
    val regex = """(.+) would (lose|gain) (\d+) happiness units by sitting next to (.+).""".toRegex()

    fun partOne(list: List<String>): Int {
        val all = list.map { Line(it) }
        val persons = all.map { it.persons.first }.toSet()
        val allMap = all.map { it.persons to it }.toMap()
        val sorted = persons
            .flatMap { person ->
                all.filter { it.persons.first == person }.map { person to (it.persons.second to it.units + allMap[it.persons.second to it.persons.first]!!.units) }
            }
//            .asSequence()
            .sortedByDescending { it.second.second }
            .toList()
        persons.forEach {}

        return 2
    }

    // 898  That's not the right answer; your answer is too high.
    fun partTwo(list: List<String>): Int {
        return 2
    }


    data class Line(val persons: Pair<String, String>, val units: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(mr.component1() to mr.component4(), if ("gain" == mr.component2()) mr.component3().toInt() else -mr.component3().toInt())
    }
}
