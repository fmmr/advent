package no.rodland.advent_2015

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

object Day12 {
    fun partOne(data: String): Int {
        val numbers = """-?\d+""".toRegex()
        return numbers.findAll(data).map { it.value.toInt() }.sum()
    }

    fun partTwo(data: String): Int {
        return (Parser.default().parse(StringBuilder(data)) as JsonObject).sumJson()
    }

    fun Any?.sumJson(): Int = when (this) {
        is Int -> this
        is JsonArray<*> -> map { it.sumJson() }.sum()
        is JsonObject -> if (containsValue("red")) 0 else map { it.value.sumJson() }.sum()
        else -> 0
    }
}
