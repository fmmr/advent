package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day17 {
    fun partOne(steps: Int): Int {
        var i = 0
        val values = generateSequence(ArrayDeque(listOf(0))) { deque ->
            val currentPos = deque.indexOf(i)
            val newPos = (currentPos + steps) % deque.size + 1
            i++
            deque.add(newPos, i)
            deque
        }.take(2018).last()
        return values[values.indexOf(2017) + 1]
    }

    fun partTwo(steps: Int): Int {
        var currentPos = 0
        return (1..50_000_000)
                .map {
                    currentPos = ((currentPos + steps) % it) + 1
                    if (currentPos == 1) {
                        it
                    } else {
                        -1
                    }
                }
                .last { it > 0 }
    }
}
