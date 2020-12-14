package no.rodland.advent_2015

object Day13 {
    val regex = """(.+) would (lose|gain) (\d+) happiness units by sitting next to (.+).""".toRegex()

    fun partOne(list: List<String>): Int {
        val all = list.map { Line(it) }
        val persons = all.map { it.persons.first }.toSet()
        val allMap = all.map { it.persons to it }.toMap()
        val sorted = persons
            .flatMap { person ->
                all.filter { it.persons.first == person }.map { (person to it.persons.second) to it.units + (allMap[it.persons.second to it.persons.first]!!.units) }
            }
            .asSequence()
            .chunked(2)
            .map { it.first() }
            .sortedByDescending { it.second }
            .toList()
        val placements = mutableListOf<Pair<String, String>>()
        val seats = persons.mapNotNull { person ->
            pickNext(person, sorted, placements)
        }
        println("persons: $persons")
        println("found all seats: $seats")
//        return seats.sumBy { it.second }
        return 2
    }

    private fun pickNext(person: String, sorted: List<Pair<Pair<String, String>, Int>>, placements: MutableList<Pair<String, String>>): Pair<Pair<String, String>, Int>? {
        val candidates = sorted
            .filter { it.first.first == person || it.first.second == person }
            .filterNot { placements.contains(it.first) }
            .filterNot { (p, _) -> placements.count { it.first == p.first } + placements.count { it.second == p.first } >= 2 }
            .filterNot { (p, _) -> placements.count { it.first == p.second } + placements.count { it.second == p.second } >= 2 }
            .firstOrNull()

        if (candidates == null) {
            println("hmmm... no more space for $person given $placements")
            return null
        } else {
            placements.add(candidates.first)
            return candidates
        }

    }

    // 898  That's not the right answer; your answer is too high.
    // 678  That's not the right answer; your answer is too low.
    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Line(val persons: Pair<String, String>, val units: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(mr.component1() to mr.component4(), if ("gain" == mr.component2()) mr.component3().toInt() else -mr.component3().toInt())
    }
}
