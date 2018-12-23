package no.rodland.advent_2018

import no.rodland.advent.Pos
import no.rodland.advent_2018.Day22.Gear.*
import java.util.*

object Day22 {
    fun partOne(depth: Int, target: Pos): Int {
        val map = buildMap(depth, target)
        return map
                .filterIndexed { i, _ ->
                    i <= target.y
                }
                .map { ar ->
                    ar
                            .filterIndexed { i, _ ->
                                i <= target.x
                            }
                            .map { it.modRes }
                            .sum()
                }.sum()
    }

    fun partTwo(depth: Int, target: Pos): Int {
        val map = buildMap(depth, target)
        val path = cheapestPath(target, map)

        val result = path.map {
            if (it.gear == TORCH) {
                it
            } else {
                it.copy(cost = it.cost + 7)
            }
        }.minBy { it.cost }!!
        println("count: $count")
        return result.cost
    }

    // ok - we need a representation of all positions in map and their distance to the start Pos.
    // BUT the distance is dependant on which gear we have at any Pos in time.
    // 

    private data class Combo(val pos: Pos, val gear: Gear, val cost: Int) : Comparable<Combo> {
        override fun compareTo(other: Combo): Int {
            val i = cost.compareTo(other.cost)
            if (i != 0) {
                return i
            }
            val p = pos.compareTo(other.pos)
            if (p != 0) {
                return p
            }
            return gear.compareTo(other.gear)
        }
    }

    var count = 0

    // from https://todd.ginsberg.com/post/advent-of-code/2018/day22/
    private fun cheapestPath(to: Pos, map: List<Array<Type>>): List<Combo> {
        val seenMinimumCost: MutableMap<Pair<Pos, Gear>, Int> = mutableMapOf(Pos(0, 0) to Gear.TORCH to 0, Pos(0, 0) to Gear.CLIMB to 0)
        val pathsToEvaluate = PriorityQueue<Combo>().apply {
            add(Combo(Pos(0, 0), Gear.TORCH, 0))
//            add(Combo(Pos(0, 0), Gear.CLIMB, 0))
        }
        val returnList = mutableListOf<Combo>()
        while (pathsToEvaluate.isNotEmpty()) {

            count++
            val thisPath = pathsToEvaluate.poll()

            // Found it, and holding the correct Gear
            val pos = thisPath.pos
            if (pos == to) {
                returnList.add(thisPath)
            }
            if (returnList.size == 2) {
                return returnList
            }

            // Candidates for our next set of decisions
            val nextSteps = mutableListOf<Combo>()

            // Move to each neighbor, holding the same Gear.
            pos.positiveNeighboor(map[0].size, map.size).forEach { neighbor ->
                // Add a Traversal for each if we can go there without changing Gears
                if (thisPath.gear in map[pos.y][pos.x].validTools()) {
                    // Can keep the Gear.
                    nextSteps += thisPath.copy(
                            pos = neighbor,
                            cost = thisPath.cost + 1
                    )
                }
            }

            // Stay where we are and switch Gears to anything we aren't using (but can)
            map[pos.y][pos.x].validTools().minus(thisPath.gear).forEach { g ->
                nextSteps += Combo(pos = thisPath.pos, gear = g, cost = thisPath.cost + 7)
            }

            // Of all possible next steps, add the ones we haven't seen, or have seen and we can now do cheaper.
            nextSteps.forEach { step ->
                val key = Pair(step.pos, step.gear)
                if (key !in seenMinimumCost || seenMinimumCost.getValue(key) > step.cost) {
                    pathsToEvaluate += step
                    seenMinimumCost[key] = step.cost
                }
            }
        }
        return listOf(Combo(Pos(-1, -1), CLIMB, -1))// No path!? Come on...
    }

    private fun calcDistances(map: List<Array<Type>>, target: Pos): Pair<Int, Int> {
        val start = Pos(0, 0)
        var Poss = listOf(Combo(start, TORCH, 0), Combo(start, CLIMB, 0))
        var count = 0

        val seen = mutableSetOf<Combo>()

        while (count < 1300) {
            Poss += getNeighbourData(Poss, map, seen)
            Poss = clean(Poss)
            count++
        }

        val targetPoss = Poss.filter { it.pos == target }
        println(targetPoss)
        val result = targetPoss.map {
            if (it.gear == TORCH) {
                it
            } else {
                it.copy(cost = it.cost + 7)
            }
        }.minBy { it.cost }!!
        return result.cost to count
    }

