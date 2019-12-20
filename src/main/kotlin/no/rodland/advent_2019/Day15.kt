package no.rodland.advent_2019

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Direction
import no.rodland.advent.Pos
import no.rodland.advent.Pos.Companion.getMinMax

object Day15 {
    fun partOne(program: List<String>): Int {
        return runBlocking {
            val (minMax, map) = buildMap(program, Pos(0, 0))
            val oxygen = findOxygen(map)

            val startP = Pos(-minMax.first.first, -minMax.second.first)

            val distances = mutableMapOf<Pos, Int>()
            buildDistance(startP, map, distances)
            distances[oxygen]!!
        }
    }

    fun partTwo(program: List<String>): Int {
        return runBlocking {
            val (_, map) = buildMap(program, Pos(0, 0))

            val oxygen = findOxygen(map)

            val distances = mutableMapOf<Pos, Int>()
            buildDistance(oxygen, map, distances)
            distances.values.max()!!
        }
    }

    private fun findOxygen(map: List<List<Long>>): Pos {
        return map.mapIndexed { idx, list -> list.indexOf(2) to idx }
                .filter { it.first != -1 }
                .map { Pos(it.first, it.second) }.first()
    }

    fun buildDistance(current: Pos, map: List<List<Long>>, distances: MutableMap<Pos, Int>, distance: Int = 0) {
        if (map[current.y][current.x] == 0L) {
            return
        }
        val last = distances[current] ?: (distance + 1)
        if (distance < last) {
            distances[current] = distance
            Direction.values().forEach { dir ->
                buildDistance(current.getNext(dir.c), map, distances, distance + 1)
            }
        }
    }

    private suspend fun buildMap(program: List<String>, start: Pos): Pair<Pair<Pair<Int, Int>, Pair<Int, Int>>, List<List<Long>>> {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val intCodeComputer = IntCodeComputer()
        intCodeComputer.launch(program, input, output)
        val positions = mutableMapOf(start to 1L)
        go(start, positions, input, output)
        val minMax = getMinMax(positions.keys)
        val map = (minMax.second.first..minMax.second.second).map { y ->
            (minMax.first.first..minMax.first.second).map { x ->
                positions.getOrDefault(Pos(x, y), 0L)
            }
        }
        return Pair(minMax, map)
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


}