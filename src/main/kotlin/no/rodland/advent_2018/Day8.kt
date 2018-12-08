object Day8 {
    fun partOne(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()).metaSumWithChildren
    }

    fun partTwo(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()).sumFromChildrenMeta
    }

    private fun createSubTree(list: MutableList<Int>): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        return Node((1..numNodes).map { createSubTree(list) }, (1..numMeta).map { list.removeAt(0) })
    }

    data class Node(val subNodes: List<Node>, val meta: List<Int>) {
        val sumFromChildrenMeta: Int =
                if (subNodes.isEmpty()) {
                    meta.sum()
                } else {
                    meta.sumBy { subNodes.getOrNull(it - 1)?.sumFromChildrenMeta ?: 0 }
                }
        val metaSumWithChildren: Int = meta.sum() + subNodes.map { it.metaSumWithChildren }.sum()
    }
}