package no.rodland.advent_2020

object Day16 {
    fun partOne(str: String): Int {
        val (rules, _, nearbyTickets) = parseInput(str)
        return nearbyTickets.flatMap { it.getInvalid(rules) }.sum()
    }

    fun partTwo(str: String): Long {
        val (rules, ticket, nearbyTickets) = parseInput(str)
        val okTickets = nearbyTickets.filterNot { it.invalid(rules) }
        val allTickets = okTickets + listOf(ticket)
        val indexRules = ticket.mapIndexed() { index, value ->
            index to rules.findRule(index, allTickets)
        }
        val departuresIndices = indexRules.filter { it.second.name.startsWith("departure") }.map { it.first }
        println("Found ${departuresIndices.size} departure related rules (should be 6)")
        val product = departuresIndices.map { ticket[it] }.fold(0L) { acc, n -> acc * n.toLong() }
        return 2
    }

    // That's not the right answer; your answer is too low

    private fun List<Rule>.findRule(index: Int, allTickets: List<Ticket>): Rule {
        val matcingRules = filter { rule ->
            rule.inRange(allTickets.map { it[index] })
        }
        return matcingRules.first()
    }


    private fun parseInput(str: String): Triple<List<Rule>, Ticket, List<Ticket>> {
        val splitted = str.split("\n\n")
        val rules = splitted[0].split("\n").map { Rule(it) }
        val ticket: Ticket = Ticket(splitted[1].split("\n")[1])
        val nearbyTickets = splitted[2].split("\n").run { subList(1, size) }.map { Ticket(it) }
        return Triple(rules, ticket, nearbyTickets)
    }


    data class Rule(val name: String, val ranges: List<IntRange>) : Iterable<IntRange> {
        constructor(str: String) : this(str.split(": ")[0], str.split(": ")[1].run { split(" or ").map { it.split("-").run { get(0).toInt()..get(1).toInt() } } })

        override fun iterator(): Iterator<IntRange> = ranges.iterator()

        fun inRange(ints: List<Int>): Boolean = ints.all { i ->
            any { range -> i in range }
        }
    }

    // index 0 matchres bnoth train and wagon
    data class Ticket(val values: List<Int>) : Iterable<Int> {
        constructor(str: String) : this(str.split(",").map { it.toInt() })

        override fun iterator(): Iterator<Int> = values.iterator()
        operator fun get(index: Int) = values[index]
        fun invalid(rules: List<Rule>): Boolean = getInvalid(rules).isNotEmpty()
        fun getInvalid(rules: List<Rule>): List<Int> = filter { i ->
            rules.all { rule ->
                rule.none { range ->
                    i in range
                }
            }
        }
    }
}



