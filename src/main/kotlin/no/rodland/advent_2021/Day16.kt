package no.rodland.advent_2021

import no.rodland.advent_2021.Day16.Packet.Companion.getOp
import prePad


@Suppress("UNUSED_PARAMETER")
object Day16 {
    fun partOne(str: String): Long {
        val parse = str.toListOfBits().parse()
        return parse.versionSum()
    }

    fun partTwo(str: String): Long {
        val parse = str.toListOfBits().parse()
        return parse.value()
    }

    fun List<Char>.parse(): Packet {
        val (version, rest1) = takeDrop(3)
        val (type, rest2) = rest1.takeDrop(3)

        val versionId = version.toLong()
        val packet = when (val typeId = type.toLong()) {
            4L -> {
                getLiteralPacket(versionId, typeId, rest2)
            }
            else -> {
                val (length, rest3) = rest2.takeDrop(1)
                when (length.single()) {
                    '0' -> parseLengthSub(versionId, typeId, rest3)
                    else -> parseNumSub(versionId, typeId, rest3)
                }
            }
        }
        return packet
    }

    private fun parseNumSub(versionId: Long, typeId: Long, rest3: List<Char>): Operation {
        val (numberSubPackets, rest4) = rest3.takeDrop(11)
        val subPackets = (0 until numberSubPackets.toLong()).fold(emptyList<Packet>() to rest4) { acc, _ ->
            val (list, rest) = acc
            val packet = rest.parse()
            list + packet to packet.rest
        }
        return getOp(versionId, typeId, subPackets.first, subPackets.second)
    }

    private fun parseLengthSub(versionId: Long, typeId: Long, seq: List<Char>): Operation {
        val (lengthSubPackets, rest1) = seq.takeDrop(15)
        val (subPacketCharList, rest2) = rest1.takeDrop(lengthSubPackets.toLong())
        val subPackets = parseLengthSub(subPacketCharList)
        return getOp(versionId, typeId, subPackets, rest2)
    }

    private fun parseLengthSub(subPacketCharList: List<Char>): List<Packet> {
        return if (subPacketCharList.isEmpty()) {
            emptyList()
        } else {
            val packet = subPacketCharList.parse()
            listOf(packet) + parseLengthSub(packet.rest)
        }
    }

    private fun getLiteralPacket(versionId: Long, typeId: Long, seq: List<Char>): Packet {
        val (lit, rest) = getLiteral(seq)
        val value = lit.joinToString("").toLong(2)
        return Literal(versionId, typeId, value, rest)
    }

    private fun getLiteral(seq: List<Char>): Pair<List<Char>, List<Char>> {
        val (literal, rest) = seq.takeDrop(5)
        return if (literal.first() == '1') {
            val (next, rem) = getLiteral(rest)
            (literal.drop(1) + next) to rem
        } else {
            literal.drop(1) to rest
        }
    }

    private fun String.toListOfBits() = map { it.toString().toLong(16) }
        .map { it.toString(2) }
        .map { it.prePad(4, "0") }
        .flatMap { it.toList() }


    private fun List<Char>.toLong(): Long = toBits().toLong(2)

    private fun List<Char>.toBits() = toList().joinToString("")

    private fun List<Char>.takeDrop(i: Long): Pair<List<Char>, List<Char>> {
        return take(i.toInt()) to drop(i.toInt())
    }


    // Only used in tests to check parsing
    fun allLiterals(str: String): List<Long> {
        return str.toListOfBits().parse().allLiterals().map { it.value }
    }

    // Only used in tests to check parsing
    fun eval(str: String): Long {
        return str.toListOfBits().parse().value()
    }


    sealed class Packet(val version: Long, val typeId: Long, val rest: List<Char>) {
        abstract fun versionSum(): Long
        abstract fun value(): Long
        abstract fun allLiterals(): List<Literal>

        override fun toString(): String {
            return "Packet(version=$version, typeId=$typeId, rest=$rest)"
        }

        companion object {
            fun getOp(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) = when (typeId) {
                0L -> Sum(version, typeId, subPackets, rest)
                1L -> Product(version, typeId, subPackets, rest)
                2L -> Min(version, typeId, subPackets, rest)
                3L -> Max(version, typeId, subPackets, rest)
                5L -> GT(version, typeId, subPackets, rest)
                6L -> LT(version, typeId, subPackets, rest)
                7L -> EQ(version, typeId, subPackets, rest)
                else -> error("No Op for $typeId")
            }

        }
    }

    class Literal(version: Long, typeId: Long, val value: Long, rest: List<Char>) : Packet(version, typeId, rest) {
        override fun versionSum(): Long = version
        override fun value(): Long = value
        override fun allLiterals(): List<Literal> = listOf(this)
    }

    sealed class Operation(version: Long, typeId: Long, val subPackets: List<Packet>, rest: List<Char>) : Packet(version, typeId, rest) {
        override fun versionSum(): Long = version + subPackets.sumOf { it.versionSum() }
        override fun allLiterals(): List<Literal> = subPackets.flatMap { it.allLiterals() }
    }

    class Sum(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value(): Long = subPackets.sumOf { it.value() }
    }

    class Product(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value() = subPackets.fold(1L) { acc, packet -> acc * packet.value() }
    }

    class Min(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value() = subPackets.fold(Long.MAX_VALUE) { acc, packet -> if (acc < packet.value()) acc else packet.value() }
    }

    class Max(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value() = subPackets.fold(Long.MAX_VALUE) { acc, packet -> if (acc > packet.value()) acc else packet.value() }
    }

    class GT(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value() = if (subPackets[0].value() > subPackets[1].value()) 1.toLong() else 0.toLong()
    }

    class LT(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value() = if (subPackets[0].value() < subPackets[1].value()) 1.toLong() else 0.toLong()
    }

    class EQ(version: Long, typeId: Long, subPackets: List<Packet>, rest: List<Char>) : Operation(version, 0, subPackets, rest) {
        override fun value() = if (subPackets[0].value() == subPackets[1].value()) 1.toLong() else 0.toLong()
    }
}
