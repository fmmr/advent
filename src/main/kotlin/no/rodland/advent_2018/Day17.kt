package no.rodland.advent_2018

import no.rodland.advent.Pos


object Day17 {
    fun partOne(list: List<String>, printFinalMap: Boolean = false): Int {
        val coordinates: List<Pair<IntRange, IntRange>> = parse(list)
        val (xminmax, yminmax) = getMinMax(coordinates)
        val cave: Caves = runSim(coordinates, xminmax, yminmax, printFinalMap)
        return (yminmax.first..yminmax.second).map { y ->
            ((xminmax.first - 1)..(xminmax.second + 1)).map { x ->
                cave[Pos(x, y)].isWater()
            }.count { it }
        }.sum()
    }

    fun partTwo(list: List<String>): Int {
        val coordinates: List<Pair<IntRange, IntRange>> = parse(list)
        val (xminmax, yminmax) = getMinMax(coordinates)
        val cave: Caves = runSim(coordinates, xminmax, yminmax)
        return (yminmax.first..yminmax.second).map { y ->
            ((xminmax.first - 1)..(xminmax.second + 1)).map { x ->
                cave[Pos(x, y)].still()
            }.count { it }
        }.sum()
    }

    private fun runSim(coordinates: List<Pair<IntRange, IntRange>>, xminmax: Pair<Int, Int>, yminmax: Pair<Int, Int>, printFinalMap: Boolean = false): Caves {
        val spring = Pos(500, 0)
        val cave: Caves = map(coordinates, xminmax, yminmax)

        advanceWater(spring, cave)

        if (printFinalMap) {
            cave.mapIndexed { y, _ ->
                ((xminmax.first - 1)..(xminmax.second + 1)).map { x ->
                    print(cave[Pos(x, y)])
                }
                println()
            }
            println()
        }
        return cave
    }


    private fun Char.wallOrStill(): Boolean = this in "~#"
    private fun Char.still(): Boolean = this in "~"
    private fun Char.isWater(): Boolean = this in "+~|"
    private fun Char.isSand(): Boolean = this == '.'
    private fun Pos.hasWalls(cave: Caves): Boolean =
            this.hasWall(Pos::right, cave) && this.hasWall(Pos::left, cave)

    private fun Pos.hasWall(nextPoint: (Pos) -> Pos, cave: Caves): Boolean {
        var point = this
        while (point in cave) {
            when (cave[point]) {
                '#' -> return true
                '.' -> return false
                else -> point = nextPoint(point)
            }
        }
        return false
    }

    private fun Pos.fillLeftAndRight(cave: Caves) {
        this.fillUntilWall(Pos::right, cave)
        this.fillUntilWall(Pos::left, cave)
    }

    private fun Pos.fillUntilWall(nextPoint: (Pos) -> Pos, cave: Caves) {
        var point = this
        while (cave[point] != '#') {
            cave[point] = '~'
            point = nextPoint(point)
        }
    }

    private fun map(clay: List<Pair<IntRange, IntRange>>, xminmax: Pair<Int, Int>, yminmax: Pair<Int, Int>): Caves {
        return (0..yminmax.second + 1).map { y ->
            (0..(xminmax.second + 1)).map { x ->
                if (clay.isClay(x, y)) '#' else '.'
            }.toCharArray()
        }.toTypedArray()
                .apply { this[0][500] = '+' }
    }


    private fun advanceWater(pos: Pos, cave: Caves) {
        if (pos.below() !in cave) {
            return
        }
        if (cave[pos.below()] == '.') {
            cave[pos.below()] = '|'
            advanceWater(pos.below(), cave)
        }
        if (cave[pos.below()].wallOrStill() && pos.right() in cave && cave[pos.right()].isSand()) {
            cave[pos.right()] = '|'
            advanceWater(pos.right(), cave)
        }
        if (cave[pos.below()].wallOrStill() && pos.left() in cave && cave[pos.left()].isSand()) {
            cave[pos.left()] = '|'
            advanceWater(pos.left(), cave)
        }
        if (pos.hasWalls(cave)) {
            pos.fillLeftAndRight(cave)
        }
    }


    fun getMinMax(coordinates: List<Pair<IntRange, IntRange>>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val xmin = coordinates.map { it.first }.map { it.first }.minOrNull()!!
        val xmax = coordinates.map { it.first }.map { it.last }.maxOrNull()!!
        val ymin = coordinates.map { it.second }.map { it.first }.minOrNull()!!
        val ymax = coordinates.map { it.second }.map { it.last }.maxOrNull()!!
        return (xmin to xmax) to (ymin to ymax)
    }

    fun parse(list: List<String>): List<Pair<IntRange, IntRange>> {
        return list.map { str ->
            val part = (0..1).map { idx ->
                val (c, range) = str.split(", ")[idx].split("=")
                val rSplit = range.split("..")
                val r1 = rSplit[0].toInt()
                val r2 = if (rSplit.size == 2) rSplit[1].toInt() else r1
                c to r1..r2
            }.toMap()
            part["x"]!! to part["y"]!!
        }
    }

}

private fun List<Pair<IntRange, IntRange>>.isClay(x: Int, y: Int): Boolean {
    return this.any { it.first.contains(x) && it.second.contains(y) }
}