    private fun clean(heisan: List<Combo>): List<Combo> {
        return heisan.groupBy({ it.pos to it.gear }, { it.cost }).map {
            Combo(it.key.first, it.key.second, it.value.min()!!)
        }
    }

    private fun getNeighbourData(Poss: List<Combo>, map: List<Array<Type>>, seen: MutableSet<Combo>): List<Combo> {
        val flatMap = Poss
                .filter { it !in seen }
                .also { seen.addAll(it) }
                .flatMap { combo ->
                    val ppos = combo.pos
                    val ptype = map[ppos.y][ppos.x]
                    val pgear = combo.gear
                    val cost = combo.cost
                    ppos.positiveNeighboor()
                            .map { n -> n to map[n.y][n.x] }
                            .map { t -> t.first to ptype.next(t.second, pgear) }
                            .filter { it.second.second > 0 }
                            .map { e -> Combo(e.first, e.second.first, e.second.second + cost) }
                }
        return flatMap
    }

    fun buildMap(depth: Int, target: Pos): List<Array<Type>> {
        val list = mutableListOf<IntArray>()
        (0..(target.y * 30)).forEach { y ->
            list.add(IntArray((target.x * 50) + 1))
            (0..(target.x * 50)).forEach { x ->
                val pos = Pos(x, y)
                val geologicIndex = if (pos == Pos(0, 0) || pos == target) {
                    0
                } else if (pos.y == 0) {
                    pos.x * 16807
                } else if (pos.x == 0) {
                    pos.y * 48271
                } else {
                    list[pos.y][pos.x - 1] * list[pos.y - 1][pos.x]
                }
                list[y][x] = (geologicIndex + depth) % 20183
            }
        }

        return list.map { ar ->
            ar.map { typeFromErosionlevel(it) }.toTypedArray()
        }
    }

    private fun erosion(pos: Pos, target: Pos, depth: Int): Int {
        val geologicIndex = if (pos == Pos(0, 0) || pos == target) {
            0
        } else if (pos.y == 0) {
            pos.x * 16807
        } else if (pos.x == 0) {
            pos.y * 48271
        } else {
            erosion(pos.left(), target, depth) * erosion(pos.above(), target, depth)
        }
        return (geologicIndex + depth) % 20183
    }

    enum class Gear {
        CLIMB, TORCH, NEITHER
    }

    enum class Type(val modRes: Int) {
        ROCKY(0), WET(1), NARROW(2);

        fun next(toType: Type, gear: Gear): Pair<Gear, Int> {
            return if (this == toType) {
                // no need to include the expensive path-segment here - always cheaper to not change 
                // return listOf(gear to 1, gears().filterNot { it == gear }.first() to 8)
                gear to 1
            } else when (this) {
                ROCKY -> when (toType) {
                    ROCKY -> error("handled above")
                    WET -> when (gear) {
                        CLIMB -> CLIMB to 1
                        TORCH -> CLIMB to 8
                        NEITHER -> NEITHER to -1
                    }
                    NARROW -> when (gear) {
                        CLIMB -> TORCH to 8
                        TORCH -> TORCH to 1
                        NEITHER -> NEITHER to -1
                    }
                }
                WET -> when (toType) {
                    ROCKY -> when (gear) {
                        CLIMB -> CLIMB to 1
                        TORCH -> NEITHER to -1
                        NEITHER -> CLIMB to 8
                    }
                    WET -> error("handled above")
                    NARROW -> when (gear) {
                        CLIMB -> NEITHER to 8
                        TORCH -> NEITHER to -1
                        NEITHER -> NEITHER to 1
                    }
                }

                NARROW -> when (toType) {
                    ROCKY -> when (gear) {
                        CLIMB -> NEITHER to -1
                        TORCH -> TORCH to 1
                        NEITHER -> TORCH to 8
                    }
                    WET -> when (gear) {
                        CLIMB -> NEITHER to -1
                        TORCH -> NEITHER to 8
                        NEITHER -> NEITHER to 1
                    }
                    NARROW -> error("handled above")
                }
            }

        }

        fun validTools(): List<Gear> {
            return when (this) {
                ROCKY -> listOf(TORCH, CLIMB)
                WET -> listOf(CLIMB, NEITHER)
                NARROW -> listOf(TORCH, NEITHER)
            }
        }
    }

    fun typeFromErosionlevel(value: Int): Type = Type.values().filter { it.modRes == (value % 3) }.first()
}