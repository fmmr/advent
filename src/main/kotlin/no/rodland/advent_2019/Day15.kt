package no.rodland.advent_2019

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos
import no.rodland.advent.Pos.Companion.getMinMax

object Day15 {
    fun partOne(program: List<String>): Int {
        val input = Channel<Long>(20)
        val output = Channel<Long>(20)
        val intCodeComputer = IntCodeComputer()
        val job = intCodeComputer.launch(program, input, output)


        val start = Pos(0, 0)
        val map = mutableMapOf(start to 1L)


        val game = GlobalScope.launch {
            go(start, map, input, output)
            val minMax = getMinMax(map.keys)
            println("min max: $minMax")

        }
        println("1")

        runBlocking {
            println("2")
            job.join()
            println("3")
            game.join()
            println("4")
        }

        println("5")
        return map.filter { it.value == 2L }.map { it.key }.first().distanceTo(start)
    }

    suspend fun go(current: Pos, map: MutableMap<Pos, Long>, input: Channel<Long>, output: Channel<Long>) {
        Direction.values().forEach { dir ->
            val next = current.getNext(dir.c)
            if (!map.containsKey(next)) {
                val block = move(input, dir, output)
                map[next] = block
                if (block != 0L) {
                    go(next, map, input, output)
                    move(input, dir.goBack(), output, " BACK")
                }
            }
        }
    }

    private suspend fun move(input: Channel<Long>, dir: Direction, output: Channel<Long>, kind: String = " FORW"): Long {
        input.send(dir.num)
        val value = output.receive()
//        println("dir: $dir, val: $value  $kind")
        return value
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


    fun partTwo(list: List<String>): Int {
        return 2
    }
}