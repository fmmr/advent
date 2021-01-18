package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day19 {

    // The Josephus Problem - Numberphile https://www.youtube.com/watch?v=uCsD3ZGzMgE

    fun partOne(numElves: Int): Int {
        return (generateSequence(1) { i -> 2 * i }.first { it > numElves } / 2).let { (numElves - it) * 2 + 1 }
    }

    // 1508979 too low

    fun partTwo(numElves: Int): Int {
        return 2
    }

    private fun naiveGamePart2(numElves: Int): Int {
        val game = IntArray(numElves) { 1 }

        var i = 0
        var tmp: Int = 0
        while (game.count { it > 0 } > 1) {

            // find player
            while (tmp == 0) {
                tmp = game[i++ % numElves]
            }
            val playerIdx = i - 1

            tmp = 0
            // find victim
            while (tmp == 0) {
                tmp = game[i++ % numElves]
            }
            val victimIdx = i - 1

            game[playerIdx % numElves] += game[victimIdx % numElves]
            game[victimIdx % numElves] = 0
            tmp = 0
        }

        return game.indexOfFirst { it > 0 } + 1
    }

    private fun naiveGamePart1(numElves: Int): Int {
        val game = IntArray(numElves) { 1 }

        var i = 0
        var tmp: Int = 0
        while (game.count { it > 0 } > 1) {

            // find player
            while (tmp == 0) {
                tmp = game[i++ % numElves]
            }
            val playerIdx = i - 1

            tmp = 0
            // find victim
            while (tmp == 0) {
                tmp = game[i++ % numElves]
            }
            val victimIdx = i - 1

            game[playerIdx % numElves] += game[victimIdx % numElves]
            game[victimIdx % numElves] = 0
            tmp = 0
        }

        return game.indexOfFirst { it > 0 } + 1
    }

}
