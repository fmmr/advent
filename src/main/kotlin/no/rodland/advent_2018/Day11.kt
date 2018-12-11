import kotlin.math.max

object Day11 {
    fun partOne(serial: Int): Pair<Int, Int> {
        val grid = 3
        val max = (1..(300 - grid)).flatMap { x ->
            (1..(300 - grid)).map { y ->
                getAggregatedValues(x, y, serial, grid)
            }
        }.maxBy { it.second }
        println("call and value: $max")
        return max!!.first
    }

    fun partTwo(
            serial: Int,
            minRange: Int,
            maxRange: Int,
            stopPredicate: (current: Int, maxReached: Int) -> Boolean = { i1, i2 -> i1 < (i2 - (20 * i2 / 100)) }): Pair<Pair<Int, Int>, Int>? {
        var maxReachedValue = 1
        var stop = false
        val max = (minRange..maxRange).map { grid ->
            val max2 = (1..(301 - grid)).flatMap { x ->
                (1..(301 - grid)).map { y ->
                    if (!stop) {
                        getAggregatedValues(x, y, serial, grid) to grid
                    } else {
                        ((x to y) to -1) to grid
                    }
                }
            }.maxBy { it.first.second }
            println("grid: $grid:   $max2   maxReached: $maxReachedValue")
            if (stopPredicate(max2!!.first.second, maxReachedValue)) {
                stop = true
            }
            maxReachedValue = max((max2.first.second ?: 0), maxReachedValue)
            max2
        }.maxBy { it!!.first.second }
        println("max $max")
        return (max!!.first.first.first to max.first.first.second) to (max.second)
    }

    private fun getAggregatedValues(xX: Int, yY: Int, serial: Int, gridsize: Int): Pair<Pair<Int, Int>, Int> {
        val pair = (xX to yY) to (0..(gridsize - 1)).flatMap { x ->
            (0..(gridsize - 1)).map { y ->
                val x1 = x + xX
                getHundred(((x1 + 10) * (y + yY) + serial) * (x1 + 10)) - 5
            }
        }.sum()
        return pair
    }

    private fun getValues(xX: Int, yY: Int, serial: Int): Pair<Pair<Int, Int>, Int> {
        val pair = (xX to yY) to (0..2).flatMap { x ->
            (0..2).map { y ->
                val x1 = x + xX
                getHundred(((x1 + 10) * (y + yY) + serial) * (x1 + 10)) - 5
            }
        }.sum()
        return pair
    }

    fun getHundred(i: Int): Int {
        return (i % 1000) / 100  // thanks chriswk
    }
}