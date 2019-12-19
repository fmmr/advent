package no.rodland.advent_2019

import no.rodland.advent.Pos

object Day18 {
    fun partOne(list: List<String>): Int {
        val setup = Setup(list)

        // find distance between all nodes (nodes = start + keys)
//        val allPos = (setup.keys + setup.entrance + setup.doors)
//        val distances = allPos
//                .flatMap { cell ->
//                    allPos.filter { it.second.char < cell.second.char }
//                            .map { otherCell -> (cell to otherCell) to distance(cell.second.pos, otherCell.second.pos, setup.cellMaps) }
//                }
//                .flatMap { listOf(it, (it.first.second to it.first.first) to it.second) }
        return 2
    }


    fun distance(current: Pos, otherCell: Pos, map: Map<Pos, Char>, inc: Int = 0): Int {
        if (map[current] == '#') {
            return 0
        }
        if (current == otherCell) {
            return inc
        }
        val neighboors = current.neighboorCellsReadingOrder()
        return inc + neighboors.map { distance(it, otherCell, map, inc + 1) }.sum()
    }


    class Setup(list: List<String>) {
        val map by lazy { list.map { it.map { it } } }
        val cells by lazy { map.mapIndexed { y, line -> line.mapIndexed { x, c -> Pos(x, y) to Cell(x, y, c) } }.flatten() }
        val cellMaps = cells.map { it.first to it.second.char }.toMap()
        val entrance by lazy { cells.filter { it.second.char == '@' }.first() }
        val keys by lazy { cells.filter { isKey(it.second.char) } }
        val doors by lazy { cells.filter { isDoor(it.second.char) } }
        val pairs by lazy { keys.map { key -> key to doors.filter { door -> key.second.char == door.second.char.toLowerCase() }.firstOrNull() } }
        val exit by lazy { pairs.filter { it.second == null }.map { it.first } }
        val walkable by lazy { cells.filter { isWalkable(it.second.char) } }

    }

    data class Cell(val pos: Pos, val char: Char) {
        constructor(x: Int, y: Int, c: Char) : this(Pos(x, y), c)

        val x = pos.x
        val y = pos.y
    }

    private fun isWalkable(char: Char) = char != '#'
    private fun isKey(char: Char) = char.toLowerCase() == char && char != '#' && char != '@' && char != '.'
    private fun isDoor(char: Char) = char.toUpperCase() == char && char != '#' && char != '@' && char != '.'

    fun partTwo(list: List<String>): Int {
        return 2
    }
}