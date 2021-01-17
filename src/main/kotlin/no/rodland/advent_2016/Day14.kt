package no.rodland.advent_2016

import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing

@Suppress("UNUSED_PARAMETER", "UnstableApiUsage", "DEPRECATION")
object Day14 {
    private val md5: HashFunction = Hashing.md5()
    val threeInARow = ".*?([a-f0-9])\\1\\1.*".toRegex()

    fun partOne(str: String): Int {
        val hashMap = mutableMapOf<String, String>()
        val hash: (String, Int) -> String = { string, i ->
            cachedHash(string, i, this::hashPart1, hashMap)
        }
        return findLastKey(str, hash)
    }

    fun partTwo(str: String): Int {
        val hashMap = mutableMapOf<String, String>()
        val hash: (String, Int) -> String = { string, i ->
            cachedHash(string, i, this::hashPart2, hashMap)
        }
        return findLastKey(str, hash)
    }

    private fun cachedHash(str: String, i: Int, hash: (String, Int) -> String, hashMap: MutableMap<String, String>): String = hashMap.getOrPut(str + i) { hash(str, i) }

    fun hashPart2(str: String, i: Int): String {
        return (0..2016).fold(str + i) { acc, _ -> md5(acc) }
    }

    fun hashPart1(str: String, i: Int): String {
        return md5(str + i)
    }

    private fun md5(acc: String) = md5.hashString(acc, Charsets.UTF_8).toString()

    fun findLastKey(str: String, hash: (String, Int) -> String): Int {
        var i = 0
        val take = generateSequence { i to hash(str, i++) }
                .filter { threeInARow.matches(it.second) }
                .filter { nextThousandContainsFiveInARow(it.second, str, i, hash) }
                .onEachIndexed { index, pair -> println("FOUND: ${index + 1} $pair") }
                .map { it.first }
                .take(64)
                .toList()
        println("Found ${take.count()} keys")
        return take
                .last()
    }


    // 14417 too low
    fun nextThousandContainsFiveInARow(matchedString: String, str: String, start: Int, hash: (String, Int) -> String): Boolean {
        var i = start
        val matchedChar = threeInARow.find(matchedString)!!.groupValues[1].first()
        val fiveInARow = ".*($matchedChar)\\1\\1\\1\\1.*".toRegex()
        return generateSequence { i to hash(str, i++) }.take(1000).any { fiveInARow.matches(it.second) }
    }

}
