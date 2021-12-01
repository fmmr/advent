package no.rodland.advent_2019

object Day06 {
    val com = Node("COM", null)

    fun partOne(list: List<String>): Int {
        val tree = parseNodes(list)
        return tree.values.sumOf { stepsToCom(it) }
    }

    fun partTwo(list: List<String>): Int {
        val tree = parseNodes(list)
        val san = tree["SAN"]!!
        val you = tree["YOU"]!!
        val sanToCom = pathToCom(san)
        val youToCom = pathToCom(you)
        val intersect = sanToCom.intersect(youToCom)
        return ((sanToCom + youToCom) - intersect).size
    }

    private fun parseNodes(list: List<String>): MutableMap<String, Node> {
        val nodes = mutableMapOf<String, Node>()
        list.forEach {
            val obj = it.split(")")
            val childName = obj[1]
            val parentName = obj[0]
            val parent = nodes.getOrDefault(parentName, Node(parentName, null))
            val child = nodes.getOrDefault(childName, Node(childName, null))
            child.parent = parent
            nodes.put(parentName, parent)
            nodes.put(childName, child)
        }
        return nodes
    }

    fun pathToCom(node: Node): List<Node> {
        if (node == com) {
            return emptyList()
        }
        return pathToCom(node.parent!!) + node.parent!!
    }

    fun stepsToCom(node: Node): Int {
        return stepsToDestination(node, com)
    }

    fun stepsToDestination(node: Node, destination: Node): Int {
        if (node == destination) {
            return 0
        }
        return 1 + stepsToDestination(node.parent!!, destination)
    }

    data class Node(val name: String, var parent: Node?)
}
