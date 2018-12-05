object Day5 {

    fun partTwo(str: String): Int {
        val newStr = reducePolymer(str)
        return ('a'..'z').map { c ->
            val testString = newStr.replace("""[$c${c.toUpperCase()}]""".toRegex(), "")
            reducePolymer(testString).length
        }.min() ?: throw IllegalStateException("Could not find a solution")
    }

    fun partOne(str: String): Int {
        return reducePolymer(str).length
    }

    private fun reducePolymer(str: String): StringBuffer {
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
        return output
    }

    private fun Char.rCap() = if (isUpperCase()) {
        toLowerCase()
    } else {
        toUpperCase()
    }
}