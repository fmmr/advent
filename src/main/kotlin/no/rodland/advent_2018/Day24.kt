package no.rodland.advent_2018

object Day24 {
    fun partOne(list: List<String>): Int {
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Group(val team: Team, var num: Int, val damage: Int, val attack: String, val initiative: Int, val weakness: List<String>, val immunity: List<String>) {
        val effective = num * damage
    }

    enum class Team {
        IMMUNE, INFECTION
    }


}