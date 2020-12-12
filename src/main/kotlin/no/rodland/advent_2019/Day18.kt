package no.rodland.advent_2019

import no.rodland.advent.Direction
import no.rodland.advent.Pos

object Day18 {
    fun partOne(list: List<String>): Int {
        val setup = Setup(list)

        val allPos = (setup.keys + setup.entrance + setup.doors)

        val allDistances = allPos.map { keyPos ->
            val distances = mutableMapOf<Pos, Int>()
            buildDistance(keyPos.first, setup.map, distances)
            keyPos to distances.filter { distance -> allPos.any { distance.key == it.first } }
                    .map { dist -> dist.key to (dist.value to setup.cellMaps[dist.key]) }
        }

        return 2
    }


    fun buildDistance(
            current: Pos,
            map: List<List<Char>>,
            distances: MutableMap<Pos, Int>,
            distance: Int = 0,
            doorsPassed: MutableList<Cell> = mutableListOf(),
            keysPassed: MutableList<Cell> = mutableListOf()) {
        if (map[current.y][current.x] == '#') {
            return
        }
        if (map[current.y][current.x].isKey()) {
            keysPassed.add(Cell(current.x, current.y, map[current.y][current.x]))
        }
        if (map[current.y][current.x].isDoor()) {
            doorsPassed.add(Cell(current.x, current.y, map[current.y][current.x]))
        }
        val last = distances[current] ?: (distance + 1)
        if (distance < last) {
            distances[current] = distance
            Direction.values().forEach { dir ->
                buildDistance(current.next(dir.c), map, distances, distance + 1, doorsPassed, keysPassed)
            }
        }
    }


    class Setup(list: List<String>) {
        val map by lazy { list.map { it.map { it } } }
        val cells by lazy { map.mapIndexed { y, line -> line.mapIndexed { x, c -> Pos(x, y) to Cell(x, y, c) } }.flatten() }
        val cellMaps = cells.map { it.first to it.second.char }.toMap()
        val entrance by lazy { cells.filter { it.second.char == '@' }.first() }
        val keys by lazy { cells.filter { it.second.char.isKey() } }
        val doors by lazy { cells.filter { it.second.char.isDoor() } }
        val pairs by lazy { keys.map { key -> key to doors.filter { door -> key.second.char == door.second.char.toLowerCase() }.firstOrNull() } }
        val exit by lazy { pairs.filter { it.second == null }.map { it.first } }
        val walkable by lazy { cells.filter { it.second.char.isWalkable() } }

    }

    data class Cell(val pos: Pos, val char: Char) {
        constructor(x: Int, y: Int, c: Char) : this(Pos(x, y), c)

        val x = pos.x
        val y = pos.y
    }

    private fun Char.isWalkable() = this != '#'
    private fun Char.isKey() = toLowerCase() == this && this != '#' && this != '@' && this != '.'
    private fun Char.isDoor() = toUpperCase() == this && this != '#' && this != '@' && this != '.'

    fun partTwo(list: List<String>): Int {
        return 2
    }
}