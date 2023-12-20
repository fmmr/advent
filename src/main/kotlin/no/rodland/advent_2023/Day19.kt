package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 19/12/2023
// Fredrik Rødland 2023

class Day19(val input: List<String>) : Day<Int, Long, Pair<Map<String, Day19.Workflow>, List<Day19.Part>>> {

    private val parsed = input.parse()
    private val workflows = parsed.first
    override fun partOne(): Int {
        val (w, parts) = parsed
        val workflows = w + ("A" to acceptWF) + ("R" to rejectWF)
        var wf: Workflow
        return parts.map { part ->
            wf = workflows["in"]!!
            while (wf.id != "A" && wf.id != "R") {
                val id = wf.filter(part)
                wf = workflows[id]!!
            }
            wf to part.sum()
        }.filter { it.first.id == "A" }.sumOf { it.second }
    }

    override fun partTwo(): Long {
        return run("in", Ranges())
    }

    fun run(wf: String, ranges: Ranges): Long {
        if (wf == "R") return 0L
        if (wf == "A") return ranges.product()

        val workflow = workflows[wf]!!

        var currentRanges = ranges
        return workflow.filters.sumOf { rule ->
            val (matchedRule, notMatched) = rule.split(currentRanges)
            currentRanges = notMatched
            // if (notMatched.empty) return@sumOf run(rule.destination, matchedRule)
            run(rule.destination, matchedRule)
        }
    }


    data class Ranges(val x: IntRange = 1..4000, val m: IntRange = 1..4000, val a: IntRange = 1..4000, val s: IntRange = 1..4000) {
        val empty = listOf(x, m, a, s).any { it.isEmpty() }
        fun product(): Long = listOf(x, m, a, s).map { it.last.toLong() - it.first + 1 }.reduce(Long::times)
    }

    data class Workflow(val id: String, val filters: List<Rule>) {
        val defaultDestination = filters.last().destination
        fun filter(part: Part) = filters.first { it.filter(part) }.destination
    }


    sealed class Rule(open val destination: String) {
        abstract fun filter(part: Part): Boolean

        companion object {
            fun fromString(string: String): Rule {
                return when {
                    string.contains(">") -> CheckRule(
                        string.substringBefore(">").first(),
                        true,
                        string.substringAfter(">").substringBefore(":").toInt(),
                        string.substringAfter(":")
                    )

                    string.contains("<") -> CheckRule(
                        string.substringBefore("<").first(),
                        false,
                        string.substringAfter("<").substringBefore(":").toInt(),
                        string.substringAfter(":")
                    )

                    string == "A" -> acceptRule
                    string == "R" -> rejectRule
                    else -> NoCheckRule(string)
                }
            }
        }

        abstract val matchRange: IntRange
        abstract val noMatchRange: IntRange
        abstract fun split(ranges: Ranges): Pair<Ranges, Ranges>

        fun matching(range: IntRange): IntRange {
            return if (range.isEmpty()) {
                matchRange
            } else {
                range.intersect(matchRange)
            }
        }

        fun noMatching(range: IntRange): IntRange {
            return if (range.isEmpty()) {
                noMatchRange
            } else {
                range.intersect(noMatchRange)
            }
        }

        fun IntRange.intersect(other: IntRange): IntRange {
            return if (this.isEmpty() || other.isEmpty()) {
                IntRange.EMPTY
            } else {
                val start = maxOf(this.first, other.first)
                val endInclusive = minOf(this.last, other.last)
                if (start > endInclusive) {
                    IntRange.EMPTY
                } else {
                    start..endInclusive
                }
            }
        }
    }

    class CheckRule(private val valToCheck: Char, private val greaterThan: Boolean, private val intToCheck: Int, private val dest: String) : Rule(dest) {

        override fun toString(): String {
            return valToCheck.toString() + (if (greaterThan) '>' else '<') + intToCheck + "=>" + dest
        }

        override fun filter(part: Part) = when (greaterThan) {
            true -> part.value(valToCheck) > intToCheck
            false -> part.value(valToCheck) < intToCheck
        }

        override val noMatchRange: IntRange = if (greaterThan) 1..intToCheck else intToCheck..4000
        override val matchRange: IntRange = if (greaterThan) (intToCheck + 1)..4000 else 1..<intToCheck
        override fun split(ranges: Ranges): Pair<Ranges, Ranges> {
            return when (valToCheck) {
                'x' -> Ranges(matching(ranges.x), ranges.m, ranges.a, ranges.s) to Ranges(noMatching(ranges.x), ranges.m, ranges.a, ranges.s)
                'm' -> Ranges(ranges.x, matching(ranges.m), ranges.a, ranges.s) to Ranges(ranges.x, noMatching(ranges.m), ranges.a, ranges.s)
                'a' -> Ranges(ranges.x, ranges.m, matching(ranges.a), ranges.s) to Ranges(ranges.x, ranges.m, noMatching(ranges.a), ranges.s)
                's' -> Ranges(ranges.x, ranges.m, ranges.a, matching(ranges.s)) to Ranges(ranges.x, ranges.m, ranges.a, noMatching(ranges.s))
                else -> error("unknown char: $valToCheck")
            }
        }

    }

    data class NoCheckRule(val dest: String) : Rule(dest) {
        override fun filter(part: Part) = true
        override val matchRange: IntRange = 1..4000
        override val noMatchRange: IntRange = IntRange.EMPTY
        override fun split(ranges: Ranges): Pair<Ranges, Ranges> = ranges to Ranges(IntRange.EMPTY, IntRange.EMPTY, IntRange.EMPTY, IntRange.EMPTY)
        override fun toString(): String = dest
    }

    data class Part(val x: Int, val m: Int, val a: Int, val s: Int) {
        fun value(char: Char) = when (char) {
            'x' -> x
            'm' -> m
            'a' -> a
            's' -> s
            else -> error("unknown char: $char")
        }

        fun sum() = x + m + a + s
    }

    companion object {
        val acceptRule = NoCheckRule("A")
        val rejectRule = NoCheckRule("R")
        val acceptWF = Workflow("A", listOf(acceptRule))
        val rejectWF = Workflow("R", listOf(rejectRule))

    }


    override fun List<String>.parse(): Pair<Map<String, Workflow>, List<Part>> {

        val (workflowStrings, ratingStrings) = joinToString("\n").split("\n\n").map { it.split("\n") }
        val workflows = workflowStrings.map {
            val id = it.substringBefore("{")
            val rules = it.substringAfter("{").substringBefore("}").split(",")
                .map { rule -> Rule.fromString(rule) }
            Workflow(id, rules)
        }
        val parts = ratingStrings.map {
            val x = it.substringAfter("x=").substringBefore(",")
            val m = it.substringAfter("m=").substringBefore(",")
            val a = it.substringAfter("a=").substringBefore(",")
            val s = it.substringAfter("s=").substringBefore("}")
            Part(x.toInt(), m.toInt(), a.toInt(), s.toInt())
        }
        return (workflows).associateBy { it.id } to parts
    }

    override val day = "19".toInt()
}
