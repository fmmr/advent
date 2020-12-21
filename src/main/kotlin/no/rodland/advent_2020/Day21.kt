package no.rodland.advent_2020

typealias Ingredient = Map<String, List<String>>
typealias Alergen = Map<String, List<String>>

@Suppress("UNUSED_PARAMETER")
object Day21 {

    // rblnlnk spmsczc tlrpk nxs ... (contains soy, eggs, nuts)
    private fun split(list: List<String>) = list
        .map { it.split(" (contains ") }
        .map { it.first().split(" ") to it.last().replace(")", "").split(", ") }

    fun partOne(list: List<String>): Int {
        val splitted = split(list)
        val allIngredients = splitted.flatMap { it.first }
        val found = findFromAlergens(splitted)
        return (allIngredients - found.values).size
    }

    fun partTwo(list: List<String>): String {
        val splitted = split(list)
        val found = findFromAlergens(splitted)
        return found.toSortedMap().values.joinToString(",")
    }

    private fun findFromAlergens(splitted: List<Pair<List<String>, List<String>>>): Map<String, String> {
        val found = mutableMapOf<String, String>()

        val alergensToIngredients = splitted
            .flatMap { (i, a) -> a.map { it to i } }
            .groupBy { it.first }
            .mapValues { (_, ingredientList) -> ingredientList.map { it.second } }
            .mapValues { (_, ingredientList) -> ingredientList.map { it.toSet() } }
            .mapValues { (_, ingredientList) -> ingredientList.reduce { acc, set -> acc.intersect(set) } }

        return find(alergensToIngredients)
    }

    private fun find(alergensToIngredients: Map<String, Set<String>>): Map<String, String> {
        if (alergensToIngredients.isEmpty()) {
            return emptyMap()
        }
        val identifyables = alergensToIngredients.filter { it.value.size == 1 }

        val ret = identifyables.map { (k, ingredients) -> k to ingredients.first() }.toMap()

        val heisan = alergensToIngredients.filterNot { it.key in ret.keys }.mapValues { (_, v) -> v.filterNot { it in ret.values }.toSet() }
        return ret + find(heisan)

    }
}
