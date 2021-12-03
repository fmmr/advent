package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day19 {

    // The Josephus Problem - Numberphile https://www.youtube.com/watch?v=uCsD3ZGzMgE

    fun partOne(numElves: Int): Int {
        (generateSequence(1) { i -> 2 * i }.first { it > numElves } / 2).let { (numElves - it) * 2 + 1 }
        //        val solutionC = solvePart1(numElves)
        return (numElves.toString(2).replaceFirst('1', '0') + '1').toInt(2)
    }

    fun partTwo(numElves: Int): Int {
        val powThreeLessTheNumElves = generateSequence(1) { i -> 3 * i }.first { it > numElves } / 3
        val diff = numElves - powThreeLessTheNumElves
        val solutionA = if (diff == 0) {
            powThreeLessTheNumElves
        } else if (numElves < (powThreeLessTheNumElves * 2)) {
            diff
        } else {
            powThreeLessTheNumElves + ((numElves - 2 * powThreeLessTheNumElves) * 2)
        }
//        val solutionB = solvePart2(numElves)
        return solutionA
    }

    // tginsberg
    @Suppress("unused")
    fun solvePart1(numElves: Int): Int {
        val queue = ArrayDeque((1..numElves).toList())
        while (queue.size > 1) {
            queue.add(queue.removeFirst())
            queue.removeFirst()
        }
        return queue.first()
    }

    // tginsberg
    @Suppress("unused")
    fun solvePart2(numElves: Int): Int {
        val left = ArrayDeque((1..numElves / 2).toList())
        val right = ArrayDeque(((numElves / 2) + 1..numElves).toList())

        while (left.size + right.size > 1) {
            if (left.size > right.size) left.removeLast()
            else right.removeFirst()

            right.addLast(left.removeFirst())
            left.addLast(right.removeFirst())
        }

        return left.firstOrNull() ?: right.first()
    }
}