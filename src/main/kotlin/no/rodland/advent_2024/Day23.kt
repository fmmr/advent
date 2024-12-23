package no.rodland.advent_2024

import no.rodland.advent.Day

// template generated: 23/12/2024
// Fredrik Rødland 2024

class Day23(val input: List<String>) : Day<Int, String, List<Pair<String, String>>> {

    private val parsed = input.parse()
    private val computers = buildMap<String, MutableSet<String>> {
        parsed.forEach { (first, second) ->
            computeIfAbsent(first) { mutableSetOf() }.add(second)
            computeIfAbsent(second) { mutableSetOf() }.add(first)
        }
    }

    override fun partOne(): Int {
        return groupsOfThree().filter { it.any { c -> c.startsWith("t") } }.size
    }

    override fun partTwo(): String {
        return findLargestGroup().sorted().joinToString(",")
    }

    private fun groupsOfThree(): Set<List<String>> {
        return computers
            .mapNotNull { (computer, neighbours) ->
                val neighboursList = neighbours.toList()
                neighboursList.indices.mapNotNull { i ->
                    val restRange = i + 1 until neighboursList.size
                    restRange.mapNotNull { j ->
                        val neighbor1 = neighboursList[i]
                        val neighbor2 = neighboursList[j]
                        if (computers[neighbor1]?.contains(neighbor2) == true) {
                            setOf(computer, neighbor1, neighbor2).sorted()
                        } else {
                            null
                        }
                    }
                }.flatten()
            }
            .flatten()
            .toSet()
    }

    private fun findLargestGroup(): Set<String> {
        return groups().maxBy { it.size }.sorted().toSet()
    }

    private fun groups(): List<MutableSet<String>> {
        val visited = mutableSetOf<String>()
        val groups = computers.keys.mapNotNull { computer ->
            if (computer !in visited) {
                val group = mutableSetOf(computer)
                val neighbors = computers[computer].orEmpty()
                for (neighbor in neighbors) {
                    if (group.all { computers[it]?.contains(neighbor) == true }) {
                        group.add(neighbor)
                    }
                }
                visited.addAll(group)
                group
            } else {
                null
            }
        }
        return groups
    }

    override fun List<String>.parse(): List<Pair<String, String>> {
        return map { line ->
            val (first, second) = line.split("-")
            first to second
        }
    }

    override val day = "23".toInt()
}
