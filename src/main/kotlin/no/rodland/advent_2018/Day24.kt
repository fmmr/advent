package no.rodland.advent_2018

import get
import getString

object Day24 {


    // 989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3
    val re = """(\d+) units each with (\d+) hit points \(?(.*?)\)? ?with an attack that does (\d+) (.*) damage at initiative (\d+)""".toRegex()
    val reWeak = """.*weak to ([^;]*).*""".toRegex()
    val reImmune = """.*immune to ([^;]*).*""".toRegex()

    fun partOne(imuneList: List<String>, infectionList: List<String>): Int {
        val immunes = imuneList.map { Group.of(Team.IMMUNE, it) }
        val infections = infectionList.map { Group.of(Team.INFECTION, it) }
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Group(val team: Team, var num: Int, val hitPoints: Int, val damage: Int, val attack: String, val initiative: Int, val weaks: List<String>, val immune: List<String>) {

        val effective = num * damage

        companion object {
            fun of(team: Team, str: String): Group {
                val info = re.getString(str, 3) ?: ""
                val weakList = reWeak.getString(info)?.split(",")?.map { it.trim() } ?: emptyList()
                val immuneList = reImmune.getString(info)?.split(", ")?.map { it.trim() } ?: emptyList()
                return Group(team, re.get(str), re.get(str, 2), re.get(str, 4), re.getString(str, 5)!!, re.get(str, 6), weakList, immuneList)
            }
        }
    }

    enum class Team {
        IMMUNE, INFECTION
    }


}