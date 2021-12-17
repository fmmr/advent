package no.rodland.advent_2021

object Day16 {
    fun partOne(str: String): Int {
        val parse = str.bitList().parse()
        return parse.versionSum()
    }

    fun partTwo(str: String): Long {
        return eval(str)
    }

    // Only used in tests to check parsing
    fun allLiterals(str: String): List<Long> {
        return str.bitList().parse().allLiterals().map { it.value }
    }

    fun eval(str: String): Long {
        return str.bitList().parse().value()
    }

    fun List<Char>.parse(): Packet {
        val (version, rest1) = takeDrop(3)
        val (type, rest2) = rest1.takeDrop(3)
        val versionId = version.toInt()
        val packet = when (val typeId = type.toInt()) {
            4 -> getLiteral(versionId, typeId, rest2)
            else -> {
                rest2.takeDrop(1).let { (length, rest3) ->
                    when (length.single()) {
                        '0' -> parseLengthSub(versionId, typeId, rest3)
                        '1' -> parseNumSub(versionId, typeId, rest3)
                        else -> error("invalid length: $length")
                    }
                }
            }
        }
        return packet
    }

    private fun parseNumSub(versionId: Int, typeId: Int, rest3: List<Char>): Operator {
        val (numberSubPackets, rest4) = rest3.takeDrop(11)
        val subPackets = (0 until numberSubPackets.toLong()).fold(emptyList<Packet>() to rest4) { acc, _ ->
            val (list, rest) = acc
            val packet = rest.parse()
            list + packet to packet.rest
        }
        return Operator(versionId, typeId, subPackets.first, subPackets.second)
    }

    private fun parseLengthSub(versionId: Int, typeId: Int, seq: List<Char>): Operator {
        fun parseLengthSub(subPacketCharList: List<Char>): List<Packet> {
            return if (subPacketCharList.isEmpty()) {
                emptyList()
            } else {
                subPacketCharList.parse().let { listOf(it) + parseLengthSub(it.rest) }
            }
        }

        val (lengthSubPackets, rest1) = seq.takeDrop(15)
        val (subPacketCharList, rest2) = rest1.takeDrop(lengthSubPackets.toLong())
        val subPackets = parseLengthSub(subPacketCharList)
        return Operator(versionId, typeId, subPackets, rest2)
    }


    private fun getLiteral(versionId: Int, typeId: Int, seq: List<Char>): Packet {
        fun getLiteral(seq: List<Char>): Pair<List<Char>, List<Char>> {
            val (literal, rest) = seq.takeDrop(5)
            val (continueBit, literalRest) = literal.takeDrop(1)
            return when (continueBit.single()) {
                '1' -> getLiteral(rest).let { (next, rem) -> (literalRest + next) to rem }
                '0' -> literal.drop(1) to rest
                else -> error("Invalid length bit: $continueBit")
            }
        }

        val (lit, rest) = getLiteral(seq)
        val value = lit.joinToString("").toLong(2)
        return Literal(versionId, typeId, value, rest)
    }

    private fun String.bitList() = map { it.digitToInt(16).toString(2).padStart(4, '0') }.flatMap { it.toList() }
    private fun List<Char>.toInt(): Int = toBits().toInt(2)
    private fun List<Char>.toLong(): Long = toBits().toLong(2)
    private fun List<Char>.toBits() = toList().joinToString("")
    private fun List<Char>.takeDrop(i: Long): Pair<List<Char>, List<Char>> = take(i.toInt()) to drop(i.toInt())

    sealed class Packet(val version: Int, val typeId: Int, val rest: List<Char>) {
        abstract fun versionSum(): Int
        abstract fun value(): Long
        abstract fun allLiterals(): List<Literal>

        override fun toString(): String {
            return "Packet(version=$version, typeId=$typeId)"
        }
    }

    class Literal(version: Int, typeId: Int, val value: Long, rest: List<Char>) : Packet(version, typeId, rest) {
        override fun versionSum(): Int = version
        override fun value(): Long = value
        override fun allLiterals(): List<Literal> = listOf(this)

        override fun toString(): String {
            return "Literal(version=$version, value=$value)"
        }
    }

    class Operator(version: Int, typeId: Int, private val subPackets: List<Packet>, rest: List<Char>) : Packet(version, typeId, rest) {
        override fun versionSum(): Int = version + subPackets.sumOf { it.versionSum() }
        override fun allLiterals(): List<Literal> = subPackets.flatMap { it.allLiterals() }
        override fun toString(): String {
            return "Operator(version=$version, typeId=$typeId, subpackets=$subPackets)"
        }

        override fun value(): Long = when (typeId) {
            0 -> subPackets.sumOf { it.value() }
            1 -> subPackets.fold(1) { acc, next -> acc * next.value() }
            2 -> subPackets.minOf { it.value() }
            3 -> subPackets.maxOf { it.value() }
            5 -> if (subPackets[0].value() > subPackets[1].value()) 1 else 0
            6 -> if (subPackets[0].value() < subPackets[1].value()) 1 else 0
            7 -> if (subPackets[0].value() == subPackets[1].value()) 1 else 0
            else -> error("No Op for $typeId")
        }
    }
}
