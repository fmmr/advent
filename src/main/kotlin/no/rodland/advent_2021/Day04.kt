package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day04 {
    fun partOne(list: List<String>): Int {
        val (numbers, boards) = parseInput(list)
        val bingo = numbers
            .asSequence()
            .flatMap { num ->
                boards
                    .asSequence()
                    .map { board ->
                        board.pick(num)
                        num to board
                    }
            }
            .filter { (_, board) -> board.isBingo() }
            .first()

        return bingo.first * bingo.second.sumUnmarked()
    }


    fun partTwo(list: List<String>): Int {
        val (numbers, boards) = parseInput(list)
        val bingo = numbers
            .asSequence()
            .runningFold(boards) { b, num ->
                boards.forEach { it.pick(num) }
                boards.filterNot { it.isBingo() }
            }
            .first { it.size == 1 }
            .first()
        // replay the game
        val lastWon = numbers
            .asSequence()
            .map { it to bingo.pick(it) }
            .first { bingo.isBingo() }
        return lastWon.first * bingo.sumUnmarked()
    }

    private class Board(val board: List<List<Int>>) {
        val picked = Array(5) {
            BooleanArray(5) { false }
        }

        fun pick(num: Int): Boolean {
            board.flatMapIndexed { x: Int, ints: List<Int> ->
                ints.mapIndexedNotNull { y: Int, i: Int ->
                    if (i == num) {
                        x to y
                    } else {
                        null
                    }
                }
            }.forEach { (x, y) ->
                picked[x][y] = true
            }
            return isBingo()
        }

        fun sumUnmarked(): Int {
            return picked.flatMapIndexed { x, a ->
                a.mapIndexed { y, b ->
                    if (b) 0 else board[x][y]
                }
            }.sum()
        }

        fun isBingo(): Boolean {
            val rowBingo = picked.any { row -> row.all { it } }
            val colBingo = picked.first().indices.any { idx -> picked.all { row -> row[idx] } }
            return rowBingo || colBingo

        }

        override fun toString(): String {
            return board.joinToString("\n")
        }
    }

    private fun parseInput(list: List<String>): Pair<List<Int>, List<Board>> {
        val numbers = list.take(2).first().split(",").map { it.trim().toInt() }
        val boards = list
            .drop(2)
            .windowed(5, 6)
            .map { l ->
                l.map { it.split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() } }
            }
            .map { Board(it) }
        return Pair(numbers, boards)
    }
}
