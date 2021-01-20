package no.rodland.advent_2016

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

    fun partTwo(list: List<String>): Int {
        return 2
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
    }

}
