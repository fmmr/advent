object Day6 {
    fun partOne(coordinates: List<Pair<Int, Int>>): Int {
        val minX = coordinates.minBy { it.first }!!.first
        val maxX = coordinates.maxBy { it.first }!!.first
        val minY = coordinates.minBy { it.second }!!.second
        val maxY = coordinates.maxBy { it.second }!!.second

        val closestPos = (minX..maxX)
                .flatMap { x ->
                    (minY..maxY).map { y ->
                        (x to y) to getClosestPos(coordinates, x, y)
                    }
                }
                .filter { it.second != null } // remove points with multiple closest


        val infiniteCoordinates = getInfinites(closestPos, minX, maxX, minY, maxY)

        val result = closestPos.filter { !infiniteCoordinates.contains(it.second) }
                .map { it.second }
                .groupingBy { it?.first }
                .eachCount()
                .maxBy { it.value }?.value
        println("MIN: x: $minX, y: $minY, MAX: x: $maxX, y: $maxY")

        return result!!
    }

    fun partTwo(coordinates: List<Pair<Int, Int>>, limit: Int): Int {
        val minX = coordinates.minBy { it.first }!!.first
        val maxX = coordinates.maxBy { it.first }!!.first
        val minY = coordinates.minBy { it.second }!!.second
        val maxY = coordinates.maxBy { it.second }!!.second

        return (minX..maxX)
                .flatMap { x ->
                    (minY..maxY).map { y ->
                        (x to y) to getSumOfDistances(coordinates, x, y)
                    }
                }.filter { it.second < limit }
                .count()


    }

    private fun getSumOfDistances(coordinates: List<Pair<Int, Int>>, x: Int, y: Int): Int {
        return coordinates.map { getDistance(it, x, y) }.sum()
    }

    fun getInfinites(closestPos: List<Pair<Pair<Int, Int>, Pair<Int, Int>?>>, minX: Int, maxX: Int, minY: Int, maxY: Int): List<Pair<Int, Int>> {
        return closestPos
                .filter { isEdge(it.first, minX, maxX, minY, maxY) }
                .map { it.second }
                .distinct()
                .filterNotNull()
    }

    private fun isEdge(pos: Pair<Int, Int>, minX: Int, maxX: Int, minY: Int, maxY: Int): Boolean {
        return pos.first == minX || pos.first == maxX || pos.second == minY || pos.second == maxY
    }

    fun getClosestPos(coordinates: List<Pair<Int, Int>>, x: Int, y: Int): Pair<Int, Int>? {
        val map = getDistances(coordinates, x, y)
        val minDistance = map.minBy { it.second }!!.second
        val filtered = map.filter { it.second == minDistance }
        return if (filtered.size == 1) {
            filtered[0].first
        } else {
            null
        }

    }

    private fun getDistances(coordinates: List<Pair<Int, Int>>, x: Int, y: Int): List<Pair<Pair<Int, Int>, Int>> {
        return coordinates.map { coord ->
            coord to getDistance(coord, x, y)
        }
    }

    fun getDistance(coord: Pair<Int, Int>, x: Int, y: Int): Int {
        return Math.abs(coord.first - x) + Math.abs(coord.second - y)
    }
}