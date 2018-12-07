object Day7 {
    fun partOne(data: List<String>): String {
        val fix = data.fix()
        return process(fix).joinToString("")
    }

    private fun process(fix: List<Pair<String, String>>): List<String> {
        if (fix.size == 1) {
            return listOf(fix[0].first, fix[0].second)
        }
        val next: Pair<String, String> = getNext(fix)
        return listOf(next.first, *process(fix.filter { it.first != next.first }).toTypedArray())
    }

    private fun getNext(fix: List<Pair<String, String>>): Pair<String, String> {
        if (fix.size == 1) {
            return fix[0]
        }
        val endLetters = fix.map { it.second }.toSet()
        return fix.filter { !endLetters.contains(it.first) }.sortedBy { it.first }.first()
    }

    fun partTwo(data: List<String>): Int {
        return 2
    }

    private fun List<String>.fix(): List<Pair<String, String>> {
        return this.map { it.replace("Step ", "").replace(" must be finished before step ", " ").replace(" can begin.", "") }
                .map { it.split(" ") }
                .map { it[0] to it[1] }
    }

}