package no.rodland.advent_2016

import com.google.common.hash.Hashing.md5

@Suppress("UNUSED_PARAMETER", "UnstableApiUsage", "DEPRECATION")
object Day05 {

    val md5 = md5()
    fun partOne(doorId: String): String {
        var i = 0
        return generateSequence { md5.hashString((doorId + (i++)), Charsets.UTF_8) }
                .map { it.toString() }
                .filter { it.startsWith("00000") }
                .map { it[5] }
                .take(8)
                .joinToString("")
    }

    fun partTwo(doorId: String): String {
        var i = 0
        val take = generateSequence { md5(doorId, i++) }
                .filter { it.startsWith("00000") }
                .filter { c -> c[5] in '0'..'7' }
                .map { it[5] to it[6] }
                .distinctBy { it.first }
                .take(8)
                .sortedBy { it.first }
                .map { it.second }
                .joinToString("")
        println("iterations: $i")
        return take
    }

    private fun md5(doorId: String, i: Int): String {
        return md5.hashString((doorId + (i)), Charsets.UTF_8).toString()
    }
}
