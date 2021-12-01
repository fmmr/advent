package no.rodland.advent_2017

import get
import getString
import kotlin.math.absoluteValue

object Day7 {

    val re = """(.*) \((\d+)\)[ ->]*(.*)""".toRegex()
    fun partOne(list: List<String>): String {
        val (nodes, rootNode) = init(list)

        println("root node: $rootNode")
        println("nodes: ${nodes.size}")
        return rootNode.name
    }

    fun partTwo(list: List<String>): Int {
        val (_, rootNode) = init(list)

        return rootNode.findImbalance()
    }

    private fun init(list: List<String>): Pair<Map<String, Node>, Node> {
        val nodes = list.associate { str ->
            val name = re.getString(str)!!
            val weight = re.get(str, 2)
            val subnodesStr = re.getString(str, 3)
            name to Node(name, weight, subnodesStr)
        }

        nodes.values.forEach { node ->
            val subNodes = node.subnodesStr
                ?.split(" ", ", ", ",")
                ?.toList()?.mapNotNull { nodes[it] } ?: emptyList()

            node.subnodes.addAll(subNodes)
            subNodes.forEach {
                it.parent = node
            }
        }
        val rootNode = nodes.values.first { it.parent == null }
        return Pair(nodes, rootNode)
    }


    private data class Node(
            val name: String,
            val weight: Int,
            val subnodesStr: String?,
            val subnodes: MutableList<Node> = mutableListOf(),
            var parent: Node? = null) {

        val totalWeight: Int by lazy {
            weight + subnodes.sumOf { it.totalWeight }
        }

        val haveBalancedChildTotal: Boolean by lazy {
            subnodes.map { it.totalWeight }.distinct().count() == 1
        }

        override fun toString(): String {
            return "Node(name='$name', weight=$weight, subnodesStr=$subnodesStr, parent=$parent)"
        }

        // I ended up just finding (a few) candidates and detucted the result from having a look at the 11 ndoes 
        // I got.
        // copied this from: from https://todd.ginsberg.com/post/advent-of-code/2017/day7/
        fun findImbalance(imbalance: Int? = null): Int =
                if (imbalance != null && haveBalancedChildTotal) {
                    // We end when I have a positive imbalance and my children are balanced.
                    weight - imbalance
                } else {
                    // Find the child tree that is off.
                    val subtreesByWeight = subnodes.groupBy { it.totalWeight }

                    // Find the imbalanced child tree (they will be the lone node in the list, when grouped by weight)
                    val badTree = subtreesByWeight.minByOrNull { it.value.size }?.value?.first()
                        ?: throw IllegalStateException("Should not be balanced here.")

                    // Recurse, passing down our imbalance. If we don't know the imbalance, calculate it.
                    // Calculate the imbalance as the absolute value of the difference of all distinct weights
                    badTree.findImbalance(imbalance ?: subtreesByWeight.keys.reduce { a, b -> a - b }.absoluteValue)
                }
    }
}