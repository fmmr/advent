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

    fun partTwo(serial: Int, minRange: Int, maxRange: Int): Pair<Pair<Int, Int>, Int>? {
        var lastVal = 0
        val max = (minRange..maxRange).map { grid ->
            val maxBy = (1..(300 - grid)).flatMap { x ->
                (1..(300 - grid)).map { y ->
                    getAggregatedValues(x, y, serial, grid) to grid
                }
            }.maxBy { it.first.second }
            println("grid: $grid: $maxBy")
            maxBy
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
        if (i < 100) {
            return 0
        }
        val toString = (i / 100).toString()
        return toString.get(toString.length - 1).toString().toInt()
    }
}