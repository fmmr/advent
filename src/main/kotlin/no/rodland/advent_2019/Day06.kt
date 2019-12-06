package no.rodland.advent_2019

object Day06 {
    val com = Node("COM", null)

    fun partOne(list: List<String>): Int {
        val tree = buildTree(list)
        return tree.values.map { stepsToCom(it) }.sum()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun buildTree(list: List<String>): MutableMap<String, Node> {
        val tree = mutableMapOf<String, Node>()
        tree["COM"] = com
        list.forEach {
            val obj = it.split(")")
            val childName = obj[1]
            val parentName = obj[0]
            val parent = tree[parentName]
            val child = tree[childName]

            if (parent == null && child == null) {
                val tmpParent = Node(parentName, null)
                val tmpCild = Node(childName, tmpParent)
                //                tmpParent.add(tmpCild)
                tree.put(parentName, tmpParent)
                tree.put(childName, tmpCild)
            } else if (parent == null && child != null) {
                val tmpParent = Node(parentName, null)
                child.parent = tmpParent
                //                tmpParent.add(child)
                tree[parentName] = tmpParent
            } else if (parent != null && child == null) {
                val tmpCild = Node(childName, parent)
                tree[childName] = tmpCild
            } else {
                child!!.parent = parent
            }
        }
        return tree
    }


    fun stepsToCom(node: Node): Int {
        return stepsToDestination(node, com)
    }

    fun stepsToDestination(node: Node, destination: Node): Int {
        if (node == destination) {
            return 0
        }
        val parent = node.parent
        return 1 + stepsToDestination(parent!!, destination)
    }

    data class Node(val name: String, var parent: Node?) {
//        val children = mutableListOf<Node>()
//        fun add(node: Node) = children.add(node)
    }

}
