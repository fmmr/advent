package no.rodland.advent_2016


@Suppress("UNUSED_PARAMETER")
object Day04 {
    fun partOne(list: List<String>): Int {
        return list.map { Room(it) }.filter { it.isReal() }.map { it.sector }.sum()
    }

    fun partTwo(list: List<String>): Int {
        return list
                .map { Room(it) }
                .filter { it.isReal() }
                .first { it.decrypt() == "northpole object storage " }.sector
    }

    private val regex = "(.*)(\\d\\d\\d)\\[(.*)]".toRegex()

    data class Room(val name: String, val sector: Int, val checkSum: String) {
        private val chars = ('a'..'z').toList()

        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(mr.component1(), mr.component2().toInt(), mr.component3())

        fun isReal(): Boolean {
            return checkSum == name
                    .filterNot { it == '-' }
                    .groupBy { it }
                    .mapValues { (_, v) -> v.size }
                    .toList()
                    .sortedWith(compareBy<Pair<Char, Int>> { -it.second }.thenBy { it.first })
                    .joinToString("") { it.first.toString() }
                    .take(checkSum.length)
        }

        fun decrypt(): String {
            return name.map { if (it == '-') ' ' else it.rotate(sector) }.joinToString("")
        }

        private fun Char.rotate(sector: Int): Char {
            val pos = (chars.indexOf(this) + sector) % chars.size
            return chars[pos]
        }
    }

}

