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

    private fun findFromAlergens(splitted: List<Pair<List<String>, List<String>>>): MutableMap<String, String> {
        val found = mutableMapOf<String, String>()

        val alergensToIngredients = splitted
            .flatMap { (i, a) -> a.map { it to i } }
            .groupBy { it.first }
            .mapValues { (_, ingredientList) -> ingredientList.map { it.second } }
            .mapValues { (_, ingredientList) -> ingredientList.map { it.toSet() } }
            .mapValues { (_, ingredientList) -> ingredientList.reduce { acc, set -> acc.intersect(set) } }

        find(found, alergensToIngredients)
        return found
    }

//    private fun recursiveFind2(alergensToIngredients: Map<String, Set<String>>): MutableMap<String, String> {
//        val identifyables = alergensToIngredients.filter { it.value.size == 1 }
//
//        val ret = identifyables.map { (k, ingredients) -> k to ingredients.first() }
//
//        alergensToIngredients.mapValues { (_, v) -> v.filterNot { it == ingredient }.toSet() }.toMutableMap()
//
//        ingredients.first().let { ingredient ->
//            found[k] = ingredient
//            alergensToIngredients1 =
//        }
//    }
//}

    private fun find(found: MutableMap<String, String>, alergensToIngredients: Map<String, Set<String>>) {
        var alergensToIngredients1 = alergensToIngredients
        while (found.size < alergensToIngredients1.size) {
            val identifyables = alergensToIngredients1.filter { it.value.size == 1 }
            identifyables.forEach { (k, ingredients) ->
                ingredients.first().let { ingredient ->
                    found[k] = ingredient
                    alergensToIngredients1 = alergensToIngredients1.mapValues { (_, v) -> v.filterNot { it == ingredient }.toSet() }.toMutableMap()
                }
            }
        }
    }
}
