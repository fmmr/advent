package no.rodland.advent_2020

typealias Ingredient = Map<String, List<String>>
typealias Alergen = Map<String, List<String>>

@Suppress("UNUSED_PARAMETER")
object Day21 {
    // rblnlnk spmsczc tlrpk nxs ... (contains soy, eggs, nuts)

    // 85 That's not the right answer
    // 4830 That's not the right answer
    fun partOne(list: List<String>): Int {
        val splitted = list
            .map { it.split("(contains ") }
            .map { it.first().split(" ").filterNot { it.isEmpty() } to it.last().replace(")", "").split(", ").filterNot { it.isEmpty() } }

        val allIngredients = splitted.flatMap { it.first }
        val alergens: Alergen = splitted.flatMap { (i, a) ->
            a.map { it to i }
        }.toMap()
        val ingredients: Ingredient = splitted.flatMap { (i, a) ->
            i.map { it to a }
        }.toMap()


        val found = findFromAlergens(splitted)

        return (allIngredients - found.values).size
    }

    // uff....
    private fun findFromAlergens(splitted: List<Pair<List<String>, List<String>>>): MutableMap<String, String> {
        val mapValues = splitted
            .flatMap { listList -> listList.second.map { allergen -> allergen to listList.first.filter { it.isNotEmpty() } } }
            .groupBy { it.first }
            .mapValues { (_, v) -> v.map { it.second } }
        val parsed = mapValues
            .mapValues { (_, v) -> v.map { it.toSet() } }
            .map { (k, v) -> k to (v.reduce { acc, list -> acc.intersect(list) }) }
            .toMap()
        var mut = parsed.toMutableMap()
        val found = mutableMapOf<String, String>()
        while (mut.isNotEmpty()) {
            val newFound = mut.filter { it.value.size == 1 }
            newFound.forEach { k, ing ->
                val ingredient = ing.first()
                found[k] = ingredient
                mut = mut.mapValues { (_, v) -> v.filterNot { it == ingredient }.toSet() }.toMutableMap()
                mut.remove(k)
            }
        }
        return found
    }


    fun partTwo(list: List<String>): String {
        val splitted = list
            .map { it.split("(contains ") }
            .map { it.first().split(" ").filterNot { it.isEmpty() } to it.last().replace(")", "").split(", ").filterNot { it.isEmpty() } }

        val allIngredients = splitted.flatMap { it.first }
        val alergens: Alergen = splitted.flatMap { (i, a) ->
            a.map { it to i }
        }.toMap()
        val ingredients: Ingredient = splitted.flatMap { (i, a) ->
            i.map { it to a }
        }.toMap()


        return findFromAlergens(splitted).toSortedMap().values.joinToString(",")
    }
}
