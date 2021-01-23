package no.rodland.advent_2017

object Day10 {
    fun partOne(input: List<Int>, range: IntRange = 0..255): Int {
        val ar = IntArray(range.last + 1) { it }
        println("starting: ${ar.contentToString()}")
        println("input   : $input")
        fix(ar, input)
        println("finished: ${ar.contentToString()}")
        return ar[0] * ar[1]
    }

    fun partTwo(input: String, range: IntRange = 0..255): String {
        return knotHash(input, range)
    }

    fun knotHash(input: String, range: IntRange = 0..255): String {
        val inputs = input.map { it.toInt() }.toMutableList()
        val ar = IntArray(range.last + 1) { it }

        inputs.addAll(listOf(17, 31, 73, 47, 23))
        var pointer = 0
        var skipSize = 0

        repeat(64) {
            val p = fix(ar, inputs, pointer, skipSize)
            pointer = p.first
            skipSize = p.second
        }
        return ar
                .toList()
                .chunked(16)
                .map { it.fold(0) { agg, n -> agg xor n } }
                .map { if (it < 16) "0${it.toString(16)}" else it.toString(16) }
                .joinToString("")
    }

    fun fix(ar: IntArray, input: List<Int>, pointer: Int = 0, skipSize: Int = 0): Pair<Int, Int> {
        var p = pointer
        var s = skipSize
        input.forEach { length ->
            getSeq(ar)
                    .drop(p)
                    .take(length)
                    .toList()
                    .reversed()
                    .forEachIndexed { idx, newNum ->
                        ar[(p + idx) % ar.size] = newNum
                    }
            p += (s + length) % ar.size
            s++
        }
        return p to s
    }

    private fun getSeq(ar: IntArray): Sequence<Int> {
        return sequence {
            while (true) {
                yieldAll(ar.toList())
            }
        }
    }
}