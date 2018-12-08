object Day8 {
    fun partOne(coordinates: List<Int>): Int {
        val node: Node = createSubTree(coordinates.toMutableList())
        val allNodes = flatMapNodes(listOf(node))
        return allNodes.map { it.metaSum }.sum()
    }

    fun partTwo(coordinates: List<Pair<Int, Int>>, limit: Int): Int {
        return 2
    }

    fun flatMapNodes(nodes: List<Node>): List<Node> {
        return listOf(nodes, nodes.flatMap { flatMapNodes(it.subNodes) }).flatten()
    }

    fun createSubTree(list: MutableList<Int>): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        val subNodes = (1..numNodes).map { createSubTree(list) }
        val meta = (1..numMeta).map { list.removeAt(0) }.sum()
        return Node(meta, subNodes)
    }

    data class Node(val metaSum: Int, val subNodes: List<Node>)
}