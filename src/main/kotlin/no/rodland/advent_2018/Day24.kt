package no.rodland.advent_2018

import get
import getString

object Day24 {
    val re = """(\d+) units each with (\d+) hit points \(?(.*?)\)? ?with an attack that does (\d+) (.*) damage at initiative (\d+)""".toRegex()
    val reWeak = """.*weak to ([^;]*).*""".toRegex()
    val reImmune = """.*immune to ([^;]*).*""".toRegex()

    fun partOne(imuneList: List<String>, infectionList: List<String>, boost: Int = 0): Int {
        val endGame = play(imuneList, infectionList, boost)
        return endGame.first.sumBy { it.num }
    }

    fun partTwo(imuneList: List<String>, infectionList: List<String>, initialBoost: Int = 0): Int {
        return generateSequence(initialBoost) { it + 1 }.takeWhile { boost ->
            val end = play(imuneList, infectionList, boost)
            val teamWon = end.first.first().team
            val fighters = end.first.sumBy { it.num }
            println("team $teamWon won with a remaining army of $fighters for boost $boost")
            end.first.first().team != Team.IMMUNE
        }.last() + 1
    }

    private fun play(imuneList: List<String>, infectionList: List<String>, boost: Int): Pair<List<Group>, List<Group>> {
        var count = 1
        val immunes = imuneList.map { Group.of(Team.IMMUNE, count++, it, boost) }
        count = 1
        val infections = infectionList.map { Group.of(Team.INFECTION, count++, it, boost) }
        val all = (immunes + infections)
        count = 0
        while (all.filter { it.alive() }.map { it.team }.distinct().count() > 1) {
            count++
            val fighters = all.filter { it.alive() }.sortedWith(compareByDescending<Group> { it.effective() }.thenByDescending { it.initiative })
            val selected: MutableSet<Group> = mutableSetOf()


            // targetSelection
            val targetSelection = fighters.map { selector ->
                val available = fighters.filter { !it.isA(selector.team) }.filter { !selected.contains(it) }
                val opponent = selector.chooseOpponent(available)
                if (opponent != null) {
                    selected.add(opponent)
                }
                selector to opponent
            }.sortedByDescending { it.first.initiative }

            // attack
            targetSelection.forEach { it.first.hit(it.second) }
        }
        return all.partition { it.alive() }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Group(
            val team: Team,
            var N: Int,
            var num: Int,
            val hitPoints: Int,
            val damage: Int,
            val attack: String,
            val initiative: Int,
            val weaks: List<String>,
            val immunities: List<String>) {
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
            return opponents
                    .sortedWith(compareByDescending<Group> { willDamage(it) }.thenByDescending { it.effective() }.thenByDescending { it.initiative })
                    .filterNot { willDamage(it) == 0 }
//                    .filter { willKillAtLeastOneUnit(it) }
                    .firstOrNull()
        }

        // filtering for these will fail test: part1, live
        private fun willKillAtLeastOneUnit(other: Group): Boolean {
            val hit = this.willDamage(other)
            val numToDie = hit / other.hitPoints
            val newOtherNum = other.num - Math.min(numToDie, other.num)
            return newOtherNum < other.num
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
            fun of(team: Team, count: Int, str: String, boost: Int = 0): Group {
                val info = re.getString(str, 3) ?: ""
                val weakList = reWeak.getString(info)?.split(",")?.map { it.trim() } ?: emptyList()
                val immuneList = reImmune.getString(info)?.split(", ")?.map { it.trim() } ?: emptyList()
                val damage = if (team == Team.IMMUNE) {
                    boost + re.get(str, 4)
                } else {
                    re.get(str, 4)
                }
                return Group(team, count, re.get(str), re.get(str, 2), damage, re.getString(str, 5)!!, re.get(str, 6), weakList, immuneList)
            }
        }
    }

    enum class Team {
        IMMUNE, INFECTION
    }
}