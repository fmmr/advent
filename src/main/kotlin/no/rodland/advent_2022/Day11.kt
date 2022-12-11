package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

@Suppress("ConvertCallChainIntoSequence")
object Day11 {
    fun partOne(input: List<String>): Long {
        return input.toMonkeys().rounds(20, 3).monkeyBusiness()
    }

    fun partTwo(input: List<String>): Long {
        return input.toMonkeys().rounds(10000, 1).monkeyBusiness()
    }

    private fun List<Monkey>.rounds(rounds: Int, divisor: Int): List<Monkey> {
        val modNumber = map { monkey -> monkey.modNumber }.reduce(Int::times)
        repeat(rounds) { round(divisor, modNumber) }
        return this
    }

    private fun List<Monkey>.round(divisor: Int, modNumber: Int) {
        forEach { monkey ->
            monkey.removeAll { item ->
                val newValue = monkey.inspectAndCount(item, divisor, modNumber)
                val newMonkey = monkey.next(newValue)
                get(newMonkey).add(newValue)
            }
        }
    }
    private fun List<Monkey>.monkeyBusiness() = map { it.count }.sortedDescending().take(2).map { it.toLong() }.reduce(Long::times)

    private fun List<String>.toMonkeys() = joinToString("\n").split("\n\n")
        .map { it.toMonkey() }


    private fun String.toMonkey(): Monkey {
        val id = substringAfter(" ").substringBefore(":").toInt()
        val items = substringAfter("Starting items: ").substringBefore("\n").split(", ").map { it.toLong() }
        val operationText = substringAfter("Operation: new = old ").substringBefore("\n")
        val op = operationText.substringBefore(" ")
        val opValue = operationText.substringAfter(" ")
        val test = substringAfter("Test: divisible by ").substringBefore("\n").toInt()
        val trueMonkey = substringAfter("If true: throw to monkey ").substringBefore("\n").toInt()
        val falseMonkey = substringAfter("If false: throw to monkey ").substringBefore("\n").toInt()
        val operation = { old: Long, divisor: Int, modNumber: Int ->
            val v = if (opValue == "old") old else opValue.toLong()
            val newValue = when (op) {
                "*" -> old * v
                "+" -> old + v
                else -> error("only + and * implemented")
            }
            newValue / divisor % modNumber
        }
        val action = { newValue: Long -> if (newValue.mod(test) == 0) trueMonkey else falseMonkey }
        return Monkey(id, ArrayDeque(items), test, operation, action)
    }

    class Monkey(
        val id: Int,
        private val items: ArrayDeque<Long>,
        val modNumber: Int,
        val inspect: (Long, Int, Int) -> Long,
        val next: (Long) -> Int
    ) : MutableList<Long> by items {

        var count = 0

        fun inspectAndCount(old: Long, divisor: Int, modNumber: Int): Long {
            count++
            return inspect(old, divisor, modNumber)
        }
    }
}


