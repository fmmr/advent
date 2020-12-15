package no.rodland.advent_2020

object Day15 {
    fun partOne(list: List<Int>): Int {
        val game = list.toMutableList()
        val gameMap = mapOf<Int, List<Int>>()

        var idx = -1

        val seq = generateSequence {
            idx++
            if (idx < game.size) {
                game[idx]
            } else {
                val last = game[idx - 1]
                val used = game.subList(0, idx - 1).lastIndexOf(last)
                if (used == -1) {
                    game.add(0)
                    0
                } else {
                    game.add(idx - 1 - used)
                    idx - 1 - used

                }
            }
        }.take(2020).toList()
        println(seq)
        return seq.last()
    }

    fun partTwo(list: List<Int>): Int {
        return 2
    }
}
