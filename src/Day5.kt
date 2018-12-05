class Day5 {
    companion object {
    }

    fun main(args: Array<String>) {
        val list5 = "src/input_5.txt".readFile()[0]
        val test = "dabAcCaCBAcCcaDA"
        (5 to 1).report {
            Day5().partOne(list5)
        }
        (5 to 2).report {
            Day5().partTwo(list5)
        }
    }

    fun partTwo(str: String) {
        val lengths = ('a'..'z').map { c ->
            val testString = str.replace("""[$c${c.toUpperCase()}]""".toRegex(), "")
            c to reducePolymer(testString).length
        }.minBy { it.second }
        println("input.length: ${str.length}")
        println("lengths: ${lengths}")
    }

    fun partOne(str: String) {
        println("input.length: ${str.length}")
        val output = reducePolymer(str)
        println("output.length: ${output.length}")
        println("output: ${output}")
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

    private fun String.rCap() {
        require(this.length == 1)
        val c = this.elementAt(0)
        if (c.isUpperCase()) {
            c.toLowerCase()
        } else {
            c.toUpperCase()
        }
    }
}