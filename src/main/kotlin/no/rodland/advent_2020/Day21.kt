package no.rodland.advent_2020

// --- Day 21: Allergen Assessment ---
object Day21 {
    fun partOne(list: List<String>): Int {
        val allergens = split(list)
        val found = resolve(allergens)
        val allIngredients = allergens.flatMap { it.first }
        return (allIngredients - found.values).size
    }

    fun partTwo(list: List<String>): String {
        val allergens = split(list)
        val found = resolve(allergens)
        return found.toSortedMap().values.joinToString(",")
    }

    // rblnlnk spmsczc tlrpk nxs ... (contains soy, eggs, nuts)
    private fun split(list: List<String>) = list
        .map { it.split(" (contains ") }
        .map { it.first().split(" ") to it.last().replace(")", "").split(", ") }

    private fun resolve(splitted: List<Pair<List<String>, List<String>>>): Map<String, String> {
        val allergensToIngredients = splitted
            .flatMap { (i, a) -> a.map { it to i } }
            .groupBy { it.first }
            .mapValues { (_, ingredientList) -> ingredientList.map { it.second } }
            .mapValues { (_, ingredientList) -> ingredientList.map { it.toSet() } }
            .mapValues { (_, ingredientList) -> ingredientList.reduce { acc, set -> acc.intersect(set) } }

        return find(allergensToIngredients)
    }

    private fun find(allergens: Map<String, Set<String>>): Map<String, String> {
        if (allergens.isEmpty()) {
            return emptyMap()
        }
        val identifyables = allergens.filter { it.value.size == 1 }
        val found = identifyables.map { (k, ingredients) -> k to ingredients.first() }.toMap()
        val rest = allergens.filterNot { it.key in found.keys }.mapValues { (_, v) -> v.filterNot { it in found.values }.toSet() }
        return found + find(rest)
    }
}
