object Day8 {
    fun partOne(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()).partOne
    }

    fun partTwo(coordinates: List<Int>): Int {
        return createSubTree(coordinates.toMutableList()).partTwo
    }

    private fun createSubTree(list: MutableList<Int>): Node {
        val numNodes = list.removeAt(0)
        val numMeta = list.removeAt(0)
        return Node((1..numNodes).map { createSubTree(list) }, (1..numMeta).map { list.removeAt(0) })
    }

    data class Node(val subNodes: List<Node>, val meta: List<Int>) {
        val partTwo: Int =
                if (subNodes.isEmpty()) {
                    meta.sum()
                } else {
                    meta.sumBy { subNodes.getOrNull(it - 1)?.partTwo ?: 0 }
                }
        val partOne: Int = meta.sum() + subNodes.map { it.partOne }.sum()
    }
}