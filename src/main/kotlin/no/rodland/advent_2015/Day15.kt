package no.rodland.advent_2015

@Suppress("UNUSED_PARAMETER")
object Day15 {
    val regex = """(.*): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".toRegex()
    fun partOne(list: List<String>): Int {
        val ingredients = list.map { Ingredient(it) }
        return 2
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }

    data class Ingredient(val name: String, val capacity: Int, val durability: Int, val flavor: Int, val texture: Int, val calories: Int) {
        constructor(str: String, mr: MatchResult.Destructured = regex.find(str)!!.destructured) : this(
            mr.component1(),
            mr.component2().toInt(),
            mr.component3().toInt(),
            mr.component4().toInt(),
            mr.component5().toInt(),
            mr.component6().toInt(),
        )
    }
}
