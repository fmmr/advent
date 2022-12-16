package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day16 {
    fun partOne(list: List<String>): Int {
        list.parse()
        return 2
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }

    data class Valve(val id: String, val rate: Int, val tunnels: List<String>)

    fun List<String>.parse(): List<Valve> {
        // Valve FB has flow rate=0; tunnels lead to valves AA, FT
        // Valve ZV has flow rate=15; tunnel leads to valve ZN
        return map {
            val id = it.substringAfter("Valve ").substringBefore(" has ")
            val rate = it.substringAfter("rate=").substringBefore(";").toInt()
            val tunnels =  it.substringAfterLast("to valve").substringAfter(" ").split(", ")
            Valve(id, rate, tunnels)
        }
    }

}
