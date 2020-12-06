package no.rodland.advent_2015

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object Day04 {
    fun partOne(code: String): Int {
        return findNumber(code, "00000")
    }

    fun partTwo(code: String): Int {
        return findNumber(code, "000000")
    }

    private fun findNumber(code: String, prefix: String): Int {
        return generateSequence((0 to md5(code + 0).toHex())) { (n, _) -> (n + 1 to md5(code + (n + 1)).toHex()) }
            .first { (_, hash) -> hash.startsWith(prefix) }
            .first
    }

    private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
    private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
}
