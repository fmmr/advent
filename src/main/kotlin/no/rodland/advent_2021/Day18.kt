package no.rodland.advent_2021

import kotlin.math.round
import kotlin.math.roundToInt

// heavily inspired by https://gitlab.com/Nohus/adventofcode2021/-/blob/master/src/main/kotlin/day18_2/Solution.kt
object Day18 {
    fun partOne(list: List<String>): Int {
        return list.map { it.parse() }.reduce { acc, fish -> acc + fish }.magnitude()
    }

    fun partTwo(list: List<String>): Int {
        return list.flatMap { l1 ->
            list.mapNotNull { l2 ->
                if (l1 != l2) {
                    (l1.parse() + l2.parse()).magnitude()
                } else {
                    null
                }
            }
        }.maxOrNull()!!
    }

    fun String.parse(): Fish {
        if (!startsWith('[')) return FishNumber(toInt())
        val inside = removeSurrounding("[", "]")
        var nestLevel = 0
        val index = inside.indexOfFirst {
            if (it == '[') nestLevel++
            else if (it == ']') nestLevel--
            else if (it == ',' && nestLevel == 0) return@indexOfFirst true
            false
        }
        return FishPair(inside.substring(0, index).parse(), inside.substring(index + 1).parse())
    }

    sealed class Fish {
        abstract fun magnitude(): Int
        abstract fun split(): Fish
        abstract fun shouldSplit(): FishNumber?
        abstract fun shouldExplode(level: Int = 0): FishPair?
        abstract fun flatten(): List<Fish>
        abstract fun replace(replacement: Pair<Fish, Fish>): Fish

        operator fun plus(fish: Fish): Fish {
            val fishPair = FishPair(this, fish)
            return fishPair.reduce()
        }

        fun reduce(): Fish {
            var reduced = this
            while (true) {
                val exploding = reduced.shouldExplode()
                if (exploding != null) {
                    val flattened = reduced.flatten()
                    val index = flattened.indexOf(exploding)
                    reduced = reduced.replace(exploding to FishNumber(0))

                    val numberToLeft = flattened.take(index).filterIsInstance<FishNumber>().lastOrNull()
                    if (numberToLeft != null) {
                        reduced = reduced.replace(numberToLeft to FishNumber(numberToLeft.value + (exploding.first as FishNumber).value))
                    }

                    val numberToRight = flattened.drop(index + 1).filterIsInstance<FishNumber>().drop(2).firstOrNull()
                    if (numberToRight != null) {
                        reduced = reduced.replace(numberToRight to FishNumber(numberToRight.value + (exploding.second as FishNumber).value))
                    }
                    continue
                }
                val splitting = reduced.shouldSplit()
                if (splitting != null) {
                    val pair = FishPair(FishNumber(splitting.value / 2), FishNumber(((splitting.value + 0.5) / 2.0).roundToInt()))
                    reduced = reduced.replace(splitting to pair)
                    continue
                }
                break
            }
            return reduced
        }

    }

    class FishNumber(val value: Int) : Fish() {
        override fun magnitude(): Int = value
        override fun split(): Fish = when {
            value > 9 -> FishPair(FishNumber(value.floorDiv(2)), FishNumber(round(value.toDouble() / 2.0).toInt()))
            else -> this
        }

        override fun shouldSplit(): FishNumber? = if (value > 9) this else null
        override fun shouldExplode(level: Int): FishPair? = null
        override fun flatten(): List<Fish> = emptyList()
        override fun replace(replacement: Pair<Fish, Fish>) = if (this === replacement.first) replacement.second else this
        override fun toString(): String {
            return "$value"
        }
    }

    class FishPair(val first: Fish, val second: Fish) : Fish() {
        override fun magnitude(): Int = first.magnitude() * 3 + second.magnitude() * 2
        override fun split(): Fish = FishPair(first.split(), second.split())
        override fun shouldSplit(): FishNumber? = first.shouldSplit() ?: second.shouldSplit()
        override fun shouldExplode(level: Int) = when (level) {
            4 -> this
            else -> first.shouldExplode(level + 1) ?: second.shouldExplode(level + 1)
        }

        override fun flatten(): List<Fish> = listOf(listOf(this.first), this.first.flatten(), listOf(this.second), this.second.flatten()).flatten()
        override fun replace(replacement: Pair<Fish, Fish>) = if (this === replacement.first) replacement.second else FishPair(first.replace(replacement), second.replace(replacement))
        override fun toString(): String {
            return "[$first,$second]"
        }

    }
}
