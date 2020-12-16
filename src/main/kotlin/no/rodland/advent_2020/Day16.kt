package no.rodland.advent_2020

object Day16 {
    fun partOne(str: String): Int {
        val splitted = str.split("\n\n")
        val rules = splitted[0].split("\n").map { Rule(it) }
        val nearbyTickets = splitted[2].split("\n").run { subList(1, size) }.map { Ticket(it) }
        return nearbyTickets.flatMap { it.getInvalid(rules) }.sum()
    }

    private fun List<Rule>.findRule(index: Int, allTickets: List<Ticket>): Rule {
        return find { rule ->
            rule.all { range -> allTickets.map { it[index] }.all { i -> i in range } }
        } ?: error("No rule found for idx: $index")
    }

    fun partTwo(str: String): Int {
//        val ticket: Ticket = Ticket(splitted[1].split("\n")[1])
//        val allTickets = (nearbyTickets)
//        val validTickets = allTickets.filterNot { it.invalid(rules) }
//        val invalidTickets = allTickets - validTickets
//        val checkTickets = validTickets + listOf(ticket)
//
//
//
//        ticket.forEachIndexed { index, value ->
//            val rule: Rule = rules.findRule(index, checkTickets)
//            println("idx: $index, value: $value rule: $rule")
//        }
        return 2
    }

    data class Rule(val name: String, val ranges: List<IntRange>) : Iterable<IntRange> {
        constructor(str: String) : this(str.split(": ")[0], str.split(": ")[1].run { split(" or ").map { it.split("-").run { get(0).toInt()..get(1).toInt() } } })

        override fun iterator(): Iterator<IntRange> = ranges.iterator()
    }

    data class Ticket(val values: List<Int>) : Iterable<Int> {
        constructor(str: String) : this(str.split(",").map { it.toInt() })

        override fun iterator(): Iterator<Int> = values.iterator()
        operator fun get(index: Int) = values[index]
        fun invalid(rules: List<Rule>): Boolean = any { i ->
            rules.all { rule ->
                rule.none { range ->
                    i in range
                }
            }
        }

        fun getInvalid(rules: List<Rule>): List<Int> = filter { i ->
            rules.all { rule ->
                rule.none { range ->
                    i in range
                }
            }
        }
    }

}



