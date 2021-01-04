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

    fun partTwo(list: List<String>): Long {
        val ingredients = list.map { Ingredient(it) }
        return if (ingredients.size == 2) {
            error("only implemented for 4 ingredients.")
        } else {
            cal500(ingredients)
        }
    }

    private fun List<Ingredient>.capacity(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.capacity }.sum().toLong())
    private fun List<Ingredient>.durability(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.durability }.sum().toLong())
    private fun List<Ingredient>.flavor(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.flavor }.sum().toLong())
    private fun List<Ingredient>.texture(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.texture }.sum().toLong())
    private fun List<Ingredient>.calories(num: List<Int>) = max(0, mapIndexed { index: Int, ingredient: Ingredient -> num[index] * ingredient.calories }.sum().toLong())

    private fun runTwo(ingredients: List<Ingredient>): Long {
        return (0..100).asSequence().map { i ->
            val j = 100 - i
            val capacity = ingredients.capacity(listOf(i, j))
            val durability = ingredients.durability(listOf(i, j))
            val flavor = ingredients.flavor(listOf(i, j))
            val texture = ingredients.texture(listOf(i, j))
            capacity * durability * flavor * texture
        }.maxOrNull()!!
    }

    private fun runFour(ingredients: List<Ingredient>): Long {
        return (0..100).asSequence().flatMap { i ->
            (0..(100 - i)).asSequence().flatMap { j ->
                (0..(100 - i - j)).asSequence().map { k ->
                    score(ingredients, i, j, k, 100 - i - j - k)
                }
            }
        }.maxOrNull()!!
    }

    private fun cal500(ingredients: List<Ingredient>): Long {
        return (0..100).asSequence().flatMap { i ->
            (0..(100 - i)).asSequence().flatMap { j ->
                (0..(100 - i - j)).asSequence().map { k ->
                    (100 - i - j - k).let { l ->
                        ingredients.calories(listOf(i, j, k, l)) to score(ingredients, i, j, k, l)
                    }
                }
            }
        }
            .filter { it.first == 500L && it.second > 0 }
            .maxByOrNull { it.second }!!
            .second
    }

    private fun score(ingredients: List<Ingredient>, i: Int, j: Int, k: Int, l: Int): Long {
        val capacity = ingredients.capacity(listOf(i, j, k, l))
        val durability = ingredients.durability(listOf(i, j, k, l))
        val flavor = ingredients.flavor(listOf(i, j, k, l))
        val texture = ingredients.texture(listOf(i, j, k, l))
        return capacity * durability * flavor * texture
    }

    // your answer is too low 8844
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
