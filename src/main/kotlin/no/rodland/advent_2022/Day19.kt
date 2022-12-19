package no.rodland.advent_2022

import no.rodland.advent_2022.Day19.Type.CLAY
import no.rodland.advent_2022.Day19.Type.GEODE
import no.rodland.advent_2022.Day19.Type.OBSIDIAN
import no.rodland.advent_2022.Day19.Type.ORE

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day19 {
    fun partOne(list: List<String>): Int {
        val blueprints = list.parse()
        return 2
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }

    enum class Type { ORE, CLAY, OBSIDIAN, GEODE }

    data class Price(val amount: Int, val type: Type)
    data class Robot(val type: Type, val price: List<Price>)
    data class BluePrint(val id: Int, val robots: List<Robot>)

    fun List<String>.parse(): List<BluePrint> {
        // Blueprint 30: Each ore robot costs 3 ore. Each clay robot costs 3 ore. Each obsidian robot costs 2 ore and 7 clay. Each geode robot costs 2 ore and 9 obsidian.
        return map { line ->
            val id = line.substringAfter("Blueprint ").substringBefore(": ").toInt()
            val oreCost = line.substringAfter("Each ore robot costs ").substringBefore(" ore. Each clay robot costs").toInt()
            val clayCost = line.substringAfter("Each clay robot costs ").substringBefore(" ore. Each obsidian robot costs ").toInt()
            val obsidianCostOre = line.substringAfter("Each obsidian robot costs ").substringBefore(" ore and ").toInt()
            val obsidianCostClay = line.substringAfter(" ore and ").substringBefore(" clay. Each geode robot costs ").toInt()
            val geodeCostOre = line.substringAfter("Each geode robot costs ").substringBefore(" ore and ").toInt()
            val geodeCostObsidian = line.substringAfterLast(" ore and ").substringBefore(" obsidian").toInt()

            BluePrint(
                id, listOf(
                    Robot(ORE, listOf(Price(oreCost, ORE))),
                    Robot(CLAY, listOf(Price(clayCost, ORE))),
                    Robot(OBSIDIAN, listOf(Price(obsidianCostOre, ORE), Price(obsidianCostClay, CLAY))),
                    Robot(GEODE, listOf(Price(geodeCostOre, ORE), Price(geodeCostObsidian, OBSIDIAN))),
                )
            )
        }
    }
}
