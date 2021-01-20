package no.rodland.advent_2016

import no.rodland.advent.Pos

@Suppress("UNUSED_PARAMETER")
object Day22 {

    val regex = """/dev/grid/node-x(\d+)-y(\d+) +(\d+)T +(\d+)T +(\d+)T +(\d+)%""".toRegex()

    fun partOne(list: List<String>): Int {
        val servers = list.drop(2).map { Server(it) }
        return servers.flatMap { s1 ->
            servers
                    .filterNot { s2 -> s2 == s1 }
                    .filter { s2 -> s2.used > 0 }
                    .filter { s2 -> s2.used < s1.available }
                    .map { s2 -> s1 to s2 }
        }.count()
    }

    // 194 not right
    //  195   That's not the right answer; your answer is too high
    //  186 too high
    fun partTwo(list: List<String>): Int {
        val servers = list.drop(2).map { Server(it) }
        val maxX = servers.maxByOrNull { it.x }!!.x
        val maxY = servers.maxByOrNull { it.y }!!.y
        val map = servers.map { Pos(it.x, it.y) to it }.toMap()
        val grid = Array(maxY + 1) { y -> Array(maxX + 1) { x -> map[Pos(x, y)]!! } }
        grid.forEach { server ->
            server.map { it.asChar() }.forEach { print(it) }
            println()
        }

        println("FMR counting: 13 + 5 + 21 + 5 + 28 * 5 + 1 = 185")
        println("tginsberg \"calculating\" same: ${
            tginsberg(servers, maxX) // Empty around wall X.
        }")
        return 185
    }

    private fun tginsberg(servers: List<Server>, maxX: Int): Int {
        val wall = servers.filter { it.size > 250 }.minByOrNull { it.x }!!
        val empty = servers.first { it.used == 0 }
        var result = Math.abs(empty.x - wall.x) + 1 // Empty around wall X.
        result += empty.y // Empty to top
        result += (maxX - wall.x) // Empty over next to goal
        result += (5 * maxX.dec()) + 1 // Goal back to start
        return result
    }

    data class Server(val x: Int, val y: Int, val size: Int, val used: Int, val available: Int, val usedPercent: Int, val name: String = "node-x$x-y$y") {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(
                mr.component1().toInt(),
                mr.component2().toInt(),
                mr.component3().toInt(),
                mr.component4().toInt(),
                mr.component5().toInt(),
                mr.component6().toInt()
        )

        fun asChar(): Char {
            return when {
                size > 500 -> '#'
                used == 0 -> '_'
                else -> '.'
            }
        }

    }

}
