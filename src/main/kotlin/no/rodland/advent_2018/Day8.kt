object Day8 {
    fun partOne(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList())
                .flatten()
                .sumBy { it.metaSum }
    }

    fun partTwo(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()).sumFromChildrenMeta
    }

    private fun createSubTree(list: MutableList<Int>): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        return Node((1..numNodes).map { createSubTree(list) }, (1..numMeta).map { list.removeAt(0) })
    }

    private fun flatMapNodes(nodes: List<Node>): List<Node> {
        return listOf(nodes, nodes.flatMap { flatMapNodes(it.subNodes) }).flatten()
    }

    data class Node(val subNodes: List<Node>, val meta: List<Int>) {
        fun flatten(): List<Node> {
            return flatMapNodes(listOf(this))
        }

        val metaSum = meta.sum()
        val sumFromChildrenMeta: Int =
                if (subNodes.isEmpty()) {
                    metaSum
                } else {
                    meta.sumBy { subNodes.getOrNull(it - 1)?.sumFromChildrenMeta ?: 0 }
                }
    }
}