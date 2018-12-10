object Day10 {
    val re = """position=<([\d- ]+),([\d- ]+)> velocity=<([\d- ]+),([\d- ]+)>""".toRegex()
    const val NOT_SET = ".."
    const val SET = "[]"

    fun partOne(p: List<String>, height: Int): Int {
        val points = p.map { Point(it.trim()) }
        var found = false
        var count = 0

        // FIND
        while (!found) {
            count++
            val newPos = points.map { it.move() }
            val min = newPos.minBy { it.y }!!.y
            val max = newPos.maxBy { it.y }!!.y
            found = max - min == height
        }

        // PRINT
        val pairs = points.map { (it.x to it.y) to SET }.toMap()
        val xs = points.map { it.x }
        val ys = points.map { it.y }

        val minX = (xs.min() ?: 0) - 3
        val maxX = ((ys.max() ?: 0)) + 3
        val minY = (ys.min() ?: 0) - 3
        val maxY = ((xs.max() ?: 0)) + 3

        (minX..maxX).forEach { y ->
            (minY..maxY).forEach { x ->
                print(pairs.get(x to y) ?: NOT_SET)
            }
            println("")
        }

        return count
    }

    private data class Point(var x: Int, var y: Int, val dx: Int, val dy: Int) {
        constructor(str: String) : this(re.getString(str).trim().toInt(), re.getString(str, 2).trim().toInt(), re.getString(str, 3).trim().toInt(), re.getString(str, 4).trim().toInt())

        fun move(): Point {
            x += dx
            y += dy
            return this
        }
    }
}

