package no.rodland.advent_2019

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos
import no.rodland.advent.Pos.Companion.getMinMax

object Day15 {
    fun partOne(program: List<String>): Int {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val intCodeComputer = IntCodeComputer()
        intCodeComputer.launch(program, input, output)


        val start = Pos(0, 0)
        val map = mutableMapOf(start to 1L)
        val distances = mutableMapOf<Pos, Int>()

        val game = runBlocking {
            go(start, map, input, output)
            val minMax = getMinMax(map.keys)
            val array = (minMax.second.first..minMax.second.second).map { y ->
                (minMax.first.first..minMax.first.second).map { x ->
                    map.getOrDefault(Pos(x, y), 0L)
                }
            }

            val oxMap = map.filter { it.value == 2L }.keys.first()
            val offsetX = -minMax.first.first
            val offsetY = -minMax.second.first
            val oxygen = Pos(oxMap.x + offsetX, oxMap.y + offsetY)
            val startP = Pos(start.x + offsetX, start.y + offsetY)

            buildDistance(startP, array, distances)
            distances[oxygen]!!
        }

        return game
    }

    fun buildDistance(current: Pos, map: List<List<Long>>, distances: MutableMap<Pos, Int>, distance: Int = 0) {
        if (map[current.y][current.x] == 0L) {
            return
        }
        val last = distances[current] ?: 10000000
        if (distance < last) {
            distances[current] = distance
            Direction.values().forEach { dir ->
                buildDistance(current.getNext(dir.c), map, distances, distance + 1)
            }
        }
    }

    suspend fun go(current: Pos, map: MutableMap<Pos, Long>, input: Channel<Long>, output: Channel<Long>) {
        Direction.values().forEach { dir ->
            val next = current.getNext(dir.c)
            if (!map.containsKey(next)) {
                val block = move(input, dir, output)
                map[next] = block
                if (block != 0L) {
                    go(next, map, input, output)
                    move(input, dir.goBack(), output)
                }
            }
        }
    }

    private suspend fun move(input: Channel<Long>, dir: Direction, output: Channel<Long>): Long {
        input.send(dir.num)
        return output.receive()
    }


    enum class Direction(val c: Char, val num: Long) {
        NORTH('N', 1L), SOUTH('S', 2), WEST('W', 3), EAST('E', 4);

        fun goBack(): Direction = when (this) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            WEST -> EAST
            EAST -> WEST
        }
    }


    fun partTwo(program: List<String>): Int {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val intCodeComputer = IntCodeComputer()
        intCodeComputer.launch(program, input, output)


        val start = Pos(0, 0)
        val map = mutableMapOf(start to 1L)
        val distances = mutableMapOf<Pos, Int>()

        return runBlocking {
            go(start, map, input, output)
            val minMax = getMinMax(map.keys)
            val array = (minMax.second.first..minMax.second.second).map { y ->
                (minMax.first.first..minMax.first.second).map { x ->
                    map.getOrDefault(Pos(x, y), 0L)
                }
            }

            val oxMap = map.filter { it.value == 2L }.keys.first()
            val offsetX = -minMax.first.first
            val offsetY = -minMax.second.first
            val oxygen = Pos(oxMap.x + offsetX, oxMap.y + offsetY)
//            val startP = Pos(start.x + offsetX, start.y + offsetY)

            buildDistance(oxygen, array, distances)
            distances.values.max()!!
        }
    }
}