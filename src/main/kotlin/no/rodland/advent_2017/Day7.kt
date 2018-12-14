package no.rodland.advent_2017

import get
import getString

object Day7 {

    val re = """(.*) \((\d+)\)[ ->]*(.*)""".toRegex()
    fun partOne(list: List<String>): String {
        val (nodes, noParent) = init(list)
        println("root node: $noParent")
        println("nodes: ${nodes.size}")
        return noParent.name
    }

    fun partTwo(list: List<String>): Int {
        val (nodes, noParent) = init(list)
        return 2

    }


    private fun init(list: List<String>): Pair<Map<String, Node>, Node> {
        val nodes = list
                .map { Node(it) }
                .map { it.name to it }
                .toMap()
        nodes.values.forEach { node ->
            node.subnodesStr?.forEach { subnode ->
                nodes[subnode]?.parent = node
//                node.subnodes.add(nodes[subnode]!!)
            }

        }
        val noParent = nodes.values.filter { it.parent == null }.first()
        return Pair(nodes, noParent)
    }


    private class Node(str: String) {
        val name: String
        val weight: Int
        val subnodesStr: List<String>?
        val subnodes: MutableList<Node> = mutableListOf()
        var parent: Node? = null
        var childrenWeight = 0
        var totalWeight = 0

        init {
            name = re.getString(str)!!
            weight = re.get(str, 2)
            subnodesStr = re.getString(str, 3)?.split(" ", ", ", ",")?.toList()
        }

        override fun toString(): String {
            return "Node(name='$name', weight=$weight, subnodes=$subnodesStr)"
        }
    }
}