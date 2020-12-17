package no.rodland.advent_2020

import product

object Day16 {
    fun partOne(str: String): Int {
        val (rules, _, nearbyTickets) = parseInput(str)
        return nearbyTickets.flatMap { it.getInvalid(rules) }.sum()
    }

    fun partTwo(str: String): Long {
        val (rules, ticket, nearbyTickets) = parseInput(str)
        val okTickets = nearbyTickets.filterNot { it.invalid(rules) }
        val usedRules = mutableSetOf<Rule>()
        val indexRules = ticket
            .mapIndexed { index, value -> index to rules.findRules(index, okTickets) }
            .sortedBy { it.second.size }
            .map { (idx, rules) ->
                val rule = rules.first { r -> r !in usedRules }
                usedRules.add(rule)
                idx to rule
            }
        return indexRules
            .filter { it.second.name.startsWith("departure") }
            .map { ticket[it.first] }
            .product()
    }

    private fun List<Rule>.findRules(index: Int, allTickets: List<Ticket>): List<Rule> = filter { rule -> rule.inRange(allTickets.map { it[index] }) }

    data class Rule(val name: String, val ranges: List<IntRange>) : Iterable<IntRange> by ranges {
        constructor(str: String) : this(str.split(": ")[0], str.split(": ")[1].run { split(" or ").map { it.split("-").run { get(0).toInt()..get(1).toInt() } } })

        fun inRange(ints: List<Int>): Boolean = ints.all { i -> inRange(i) }

        private fun inRange(int: Int): Boolean = any { range -> int in range }
    }

    // index 0 matchres bnoth train and wagon
    data class Ticket(val values: List<Int>) : List<Int> by values {
        constructor(str: String) : this(str.split(",").map { it.toInt() })

        fun invalid(rules: List<Rule>): Boolean = getInvalid(rules).isNotEmpty()
        fun getInvalid(rules: List<Rule>): List<Int> = filter { i ->
            rules.all { rule ->
                rule.none { range ->
                    i in range
                }
            }
        }
    }

    private fun parseInput(str: String): Triple<List<Rule>, Ticket, List<Ticket>> {
        val splitted = str.split("\n\n")
        val rules = splitted[0].split("\n").map { Rule(it) }
        val ticket = Ticket(splitted[1].split("\n")[1])
        val nearbyTickets = splitted[2].split("\n").run { subList(1, size) }.map { Ticket(it) }
        return Triple(rules, ticket, nearbyTickets)
    }
}

