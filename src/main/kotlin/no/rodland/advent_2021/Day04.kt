package no.rodland.advent_2021

import println

@Suppress("UNUSED_PARAMETER")
object Day04 {
    fun partOne(list: List<String>): Int {
        val (numbers, boards) = parseInput(list)
        val picked = mutableSetOf<Int>()
        val (board, lastNumber) = numbers
            .asSequence()
            .runningFold(emptyList<Pair<Board, Int>>()) { acc, num ->
                picked.add(num)
                boards.filter { it.isBingo(picked) }.map { it to num }
            }
            .first { it.size == 1 }
            .first()
        return board.sumUnmarked(picked) * lastNumber
    }


    fun partTwo(list: List<String>): Int {
        val (numbers, boards) = parseInput(list)
        val picked = mutableSetOf<Int>()
        val (board, lastNumber) = numbers
            .asSequence()
            .runningFold(emptyList<Pair<Board, Int>>()) { acc, num ->
                picked.add(num)
                boards.filter { it.isBingo(picked) }.map { it to num }
            }
            .zipWithNext()
            .filter { it.first.size != it.second.size }
            .map { (prev, next) ->
                next.filter { it.first !in prev.map { prevBoard -> prevBoard.first } }
            }
//            .onEach { it.println() }
            .last()
            .first()
        val played = numbers.takeWhile { it != lastNumber } + lastNumber
        println()
        return board.sumUnmarked(played.toSet()) * lastNumber
    }

    private data class Board(val board: List<List<Int>>) {
        val columns = board[0].indices.map { idx -> board.map { it[idx] } }

        fun sumUnmarked(numbers: Set<Int>): Int {
            return board.flatten().filterNot { it in numbers }.sum()
        }

        fun isBingo(numbers: Set<Int>): Boolean {
            return listOf(board, columns).any { b ->
                b.any { r -> r.all { n -> n in numbers } }
            }
        }

        override fun toString(): String {
            return board[0].joinToString(" ")
        }

    }

    private fun parseInput(list: List<String>): Pair<List<Int>, List<Board>> {
        val numbers = list.take(2).first().split(",").map { it.trim().toInt() }
        val boards = list
            .drop(2)
            .filter { it.isNotEmpty() }
            .chunked(5)
            .map { l ->
                l.map { line -> line.split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() } }
            }
            .map { Board(it) }
        return Pair(numbers, boards)
    }
}
