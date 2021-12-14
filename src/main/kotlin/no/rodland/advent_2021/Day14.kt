package no.rodland.advent_2021

object Day14 {
    fun partOneAsPartTwo(list: List<String>): Long {
        return partTwo(list, 10)
    }

    fun partOne(list: List<String>): Long {
        val (template, rules) = parse(list)
        val result = (0 until 10)
            .fold(template) { acc, n ->
                acc
                    .windowed(2)
                    .flatMap { (first, second) ->
                        val middle = rules[first to second]!!
                        listOf(first, middle)
                    } + acc.last()
            }
            .groupingBy { it }
            .eachCount().values.map { it.toLong() }
        return result.maxOrNull()!! - result.minOrNull()!!
    }

    fun partTwo(list: List<String>): Long {
        return partTwo(list, 40)
    }

    fun partTwo(list: List<String>, iterations: Int): Long {
        val (template, rules) = parse(list)
        val initialCounts = initialCounts(template)
        val pairCounts = (0 until iterations).fold(initialCounts) { acc, i -> insert(acc, rules) }
        val (max, min) = maxMin(pairCounts, template)
        return max - min
    }

    private fun initialCounts(template: List<Char>) = template
        .windowed(2)
        .groupingBy { it.first() to it.last() }
        .eachCount()
        .mapValues { it.value.toLong() }

    private fun maxMin(pairCounts: Map<Pair<Char, Char>, Long>, template: List<Char>): Pair<Long, Long> {
        val doubleCounts = pairCounts
            .toList()
            .flatMap { (pair, count) -> listOf(pair.first to count, pair.second to count) }
            .groupingBy { it.first }
            .fold(0L) { a, e -> a + e.second }
            .toMutableMap()

        // first and last element are not double-counted
        doubleCounts[template.first()] = doubleCounts[template.first()]!! + 1
        doubleCounts[template.last()] = doubleCounts[template.last()]!! + 1

        // doubleCounts is... well... double counted.
        val counts = doubleCounts.map { it.value / 2 }

        val max = counts.maxOrNull()!!
        val min = counts.minOrNull()!!
        return Pair(max, min)
    }

    private fun insert(acc: Map<Pair<Char, Char>, Long>, rules: Map<Pair<Char, Char>, Char>) =
        acc.flatMap { (pair, count) ->
            val (first, second) = pair
            val middle = rules[pair]!!
            listOf((first to middle) to count, (middle to second) to count)
        }
            .groupingBy { it.first }
            .fold(0L) { a, e -> a + e.second }

    private fun parse(list: List<String>): Pair<List<Char>, Map<Pair<Char, Char>, Char>> {
        return Pair(list[0].toList(), list.drop(2).associate { str -> (str[0] to str[1]) to str.substringAfter("-> ")[0] })
    }
}

