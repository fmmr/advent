package no.rodland.advent_2015

import kotlin.math.max

@Suppress("UNUSED_PARAMETER")
object Day15 {
    val regex = """(.*): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".toRegex()

    fun partOne(list: List<String>): Long {
        val ingredients = list.map { Ingredient(it) }

        return if (ingredients.size == 2) {
            runTwo(ingredients)
        } else {
            runFour(ingredients)
        }
    }

    private fun runTwo(ingredients: List<Ingredient>): Long {
        return (0..100).map { i ->
            val j = 100 - i
            val capacity = ingredients.capacity(listOf(i, j))
            val durability = ingredients.durability(listOf(i, j))
            val flavor = ingredients.flavor(listOf(i, j))
            val texture = ingredients.texture(listOf(i, j))
            capacity * durability * flavor * texture
        }.maxOrNull()!!
    }

    private fun List<Ingredient>.capacity(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.capacity }.sum().toLong())
    private fun List<Ingredient>.durability(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.durability }.sum().toLong())
    private fun List<Ingredient>.flavor(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.flavor }.sum().toLong())
    private fun List<Ingredient>.texture(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.texture }.sum().toLong())

    private fun runFour(ingredients: List<Ingredient>): Long {
        return (0..100).flatMap { i ->
            (0..(100 - i)).flatMap { j ->
                (0..(100 - i - j)).map { k ->
                    val l = 100 - i - j - k
                    val capacity = ingredients.capacity(listOf(i, j, k, l))
                    val durability = ingredients.durability(listOf(i, j, k, l))
                    val flavor = ingredients.flavor(listOf(i, j, k, l))
                    val texture = ingredients.texture(listOf(i, j, k, l))
                    capacity * durability * flavor * texture
                }
            }
        }.maxOrNull()!!
    }

//    val capacity = max(0, i * ingredients[0].capacity + j * ingredients[1].capacity).toLong()
//    val durability = max(0, i * ingredients[0].durability + j * ingredients[1].durability).toLong()
//    val flavor = max(0, i * ingredients[0].flavor + j * ingredients[1].flavor).toLong()
//    val texture = max(0, i * ingredients[0].texture + j * ingredients[1].texture).toLong()
//    capacity * durability * flavor * texture

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
