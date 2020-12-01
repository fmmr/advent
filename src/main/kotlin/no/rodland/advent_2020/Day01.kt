package no.rodland.advent_2020

object Day01 {
    fun partOne(expences: List<Int>): Int {
        val number = expences.first { exp ->
            expences.contains(2020 - exp)
        }
        return (2020 - number) * number
    }

    fun partTwo(expences: List<Int>): Int {
        val number = expences.first { exp ->
            expences.contains(2020 - exp)
        }
        return (2020 - number) * number
    }

//    fun findFuel2FromChriswk(weight: Int): Int {
//        return generateSequence(partOne(weight)) { w -> partOne(w) }.takeWhile { it > 0 }.sum()
//    }
}
