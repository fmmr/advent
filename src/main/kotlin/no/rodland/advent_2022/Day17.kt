package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day17 {
    fun partOne(input: String): Long {
        val repeatingSeq = input.repeat()
        val rocksSeq = rocks.repeat()
        return 2
    }

    private fun String.repeat() = sequence {
        val string = this@repeat
        var i = 0
        while (true) {
            this.yield(string[i++ % length])
        }
    }

    private fun List<String>.repeat() = sequence {
        val list = this@repeat
        var i = 0
        while (true) {
            this.yield(list[i++ % size])
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(input: String): Long {
        return 2
    }

    val rocks = listOf(
        "####",
        """
            .#.
            ###
            .#.
        """.trimIndent(),
        """
            ..#
            ..#
            ###
        """.trimIndent(),
        """
            #
            #
            #
            #
        """.trimIndent(),
        """
            ##
            ##
        """.trimIndent(),
    )
}
