package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

@Suppress("ConvertCallChainIntoSequence")
object Day11 {
    fun partOne(list: List<String>): Int {
        val (monkeys, modNumber) = list.toMonkeys()
        repeat(20) { monkeys.round(3, modNumber) }
        return monkeys.values.map { it.count }.sortedDescending().take(2).reduce(Int::times)
    }

    fun partTwo(list: List<String>): Long {
        val (monkeys, modNumber) = list.toMonkeys()
        repeat(10000) { monkeys.round(1, modNumber) }
        return monkeys.values.map { it.count }.sortedDescending().take(2).map { it.toLong() }.reduce(Long::times)
    }

    private fun Map<Int, Monkey>.round(divisor: Int = 1, modNumber: Int = -1) {
        forEach { (_, monkey) ->
            monkey.removeAll { item ->
                val inspected = monkey.inspectAndCount(item) / divisor
                val newValue = inspected % modNumber
                val newMonkey = monkey.next(newValue)
                get(newMonkey)!!.add(newValue)
            }
        }
    }

    private fun List<String>.toMonkeys() = joinToString("\n").split("\n\n")
        .map { it.toMonkey() }
        .associateBy { it.id }
        .let {
            it to it.values.map { monkey -> monkey.modNumber }.reduce(Int::times)
        }

    private fun String.toMonkey(): Monkey {
        val id = substringAfter(" ").substringBefore(":").toInt()
        val items = substringAfter("Starting items: ").substringBefore("\n").split(", ").map { it.toLong() }
        val operationText = substringAfter("Operation: new = old ").substringBefore("\n")
        val op = operationText.substringBefore(" ")
        val opValue = operationText.substringAfter(" ")
        val test = substringAfter("Test: divisible by ").substringBefore("\n").toInt()
        val trueMonkey = substringAfter("If true: throw to monkey ").substringBefore("\n").toInt()
        val falseMonkey = substringAfter("If false: throw to monkey ").substringBefore("\n").toInt()
        val operation = { newValue: Long ->
            val v = if (opValue == "old") newValue else opValue.toLong()
            when (op) {
                "*" -> newValue * v
                "+" -> newValue + v
                else -> error("only + and * allowed")
            }
        }
        val action = { newValue: Long -> if (newValue.mod(test) == 0) trueMonkey else falseMonkey }
        return Monkey(id, ArrayDeque(items), test, operation, action)
    }


    class Monkey(val id: Int, private val items: ArrayDeque<Long>, val modNumber: Int, val inspect: (Long) -> Long, val next: (Long) -> Int) : MutableList<Long> by items {

        var count = 0

        fun inspectAndCount(old: Long): Long {
            count++
            return inspect(old)
        }
    }

}


