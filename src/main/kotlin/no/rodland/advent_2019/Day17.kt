package no.rodland.advent_2019

import kotlinx.coroutines.runBlocking
import no.rodland.advent.Pos

object Day17 {
    fun partOne(list: List<String>): Int {
        val map = runBlocking { runAmp(list) }
        val calibration = map.filter { cell -> cell.value != '.' && cell.key.neighboorCellsReadingOrder().all { nei -> map[nei] != '.' } }
                .map { it.key.x * it.key.y }
                .sum()
        return calibration
    }

    suspend fun runAmp(program: List<String>): Map<Pos, Char> {
        val list = mutableListOf<Long>()
        IntCodeComputer().runSuspend(program, { 0 }, { list.add(it) })
        return list.map { it.toChar() }
                .joinToString("").split(10.toChar())
                .mapIndexed { y, line -> line.mapIndexed { x, char -> Pos(x, y) to char } }
                .flatten()
                .toMap()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}