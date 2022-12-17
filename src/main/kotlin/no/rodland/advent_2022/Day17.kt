package no.rodland.advent_2022

import isEven
import kotlin.math.max

// template generated: 28/11/2022
// Fredrik Rødland 2022

// copied from https://github.com/forketyfork/aoc-2022/blob/main/src/main/kotlin/year2022/Day17.kt

object Day17 {
    fun partOne(input: String): Long {
        return solution(input, 2022)
    }

    fun partTwo(input: String): Long {
        return solution(input, 1000000000000)
    }

    private data class State(val str: String)

    private fun solution(input: String, stepUntil: Long): Long {
        val bucket = MutableList(1) { 0b1111111 } // floor
        var bucketSize = bucket.size
        var directionIdx = 0
        var rockIdx = 0
        var skippedHeight: Long = 0

        val visited = mutableMapOf<State, Pair<Long, Long>>()

        var currentStep = 0L

        var lookingForLoop = true

        while (currentStep < stepUntil) {
            var rock = rocks[rockIdx].toList()
            repeat(3 + rock.size) { bucket.add(0) }
            var figureRowStart = bucket.size - rock.size

            // moving the figure down and sideways until it lands
            while (true) {
                when (input[directionIdx]) {
                    '<' -> if (rock.canShiftLeft() && !rock.shiftLeft().overlaps(bucket.subList(figureRowStart, figureRowStart + rock.size))) {
                        rock = rock.shiftLeft()
                    }

                    '>' -> if (rock.canShiftRight() && !rock.shiftRight().overlaps(bucket.subList(figureRowStart, figureRowStart + rock.size))) {
                        rock = rock.shiftRight()
                    }

                    else -> error("Unknown direction")
                }
                directionIdx = directionIdx.nextIndexOf(input)
                figureRowStart--
                if (rock.overlaps(bucket.subList(figureRowStart, figureRowStart + rock.size))) {
                    figureRowStart++
                    break
                }
            }

            // painting the figure into the bucket
            rock.forEachIndexed { idx, row ->
                bucket[figureRowStart + idx] = bucket[figureRowStart + idx] or row
            }

            // removing the excessive height from the bucket
            val lowestRow = bfsLowestRow(bucket, MutableList(bucket.size) { 0 }, 1, bucket.lastIndex)
            repeat(lowestRow - 1) {
                bucket.removeAt(1)
            }
            skippedHeight += lowestRow - 1

            // removing empty rows from the bucket
            while (bucket.last() == 0) {
                bucket.removeLast()
            }

            bucketSize = max(bucket.size, bucketSize)

            // check if we've seen this place before...
            if (lookingForLoop) {
                val state = State(bucket.joinToString("_") + "->" + directionIdx + ":" + rockIdx)
                visited[state]?.let { (loopStartingStep, loopStartingHeight) ->
                    val loopSize = currentStep - loopStartingStep
                    val loopCount = ((stepUntil - currentStep) / loopSize) - 1
                    val currentTowerHeight = skippedHeight + bucket.size - 1


                    println("FOUND: step: $loopStartingStep, height: $loopStartingHeight")
                    println("FOUND: $state")
                    println(
                        "currentStep: $currentStep, " +
                            "loopSize: $loopSize, " +
                            "loopCount: $loopCount, " +
                            "currentTowerHeight: $currentTowerHeight, " +
                            "skippedHeight: $skippedHeight, " +
                            "max bucket size: $bucketSize"
                    )

                    skippedHeight += (currentTowerHeight - loopStartingHeight) * loopCount
                    currentStep += loopCount * loopSize // skip all loops
                    lookingForLoop = false // just let the regular algorithm finish the job for the rest of the steps

                    println("END: currentStep: $currentStep, skippedHeight: $skippedHeight, ")


                }
                visited[state] = currentStep to skippedHeight + bucket.size - 1L
            }

            rockIdx = rockIdx.nextIndexOf(rocks)
            currentStep++
        }
        return skippedHeight + bucket.size - 1
    }

    private fun bfsLowestRow(bucket: List<Int>, visited: MutableList<Int>, x: Int, y: Int): Int {
        if (x !in (1..0b1000000) || y < 1 || (visited[y] and x != 0)) {
            return Int.MAX_VALUE
        }
        visited[y] = visited[y] or x
        return if (bucket[y] and x != 0) {
            Int.MAX_VALUE
        } else {
            minOf(
                y,
                bfsLowestRow(bucket, visited, x shl 1, y),
                bfsLowestRow(bucket, visited, x shr 1, y),
                bfsLowestRow(bucket, visited, x, y - 1)
            )
        }
    }

    private fun Int.nextIndexOf(list: List<Any>): Int = (this + 1) % list.size
    private fun Int.nextIndexOf(str: String): Int = (this + 1) % str.length

    private fun List<Int>.shiftLeft() = map { it shl 1 }
    private fun List<Int>.shiftRight() = map { it shr 1 }
    private fun List<Int>.canShiftLeft() = all { it < 0b1000000 }
    private fun List<Int>.canShiftRight() = all(Int::isEven)
    private fun List<Int>.overlaps(other: List<Int>) = zip(other).any { (first, second) -> first and second != 0 }

    private val rocks = listOf(
        listOf(
            0b0011110
        ),
        listOf(
            0b0001000,
            0b0011100,
            0b0001000
        ),
        listOf(
            0b0011100,
            0b0000100,
            0b0000100,
        ),
        listOf(
            0b0010000,
            0b0010000,
            0b0010000,
            0b0010000
        ),
        listOf(
            0b0011000,
            0b0011000
        )
    )
}

