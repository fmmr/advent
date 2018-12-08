object Day8 {
    fun partOne(coordinates: List<Int>): Int {
        val node: Node = createSubTree(coordinates.toMutableList())
        val allNodes = flatMapNodes(listOf(node))
        return allNodes.map { it.metaSum }.sum()
    }

    fun partTwo(coordinates: List<Int>): Int {
        return createSubTreeTwo(coordinates.toMutableList()).metaSum
    }

    private fun createSubTreeTwo(list: MutableList<Int>): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        val subNodes = (1..numNodes).map { createSubTreeTwo(list) }
        val meta = if (subNodes.isNotEmpty()) {
            (1..numMeta)
                    .map { list.removeAt(0) }
                    .map {
                        if ((it - 1) < subNodes.size) {
                            subNodes[it - 1].metaSum
                        } else {
                            0
                        }
                    }
                    .sum()
        } else {
            (1..numMeta).map { list.removeAt(0) }.sum()
        }
        return Node(meta, subNodes)
    }


    private fun flatMapNodes(nodes: List<Node>): List<Node> {
        return listOf(nodes, nodes.flatMap { flatMapNodes(it.subNodes) }).flatten()
    }

    private fun createSubTree(list: MutableList<Int>): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        val subNodes = (1..numNodes).map { createSubTree(list) }
        val meta = (1..numMeta).map { list.removeAt(0) }.sum()
        return Node(meta, subNodes)
    }

    data class Node(val metaSum: Int, val subNodes: List<Node>)
}