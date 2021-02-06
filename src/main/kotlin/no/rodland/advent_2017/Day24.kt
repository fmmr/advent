package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {

        val start = Port(0, 0, Used.FIRST)
        val ports = list.map { it.split("/") }.map { Port(it.first().toInt(), it.last().toInt()) }.toSet()
        val bridges = build(listOf(start), ports)
        val strongestBridge = bridges.map { l -> l.sumBy { it.strength } }
        return strongestBridge.maxOrNull()!!
    }

    fun build(ports: List<Port> = emptyList(), available: Set<Port>): List<List<Port>> {
        val last = ports.last()
        val lastUnused = last.unused()
        val nextPorts = available.filter { it.validFor(last) }

        return if (nextPorts.size == 0) {
            listOf(ports)
        } else {
            nextPorts.flatMap { nextPort ->
                val portsWithNext = ports.dropLast(1) + Port(last.port1, last.port2, Used.BOTH) + Port(nextPort.port1, nextPort.port2, lastUnused)
                val newAvailable = available - nextPort
                build(portsWithNext, newAvailable)
            }
        }
    }


    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Port(val port1: Int, val port2: Int, val used: Used = Used.NONE) {
        constructor(port1: Int, port2: Int, usedNum: Int) : this(port1, port2, if (usedNum == port1) Used.FIRST else Used.SECOND)

        val strength = port1 + port2
        fun validFor(p: Port) = validFor(p.unused())
        fun validFor(i: Int) = port1 == i || port2 == i

        fun unused() = when (used) {
            Used.FIRST -> port2
            Used.SECOND -> port1
            Used.BOTH -> -1
            else -> error("should not happen: $this $used")
        }
    }

    enum class Used {
        FIRST, SECOND, BOTH, NONE
    }

}
