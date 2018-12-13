package no.rodland.advent_2018

object Day13 {
    fun partOne(list: List<String>): Pair<Int, Int> {
        val (trains, track: List<MutableList<Char>>) = init(list)
        while (!trains.crashed()) {
            trains.forEach {
                if (!trains.crashed()) {
                    it.move(track)
                }
            }
        }
        println("CRASHED!!! ${trains.getCrash()}")
        return trains.getCrash()

    }

    fun partTwo(list: List<String>): Pair<Int, Int> {
        val (trains, track: List<MutableList<Char>>) = init(list)

        while (trains.size > 1) {
            trains.sorted().forEach { train ->
                if (trains.crashed()) {
                    val crash = trains.getCrash()
                    trains.removeIf { crash.first == it.x && crash.second == it.y }
                    println("CRASHED!!! $crash  ${trains.size}")
                }
                train.move(track)
            }
        }
        val train = trains.first()
        println("lasttrain: $train")
        return train.x to train.y
    }

    private fun init(list: List<String>): Pair<MutableSet<Train>, List<MutableList<Char>>> {
        val trains = sortedSetOf<Train>()
        val track: List<MutableList<Char>> = list.mapIndexed { y, line ->
            trains.addAll(line
                    .mapIndexed { x, c -> c to (x to y) }
                    .filter { it.first.isTrain() }
                    .map { Train("${it.second.first}_$y", it.second.first, it.second.second, direction(it.first)) })
            line.map { plainTrack(it) }.toMutableList()
        }
        return Pair(trains, track)
    }


    private fun plainTrack(it: Char): Char {
        return when (it) {
            in "<>" -> '-'
            in "v^" -> '|'
            else -> it
        }
    }

    data class Train(val name: String = "tmp", var x: Int, var y: Int, var direction: Direction, var turn: Turn = Turn.LEFT) : Comparable<Train> {
        override fun compareTo(other: Train): Int {
            val yComp = y.compareTo(other.y)
            return if (yComp == 0) {
                x.compareTo(other.x)
            } else yComp
        }

        fun move(track: List<MutableList<Char>>): Train {
            val next: Train = getNext(track[y][x])
            assignValuesFrom(next)
            return this
        }

        private fun getNext(currentTrack: Char): Train {
            return when (currentTrack) {
                '+' -> {
                    val xy = turn.xyAfterTurn(direction)
                    copy(x = x + xy.first, y = y + xy.second, turn = turn.next(), direction = turn.directionAfterTurn(direction))
                }
                '|' -> when (direction) {
                    Direction.UP -> copy(y = y - 1)
                    Direction.DOWN -> copy(y = y + 1)
                    else -> error("not a valid direction: $direction")
                }
                '-' -> when (direction) {
                    Direction.LEFT -> copy(x = x - 1)
                    Direction.RIGHT -> copy(x = x + 1)
                    else -> error("not a valid direction: $direction")
                }
                '/' -> when (direction) {
                    Direction.UP -> copy(x = x + 1, direction = Direction.RIGHT)
                    Direction.DOWN -> copy(x = x - 1, direction = Direction.LEFT)
                    Direction.LEFT -> copy(y = y + 1, direction = Direction.DOWN)
                    Direction.RIGHT -> copy(y = y - 1, direction = Direction.UP)
                }
                '\\' -> when (direction) {
                    Direction.UP -> copy(x = x - 1, direction = Direction.LEFT)
                    Direction.DOWN -> copy(x = x + 1, direction = Direction.RIGHT)
                    Direction.LEFT -> copy(y = y - 1, direction = Direction.UP)
                    Direction.RIGHT -> copy(y = y + 1, direction = Direction.DOWN)
                }
                else -> error("not a valid trackpart: $currentTrack")
            }
        }

        private fun assignValuesFrom(next: Train) {
            x = next.x
            y = next.y
            turn = next.turn
            direction = next.direction
        }

        fun samePos(other: Train): Boolean {
            return x == other.x && y == other.y
        }
    }

    enum class Turn {
        LEFT, RIGHT, STRAIGHT;

        fun directionAfterTurn(previousDir: Direction): Direction {
            return when (this) {
                LEFT -> when (previousDir) {
                    Direction.UP -> Direction.LEFT
                    Direction.DOWN -> Direction.RIGHT
                    Direction.LEFT -> Direction.DOWN
                    Direction.RIGHT -> Direction.UP
                }

                RIGHT -> when (previousDir) {
                    Direction.UP -> Direction.RIGHT
                    Direction.DOWN -> Direction.LEFT
                    Direction.LEFT -> Direction.UP
                    Direction.RIGHT -> Direction.DOWN
                }
                STRAIGHT -> previousDir
            }
        }

        fun xyAfterTurn(previousDir: Direction): Pair<Int, Int> {
            return when (this) {
                LEFT -> when (previousDir) {
                    Direction.UP -> -1 to 0
                    Direction.DOWN -> 1 to 0
                    Direction.LEFT -> 0 to 1
                    Direction.RIGHT -> 0 to -1
                }

                RIGHT -> when (previousDir) {
                    Direction.UP -> 1 to 0
                    Direction.DOWN -> -1 to 0
                    Direction.LEFT -> 0 to -1
                    Direction.RIGHT -> 0 to 1
                }
                STRAIGHT -> when (previousDir) {
                    Direction.UP -> 0 to -1
                    Direction.DOWN -> 0 to 1
                    Direction.LEFT -> -1 to 0
                    Direction.RIGHT -> 1 to 0
                }
            }
        }

        fun next(): Turn {
            return when (this) {
                LEFT -> STRAIGHT
                RIGHT -> LEFT
                STRAIGHT -> RIGHT
            }
        }
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }


    private fun direction(c: Char): Direction {
        return when (c) {
            '^' -> Direction.UP
            'v' -> Direction.DOWN
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> error("not a valid direction")
        }

    }

    private fun Set<Train>.crashed(): Boolean {
        return any { trainOnSamePos(it) }
    }

    private fun Set<Train>.getCrash(): Pair<Int, Int> =
            filter { trainOnSamePos(it) }.map { it.x to it.y }.first()

    private fun Set<Train>.trainOnSamePos(it1: Train) = count { it2 -> it1.samePos(it2) } > 1

    private fun Char.isTrain(): Boolean = this in "><v^"
}


