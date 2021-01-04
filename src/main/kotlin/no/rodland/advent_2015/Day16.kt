package no.rodland.advent_2015

@Suppress("UNUSED_PARAMETER")
object Day16 {
    val regex = """Sue (\d+): (.*): (\d+), (.*): (\d+), (.*): (\d+)""".toRegex()

    fun partOne(list: List<String>): Int {
        val sues = list.map { Sue(it) }
        return sues.first { it.matchesPart1() }.sue
    }

    fun partTwo(list: List<String>): Int {
        val sues = list.map { Sue(it) }
        return sues.first { it.matchesPart2() }.sue
    }

    val mfcsam = mapOf(
        "children" to 3,
        "cats" to 7,
        "samoyeds" to 2,
        "pomeranians" to 3,
        "akitas" to 0,
        "vizslas" to 0,
        "goldfish" to 5,
        "trees" to 3,
        "cars" to 2,
        "perfumes" to 1,
    )

    data class Sue(val sue: Int, val map: Map<String, Int>, val nonSpecial: Map<String, Int> = map.filterNot { it.key in listOf("cats", "trees", "pomeranians", "goldfish") }) {
        fun matchesPart1(): Boolean = matchesMap(map)

        fun matchesPart2(): Boolean = if (matchesMap(nonSpecial)) {
            !(shouldBeGreater("trees") || shouldBeGreater("cats") || shouldBeLess("pomeranians") || shouldBeLess("goldfish"))
        } else {
            false
        }

        private fun matchesMap(testMap: Map<String, Int>) = testMap.all { (k, v) -> mfcsam[k] == v }

        private fun shouldBeGreater(param: String) = map.getOrDefault(param, maxmfcsam) <= mfcsam[param]!!
        private fun shouldBeLess(param: String) = map.getOrDefault(param, minmfcsam) >= mfcsam[param]!!

        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(
            mr.component1().toInt(),
            mapOf(
                mr.component2() to mr.component3().toInt(),
                mr.component4() to mr.component5().toInt(),
                mr.component6() to mr.component7().toInt()
            )
        )

        companion object {
            val maxmfcsam = mfcsam.map { it.value }.maxOrNull()!! + 1
            val minmfcsam = mfcsam.map { it.value }.minOrNull()!! - 1
        }
    }
}
