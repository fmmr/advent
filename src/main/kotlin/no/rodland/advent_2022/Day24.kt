package no.rodland.advent_2022

import no.rodland.advent.Pos

// template generated: 23/12/2022
// Fredrik Rødland 2022
// https://todd.ginsberg.com/post/advent-of-code/2022/day24/

@Suppress("unused", "DuplicatedCode")
class Day24(val input: List<String>) {

    private val initialMapState: MapState = MapState.of(input)
    private val start: Pos = Pos(input.first().indexOfFirst { it == '.' }, 0)
    private val goal: Pos = Pos(input.last().indexOfFirst { it == '.' }, input.lastIndex)


    fun partOne(): Int {
        return solve().first
    }

    fun partTwo(): Int {
        val  first = solve()
        val second = solve(goal, start, first.second, first.first)
        val third = solve(start, goal, second.second, second.first)
        return third.first
    }

    private fun solve(
        startPlace: Pos = start,
        stopPlace: Pos = goal,
        startState: MapState = initialMapState,
        stepsSoFar: Int = 0
    ): Pair<Int, MapState> {
        val mapStates = mutableMapOf(stepsSoFar to startState)
        val queue = mutableListOf(PathAttempt(stepsSoFar, startPlace))
        val seen = mutableSetOf<PathAttempt>()

        while (queue.isNotEmpty()) {
            val thisAttempt = queue.removeFirst()
            if (thisAttempt !in seen) {
                seen += thisAttempt
                val nextMapState = mapStates.computeIfAbsent(thisAttempt.steps + 1) { key ->
                    mapStates.getValue(key - 1).nextState()
                }

                // Can we just stand still here?
                if (nextMapState.isOpen(thisAttempt.location)) queue.add(thisAttempt.next())

                val neighbors = thisAttempt.location.neighbourCellsUDLR()

                // Is one of our neighbors the goal?
                if (stopPlace in neighbors) return Pair(thisAttempt.steps + 1, nextMapState)

                // Add neighbors that will be open to move to on the next turn.
                neighbors
                    .filter { it == start || (nextMapState.inBounds(it) && nextMapState.isOpen(it)) }
                    .forEach { neighbor ->
                        queue.add(thisAttempt.next(neighbor))
                    }
            }
        }
        throw IllegalStateException("No path to goal")
    }
    private data class PathAttempt(val steps: Int, val location: Pos) {
        fun next(place: Pos = location): PathAttempt =
            PathAttempt(steps + 1, place)
    }

    private data class Blizzard(val location: Pos, val offset: Pos) {
        fun next(boundary: Pos): Blizzard {
            var nextLocation = location + offset
            when {
                nextLocation.x == 0 -> nextLocation = Pos(boundary.x, location.y)
                nextLocation.x > boundary.x -> nextLocation = Pos(1, location.y)
                nextLocation.y == 0 -> nextLocation = Pos(location.x, boundary.y)
                nextLocation.y > boundary.y -> nextLocation = Pos(location.x, 1)
            }
            return copy(location = nextLocation)
        }
    }

    private data class MapState(val boundary: Pos, val blizzards: Set<Blizzard>) {
        private val unsafeSpots = blizzards.map { it.location }.toSet()
        fun isOpen(place: Pos): Boolean =
            place !in unsafeSpots

        fun inBounds(place: Pos): Boolean =
            place.x > 0 && place.y > 0 && place.x <= boundary.x && place.y <= boundary.y

        fun nextState(): MapState =
            copy(blizzards = blizzards.map { it.next(boundary) }.toSet())

        companion object {
            fun of(input: List<String>): MapState =
                MapState(
                    Pos(input.first().lastIndex - 1, input.lastIndex - 1),
                    input.flatMapIndexed { y, row ->
                        row.mapIndexedNotNull { x, char ->
                            when (char) {
                                '>' -> Blizzard(Pos(x, y), Pos(1, 0))
                                '<' -> Blizzard(Pos(x, y), Pos(-1, 0))
                                'v' -> Blizzard(Pos(x, y), Pos(0, 1))
                                '^' -> Blizzard(Pos(x, y), Pos(0, -1))
                                else -> null
                            }
                        }
                    }.toSet()
                )
        }
    }



}
