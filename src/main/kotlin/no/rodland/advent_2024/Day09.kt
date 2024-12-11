package no.rodland.advent_2024

import no.rodland.advent.Day
import java.util.*

// template generated: 09/12/2024
// Fredrik Rødland 2024
// Thanks to Todd for inspiration for part 2 https://todd.ginsberg.com/post/advent-of-code/2024/day9/
class Day09(val input: List<String>) : Day<Long, Long, Pair<Int, Pair<List<Day09.Num>, List<Day09.Block>>>> {

    private val parsed = input.parse()
    private val sizePart1 = parsed.first
    private val parsedList = parsed.second.first
    private val blocks = parsed.second.second

    override fun partOne(): Long {
        val list = parsedList.toMutableList()
        val memory = (0..<sizePart1).map { idx ->
            val num = list[idx]
            if (num.isFile) {
                num
            } else {
                list.removeLastNonNull()
            }
        }
        return memory.map { it.id }.checksum()
    }

    override fun partTwo(): Long {
        val freeSpace: MutableMap<Int, PriorityQueue<Int>> = blocks
            .filter { it.id == null }
            .groupBy({ it.size }, { it.start })
            .mapValues { (_, v) -> PriorityQueue(v) }
            .toMutableMap()

        return blocks.filterNot { it.id == null }.reversed().sumOf { block ->
            block.checksum(
                freeSpace.findSpace(block)
            )
        }
    }

    private fun MutableMap<Int, PriorityQueue<Int>>.findSpace(block: Block): Int {
        return (block.size..9)
            .mapNotNull { trySize ->
                if (this[trySize]?.isNotEmpty() == true) trySize to this.getValue(trySize).first()
                else null
            }
            .sortedBy { it.second }
            .filter { it.second < block.start }
            .firstNotNullOfOrNull { (blockSize, startAt) ->
                this[blockSize]?.poll()
                if (blockSize != block.size) {
                    val diff = blockSize - block.size
                    computeIfAbsent(diff) { _ -> PriorityQueue() }.add(startAt + block.size)
                }
                startAt
            } ?: block.start
    }

    private fun MutableList<Num>.removeLastNonNull(): Num {
        var last = removeLast()
        while (last.isSpace) {
            last = removeLast()
        }
        return last
    }

    private fun Iterable<Int>.checksum(): Long = foldIndexed(0L) { i, acc, id -> acc + i * id }


    override fun List<String>.parse(): Pair<Int, Pair<List<Num>, List<Block>>> {
        var id = 0
        var spaceId = -1
        var size = 0
        val chunked = first()
            .map { c -> c.digitToInt() }
            .chunked(2) {
                val num = Num(id++, it.first())
                val space = if (it.size == 2) {
                    Num(spaceId--, it.last())
                } else {
                    Num(spaceId--, 0)
                }
                size += it.first()
                num to space
            }
        val flatMap = chunked.flatMap { (num, space) ->
            (0..<num.size).map { num } + (0..<space.size).map { space }
        }
        var start = 0
        val part2 = chunked.flatMap { (num, space) ->
            val nb = Block(start, num.size, num.id.toLong())
            val sb = Block(start + num.size, space.size, null)
            start += space.size + num.size
            listOf(nb, sb)
        }
        return size to (flatMap to part2)
    }

    data class Num(val id: Int, val size: Int) {
        val isSpace = id < 0
        val isFile = !isSpace
    }

    data class Block(val start: Int, val size: Int, val id: Long?) {
        val isSpace = id == null
        val isFile = !isSpace

        fun checksum(index: Int = start): Long =
            (0..<size).sumOf {
                (index + it) * (id ?: 0L)
            }

    }

    override val day = "09".toInt()
}
