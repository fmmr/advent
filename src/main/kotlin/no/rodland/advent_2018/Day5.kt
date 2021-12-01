object Day5 {
    fun partOne(str: String): Int {
        return reducePolymer(str).length
    }

    fun partTwo(str: String): Int {
        val newStr = reducePolymer(str)
        return ('a'..'z').map { c ->
            val testString = newStr.replace("""[$c${c.toUpperCase()}]""".toRegex(), "")
            reducePolymer(testString).length
        }.minOrNull() ?: throw IllegalStateException("Could not find a solution")
    }

    fun partTwo_take2(str: String): Int {
        val newStr = reducePolymer(str)
        return ('a'..'z').map { c ->
            reducePolymer_take_2(newStr, c).length
        }.minOrNull() ?: throw IllegalStateException("Could not find a solution")
    }

    fun partTwo_chriswk(str: String): Int {
        val newStr = reducePolymer(str)
        return ('a'..'z').map { c ->
            react(newStr, c).length
        }.minOrNull() ?: throw IllegalStateException("Could not find a solution")
    }


    private fun reducePolymer(str: String): String {
        val output = str.foldIndexed(StringBuffer()) { index, acc, c ->
            acc.apply {
                debug("$index: $acc - $c ")
                if (length > 0 && last() == c.rCap()) {
                    debug("dropping last")
                    deleteCharAt(lastIndex)
                } else {
                    debug("appending $c")
                    append(c)
                }
            }
        }
        return output.toString()
    }


    // copied from https://gitlab.com/chriswk/adventofcode/blob/master/src/main/kotlin/com/chriswk/aoc2018/Day5.kt
    private fun react(polymer: String, ignore: Char? = null): String {
        return polymer.fold(mutableListOf<Char>()) { done, char ->
            when {
                ignore != null && char.equals(ignore, true) -> Unit
                done.lastOrNull() matches char -> done.removeAt(done.size - 1)
                else -> done.add(char)
            }
            done
        }.joinToString("")
    }

    // copied from https://gitlab.com/chriswk/adventofcode/blob/master/src/main/kotlin/com/chriswk/aoc2018/Day5.kt
    private infix fun Char?.matches(other: Char): Boolean {
        return when {
            this == null -> false
            this.toUpperCase() != other.toUpperCase() -> false
            else -> this != other
        }
    }


    // inspired by Christopher
    private fun reducePolymer_take_2(str: String, ignore: Char? = null): StringBuffer {
        val output = str.fold(StringBuffer()) { acc, c ->
            acc.apply {
                when {
                    ignore != null && c.equals(ignore, true) -> Unit
                    lastOrNull() == c.rCap() -> {
                        debug("dropping last")
                        deleteCharAt(lastIndex)
                    }
                    else -> {
                        debug("appending $c")
                        append(c)
                    }
                }
            }
        }
        return output
    }

    private fun Char.rCap() = if (isUpperCase()) {
        toLowerCase()
    } else {
        toUpperCase()
    }
}