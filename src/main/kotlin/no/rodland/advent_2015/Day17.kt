package no.rodland.advent_2015

@Suppress("UNUSED_PARAMETER")
object Day17 {

    fun partOne(list: List<Int>, liters: Int): Int {
        return combinations(list).count { it.sum() == liters }
    }

    fun partTwo(list: List<Int>, liters: Int): Int {
        return combinations(list).filter { it.sum() == liters }.groupBy { it.size }.minByOrNull { it.key }!!.value.size
    }

    // https://stackoverflow.com/a/59877740/13131627
    private fun <T> combinations(list: List<T>): List<List<T>> {
        val retList = mutableListOf<List<T>>()
        val count = (1 shl list.size)
        (0 until count).forEach { i ->
            val working = mutableListOf<T>()
            (list.indices).forEach { j ->
                if (i and (1 shl j) > 0) {
                    working.add(list[j])
                }
            }
            retList.add(working)
        }
        return retList
    }
}
