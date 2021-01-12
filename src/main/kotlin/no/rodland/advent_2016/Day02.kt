package no.rodland.advent_2016

import no.rodland.advent.Pos

object Day02 {

    val keypad_part1 = listOf("123", "456", "789").map { it.toCharArray() }.toTypedArray()
    val keypad_part2 = listOf("XX1XX", "X234X", "56789", "XABCX", "XXDXX").map { it.toCharArray() }.toTypedArray()

    fun partOne(list: List<String>): String {
        return getCode(list, keypad_part1)
    }

    fun partTwo(list: List<String>): String {
        return getCode(list, keypad_part2)
    }

    private fun getCode(list: List<String>, pad: Array<CharArray>): String {
        val start = pad.flatMapIndexed { y: Int, chars: CharArray -> chars.mapIndexed { x: Int, c: Char -> c to Pos(x, y) } }.first { it.first == '5' }.second
        return list
                .runningFold(start) { startLine: Pos, s: String ->
                    s.fold(startLine) { acc, c ->
                        acc.next(c).let {
                            if (pad.outOfBounds(it)) acc else it
                        }
                    }
                }
                .drop(1)
                .joinToString("") { it.toKeyPad(pad) }
    }

    private fun Array<CharArray>.outOfBounds(it: Pos) = it.x < 0 || it.y < 0 || it.x >= this[0].size || it.y >= size || this[it.y][it.x] == 'X'

    private fun Pos.toKeyPad(pad: Array<CharArray>): String = pad[y][x].toString()
}
