package no.rodland.advent_2017

@Suppress("UNUSED_PARAMETER")
object Day14 {
    fun partOne(hashes: Map<Int, String>): Int {
        return hashes.map { (_, row) -> row.count { it == '1' } }.sum()
    }

    fun partTwo(hashes: Map<Int, String>): Int {
        return 2
    }

    @Suppress("unused")
    private fun printHashes() {
        listOf("ffayrhll", "flqrgnkx").forEach { str ->
            println(str)
            val hashes = (0..127).map { i ->
                val hash = toKnotHash("$str-$i").toBinary()
                println("$i to \"$hash\",")
                hash
            }
            println()
            println()
        }
    }

    private fun toKnotHash(s: String): String {
        return Day10.knotHash(s)
    }

    private fun String.toBinary(): String {
        return map { it.toString() }.map { it.toInt(16) }.map { it.toString(2) }.map { it.padStart(4, '0') }.joinToString("")

    }

}

