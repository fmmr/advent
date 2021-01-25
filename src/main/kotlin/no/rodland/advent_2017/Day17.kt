package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day17 {
    fun partOne(steps: Int): Int {

//        val deque = ArrayDeque<Int>(listOf(0))
//        deque.add(1, 1)
//        deque.add(1, 2)

        var i = 0
        val heisan = generateSequence(ArrayDeque(listOf(0))) { deque ->
            val currentPos = deque.indexOf(i)
            val newPos = (currentPos + steps) % deque.size + 1
            i++
            deque.add(newPos, i)
            deque
        }.take(2018).last()
        return heisan[heisan.indexOf(2017) + 1]
    }

    fun partTwo(steps: Int): Int {
        return 2
    }
}
