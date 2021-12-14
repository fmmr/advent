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
        val initialCounts = template
            .windowed(2)
            .groupingBy { it.first() to it.last() }
            .eachCount()
            .mapValues { it.value.toLong() }

        val doubleCounts = (0 until iterations)
            .fold(initialCounts) { acc, i ->
                acc.flatMap { (pair, count) ->
                    val (first, second) = pair
                    val middle = rules[pair]!!
                    listOf((first to middle) to count, (middle to second) to count)
                }
                    .groupingBy { it.first }
                    .fold(0L) { a, e -> a + e.second }
            }
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

        return counts.maxOrNull()!! - counts.minOrNull()!!
    }

    private fun parse(list: List<String>): Pair<List<Char>, Map<Pair<Char, Char>, Char>> {
        return Pair(list[0].toList(), list.drop(2).associate { str -> (str[0] to str[1]) to str.substringAfter("-> ")[0] })
    }
}

