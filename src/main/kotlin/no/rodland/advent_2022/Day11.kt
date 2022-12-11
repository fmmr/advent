package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day11 {
    @Suppress("UNUSED_PARAMETER")
    fun partOne(list: List<String>): Int {
        val monkeys = list.joinToString("\n").split("\n\n").map { Monkey.fromList(it) }.associateBy { it.id }
        repeat(20) { monkeys.round() }
        return monkeys.values.map { it.count }.sortedDescending().take(2).reduce(Int::times)
    }

    private fun Map<Int, Monkey>.round() {
        forEach { (_, monkey) ->
            monkey.removeAll { item ->
                val newValue = monkey.inspectAndCount(item) / 3
                val newMonkey = monkey.next(newValue)
                get(newMonkey)!!.add(newValue)
            }
        }
    }

    class Monkey(val id: Int, private val items: ArrayDeque<Int>, val inspect: (Int) -> Int, val next: (Int) -> Int) : MutableList<Int> by items {

        var count = 0

        fun inspectAndCount(old: Int): Int {
            count++
            return inspect(old)
        }

        companion object {
            fun fromList(input: String): Monkey {
                val id = input.substringAfter(" ").substringBefore(":").toInt()
                val items = input.substringAfter("Starting items: ").substringBefore("\n").split(", ").map { it.toInt() }
                val operationText = input.substringAfter("Operation: new = old ").substringBefore("\n")
                val op = operationText.substringBefore(" ")
                val opValue = operationText.substringAfter(" ")
                val test = input.substringAfter("Test: divisible by ").substringBefore("\n").toInt()
                val trueMonkey = input.substringAfter("If true: throw to monkey ").substringBefore("\n").toInt()
                val falseMonkey = input.substringAfter("If false: throw to monkey ").substringBefore("\n").toInt()
                val operation = { newValue: Int ->
                    val v = if (opValue == "old") newValue else opValue.toInt()
                    when (op) {
                        "*" -> newValue * v
                        "+" -> newValue + v
                        else -> error("only + and * allowed")
                    }
                }
                val action = { newValue: Int -> if (newValue % test == 0) trueMonkey else falseMonkey }
                return Monkey(id, ArrayDeque(items), operation, action)
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }
}


