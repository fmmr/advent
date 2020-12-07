package no.rodland.advent_2015

object Day08 {
    fun partOne(list: List<String>): Int {
        if (list.size < 10) {
            debug(list)
        }
        return list.map { it.codeLength() - it.memLength() }.sum()
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    private fun String.codeLength(): Int = length
    private fun String.memLength(): Int = mem().length

    private fun String.mem() = replace("^\"".toRegex(), "")
        .replace("\"$".toRegex(), "")
        .replace("""\\x([0-9A-Fa-f][0-9A-Fa-f])""".toRegex()) { matchResult -> matchResult.groupValues[1].toInt(16).toChar().toString() }
        .replace("""\\(["\\])""".toRegex(), "$1")

    private fun debug(list: List<String>) {
        list.onEach {
            println("FILE: $it, MEM: ${it.mem()}, CL: ${it.codeLength()}, ML: ${it.memLength()}, DIFF: ${it.codeLength() - it.memLength()}")
        }
    }
}
// 1344 to high