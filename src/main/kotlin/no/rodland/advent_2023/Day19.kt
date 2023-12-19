package no.rodland.advent_2023

import no.rodland.advent.Day

// template generated: 19/12/2023
// Fredrik Rødland 2023

class Day19(val input: List<String>) : Day<Int, Long, Pair<Map<String, Day19.Workflow>, List<Day19.Part>>> {

    private val parsed = input.parse()

    override fun partOne(): Int {
        val (workflows, parts) = parsed
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
        return 2
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
        return (workflows + acceptWF + rejectWF).associateBy { it.id } to parts
    }

    data class Workflow(val id: String, val filters: List<Rule>) {
        fun filter(part: Part) = filters.first { it.filter(part) }.destination
    }

    class CheckRule(private val valToCheck: Char, private val greaterThan: Boolean, private val intToCheck: Int, dest: String) : Rule(dest) {
        override fun filter(part: Part) = when (greaterThan) {
            true -> part.value(valToCheck) > intToCheck
            false -> part.value(valToCheck) < intToCheck
        }
    }

    sealed class Rule(open val destination: String) {
        abstract fun filter(part: Part): Boolean

        companion object {
            fun fromString(string: String): Rule {
                return when {
                    string.contains(">") -> {
                        val x = string.substringBefore(">")
                        val i = string.substringAfter(">").substringBefore(":")
                        val dest = string.substringAfter(":")
                        CheckRule(x.first(), true, i.toInt(), dest)
                    }

                    string.contains("<") -> {
                        val x = string.substringBefore("<")
                        val i = string.substringAfter("<").substringBefore(":")
                        val dest = string.substringAfter(":")
                        CheckRule(x.first(), false, i.toInt(), dest)
                    }

                    string == "A" -> acceptRule
                    string == "R" -> rejectRule
                    else -> NoCheckRule(string)
                }
            }
        }
    }


    open class NoCheckRule(dest: String) : Rule(dest) {
        override fun filter(part: Part) = true
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

    override val day = "19".toInt()
}
