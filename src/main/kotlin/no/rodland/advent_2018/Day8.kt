object Day8 {
    fun partOne(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()) { _, i -> i }
                .flatten()
                .map { it.metaSum }
                .sum()
    }

    fun partTwo(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()) { s, i ->
            when {
                s.isEmpty() -> i
                (i - 1) < s.size -> s[i - 1].metaSum
                else -> 0
            }
        }.metaSum
    }

    private fun createSubTree(list: MutableList<Int>, metaDataMapper: (List<Node>, Int) -> Int): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        val subNodes = (1..numNodes).map { createSubTree(list, metaDataMapper) }
        val meta = (1..numMeta)
                .map { list.removeAt(0) }
                .map { metaDataMapper(subNodes, it) }
                .sum()
        return Node(meta, subNodes)
    }

    private fun flatMapNodes(nodes: List<Node>): List<Node> {
        return listOf(nodes, nodes.flatMap { flatMapNodes(it.subNodes) }).flatten()
    }

    data class Node(val metaSum: Int, val subNodes: List<Node>) {
        fun flatten(): List<Node> {
            return flatMapNodes(listOf(this))
        }
    }
}