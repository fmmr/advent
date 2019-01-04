package no.rodland.advent_2018

import get
import getString

object Day24 {
    val re = """(\d+) units each with (\d+) hit points \(?(.*?)\)? ?with an attack that does (\d+) (.*) damage at initiative (\d+)""".toRegex()
    val reWeak = """.*weak to ([^;]*).*""".toRegex()
    val reImmune = """.*immune to ([^;]*).*""".toRegex()

    fun partOne(imuneList: List<String>, infectionList: List<String>): Int {
        val immunes = imuneList.map { Group.of(Team.IMMUNE, it) }
        val infections = infectionList.map { Group.of(Team.INFECTION, it) }


        val all = (immunes + infections).sorted()

        while (all.filter { it.alive() }.map { it.team }.distinct().count() > 1) {
            val fighters = all.filter { it.alive() }
            val selected: MutableSet<Group> = mutableSetOf()
            // targetSelection
            val targetSelection = fighters.map { selector ->
                val available = fighters.filter { !it.isA(selector.team) }.filter { !selected.contains(it) }
                val opponent = selector.chooseOpponent(available)
                if (opponent != null) {
                    selected.add(opponent)
                }
                selector to opponent
            }.sortedBy { it.first.initiative * -1 }

            // attack
            targetSelection.forEach { it.first.hit(it.second) }
//            val debug = fighters.map { it.team to it.alive() }.partition { it.second }
//            println("debug = ${debug}")
        }

        val endGame = all.partition { it.alive() }
        return endGame.first.sumBy { it.num }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Group(
            val team: Team,
            var num: Int,
            val hitPoints: Int,
            val damage: Int,
            val attack: String,
            val initiative: Int,
            val weaks: List<String>,
            val immunities: List<String>,
            val initEffect: Int = num * damage  // todo for debugging
    ) : Comparable<Group> {
        override fun compareTo(other: Group): Int {
            val powerComp = other.effective().compareTo(effective())
            if (powerComp == 0) {
                return other.initiative.compareTo(initiative)
            }
            return powerComp;
        }

        fun effective() = num * damage
        fun alive() = num > 0

        fun isA(t: Team) = t == team

        fun hit(other: Group?) {
            if (other != null) {
                other.hitMe(this)
            }
        }

        fun hitMe(other: Group) {
            val hit = other.willDamage(this)
            val numToDie = hit / hitPoints
            num -= Math.min(numToDie, num)
        }

        fun chooseOpponent(opponents: List<Group>): Group? {
            val potential = opponents.map { it to willDamage(it) }.maxBy { it.second }
            if ((potential?.second ?: 0) > 0) {
                return potential!!.first
            }
            return null
        }

        fun willDamage(other: Group): Int {
            if (other.immunities.contains(attack)) {
                return 0
            }
            if (other.weaks.contains(attack)) {
                return effective() * 2
            }
            return effective()
        }

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