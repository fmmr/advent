package no.rodland.advent_2021

@Suppress("UNUSED_PARAMETER")
object Day10 {
    val map = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')

    fun partOne(list: List<String>): Int {
        val deque = ArrayDeque<Char>()
        return list.mapNotNull { str ->
            sequence {
                str.toCharArray().forEach { c ->
                    val close = map[c]
                    if (close == null) {
                        val last = deque.removeLast()
                        yield(c to last)
                    } else {
                        deque.add(close)
                    }
                }
            }
                .firstOrNull { it.first != it.second }
        }
            .map { it.first }
            .sumOf { toPointsPart1(it) }
    }

    fun partTwo(list: List<String>): Long {
        val hei = list.asSequence().map { str ->
            val deque = ArrayDeque<Char>()
            var corrupt = false
            sequence {
                str.toCharArray().forEach { c ->
                    val close = map[c]
                    if (close == null) {
                        val last = deque.removeLast()
                        corrupt = corrupt || c != last
                    } else {
                        deque.add(close)
                    }
                }
                if (deque.isNotEmpty() && !corrupt) {
                    yieldAll(deque.map { it }.reversed())
                }
            }
                .toList()
        }
            .filter { it.isNotEmpty() }
            //            .onEach { println(it) }
            .map { chars ->
                chars.map { it.toPointsPart2() }
            }
            .map {
                it.fold(0L) { acc, i ->
                    (acc * 5) + i
                }
            }
            .sorted().toList()
        return hei[hei.size / 2]

    }

    fun toPointsPart1(c: Char): Int = when (c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> error("illegal char")
    }

    private fun Char.toPointsPart2(): Int = when (this) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> error("illegal char")
    }
}

