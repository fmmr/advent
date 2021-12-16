package no.rodland.advent_2021

import prePad


@Suppress("UNUSED_PARAMETER")
object Day16 {
    fun partOne(str: String): Int {
        val parse = str.toListOfBits().parse()
        return parse.versionSum()
    }

    fun partTwo(str: String): Int {
        return 2
    }

    fun List<Char>.parse(): Packet {
        val (version, rest1) = takeDrop(3)
        val (type, rest2) = rest1.takeDrop(3)

        val versionId = version.toInt()
        val typeId = type.toInt()
//        println("Parsing ${versionId}_$typeId")
        val packet = when (typeId) {
            4 -> {
                getLiteralPacket(version.toInt(), typeId, rest2)
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

    private fun parseNumSub(versionId: Int, typeId: Int, rest3: List<Char>): Operation {
        val (numberSubPackets, rest4) = rest3.takeDrop(11)
        val subPackets = (0 until numberSubPackets.toInt()).fold(emptyList<Packet>() to rest4) { acc, _ ->
            val (list, rest) = acc
            val packet = rest.parse()
            list + packet to packet.rest
        }
        return Operation(versionId, typeId, subPackets.first, subPackets.second)
    }

    private fun parseLengthSub(versionId: Int, typeId: Int, seq: List<Char>): Operation {


        val (lengthSubPackets, rest1) = seq.takeDrop(15)
        val (subPacketCharList, rest2) = rest1.takeDrop(lengthSubPackets.toInt())
        val subPackets = parseLengthSub(subPacketCharList)
        return Operation(versionId, typeId, subPackets, rest2)
    }

    private fun parseLengthSub(subPacketCharList: List<Char>): List<Packet> {
        return if (subPacketCharList.isEmpty()) {
            emptyList()
        } else {
            val packet = subPacketCharList.parse()
            listOf(packet) + parseLengthSub(packet.rest)
        }
    }

    private fun getLiteralPacket(versionId: Int, typeId: Int, seq: List<Char>): Packet {
        val (lit, rest) = getLiteral(seq)
        return Literal(versionId, typeId, lit.joinToString("").toLong(2), rest)
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

    private fun String.toListOfBits() = map { it.toString().toInt(16) }
        .map { it.toString(2) }
        .map { it.prePad(4, "0") }
        .flatMap { it.toList() }


    private fun List<Char>.toInt(): Int = toBits().toInt(2)

    private fun List<Char>.toBits() = toList().joinToString("")

    private fun List<Char>.takeDrop(i: Int): Pair<List<Char>, List<Char>> {
        return take(i) to drop(i)
    }


    // Only used in tests to check parsing
    fun allLiterals(str: String): List<Long> {
        return str.toListOfBits().parse().allLiterals().map { it.value }
    }


    sealed class Packet(val version: Int, val typeId: Int, val rest: List<Char>) {
        abstract fun versionSum(): Int
        abstract fun allLiterals(): List<Literal>

        override fun toString(): String {
            return "Packet(version=$version, typeId=$typeId, rest=$rest)"
        }
    }

    class Literal(version: Int, typeId: Int, val value: Long, rest: List<Char>) : Packet(version, typeId, rest) {
        override fun versionSum(): Int = version
        override fun allLiterals(): List<Literal> = listOf(this)
    }

    class Operation(version: Int, typeId: Int, val subPackets: List<Packet>, rest: List<Char>) : Packet(version, typeId, rest) {
        override fun versionSum(): Int = version + subPackets.sumOf { it.versionSum() }
        override fun allLiterals(): List<Literal> = subPackets.flatMap { it.allLiterals() }
    }
}
