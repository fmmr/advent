package no.rodland.advent_2021

import kotlin.math.round

@Suppress("UNUSED_PARAMETER")
object Day18 {
    fun partOne(list: List<String>): Int {
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    sealed class Fish {
        abstract fun right(): Fish
        abstract fun left(): Fish
        abstract fun magnitude(): Int
        abstract fun split(): Fish
        abstract fun explode(): Fish
    }

    data class FishNumber(val value: Int) : Fish() {
        override fun right(): Fish = this
        override fun left(): Fish = this
        override fun magnitude(): Int = value
        override fun explode(): Fish = TODO("implement explode")

        override fun split(): Fish = when {
            value > 9 -> FishPair(FishNumber(value.floorDiv(2)), FishNumber(round(value.toDouble() / 2.0).toInt()))
            else -> this
        }
    }

    data class FishPair(val first: Fish, val second: Fish) : Fish() {
        override fun right(): Fish = second.right()
        override fun left(): Fish = first.left()
        override fun magnitude(): Int = first.magnitude() * 3 + second.magnitude() * 2
        override fun split(): Fish = FishPair(first.split(), second.split())
        override fun explode(): Fish = TODO("implement explode")
    }
}
