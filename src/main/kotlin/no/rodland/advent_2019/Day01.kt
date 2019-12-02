package no.rodland.advent_2019

object Day01 {
    fun partOne(mass: Int) = (mass / 3) - 2

    fun partTwo(mass: Int): Int {
        return if (mass < 7) {
            0
        } else {
            val partOne = partOne(mass)
            partOne + partTwo(partOne)
        }
    }
}
