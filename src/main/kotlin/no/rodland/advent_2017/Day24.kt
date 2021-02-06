package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day24 {
    fun partOne(list: List<String>): Int {
        val bridges = bridges(list)
        return bridges.map { l -> l.sumBy { it.strength } }.maxOrNull()!!
    }

    fun partTwo(list: List<String>): Int {
        val bridges = bridges(list)
        val size = bridges.map { it.size }.maxOrNull()!!
        val candidates = bridges.filter { it.size == size }
        return candidates.map { l -> l.sumBy { it.strength } }.maxOrNull()!!
    }

    private fun bridges(list: List<String>): List<List<Port>> {
        val start = Port(0, 0, Used.FIRST)
        val ports = list.map { it.split("/") }.map { Port(it.first().toInt(), it.last().toInt()) }.toSet()
        return build(listOf(start), ports)
    }


    private fun build(ports: List<Port> = emptyList(), available: Set<Port>): List<List<Port>> {
        val last = ports.last()
        val lastUnused = last.unused()
        val nextPorts = available.filter { it.validFor(last) }

        return if (nextPorts.isEmpty()) {
            listOf(ports)
        } else {
            nextPorts.flatMap { nextPort ->
                val portsWithNext = ports.dropLast(1) + Port(last.port1, last.port2, Used.BOTH) + Port(nextPort.port1, nextPort.port2, lastUnused)
                val newAvailable = available - nextPort
                build(portsWithNext, newAvailable)
            }
        }
    }

    data class Port(val port1: Int, val port2: Int, val used: Used = Used.NONE) {
        constructor(port1: Int, port2: Int, usedNum: Int) : this(port1, port2, if (usedNum == port1) Used.FIRST else Used.SECOND)

        val strength = port1 + port2
        fun validFor(p: Port) = validFor(p.unused())
        private fun validFor(i: Int) = port1 == i || port2 == i

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
