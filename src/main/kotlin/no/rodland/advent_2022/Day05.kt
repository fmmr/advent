package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022


object Day05 {
    fun partOne(inputCrates: List<String>, inputCommands: List<String>): String {
        val crates = Crates(inputCrates)
        inputCommands.map { Command(it) }.forEach { crates.apply(it) }
        return crates.signature()
    }
    fun partTwo(inputCrates: List<String>, inputCommands: List<String>): String {
        val crates = Crates(inputCrates)
        inputCommands.map { Command(it) }.forEach { crates.apply(it, false) }
        return crates.signature()
    }

    private class Command(input: String) {
        val num = input.substringAfter("move ").substringBefore(" from").toInt()
        val from = input.substringAfter("from ").substringBefore(" to").toInt()
        val to = input.substringAfter(" to ").toInt()
    }

    private class Crates(input: List<String>) {
        val cratesList = input.last().split(" ").filterNot { it.isEmpty() }.map { it.toInt() }.map { it to it.stringIdx() }
        val content = input.reversed().subList(1, input.size)
        val inventory = cratesList.associate { (num, idx) ->
            num to content.mapNotNull { if (it.length > idx) it[idx] else null }.filterNot { it == ' ' }
        }.toMutableMap()

        fun apply(cmd: Command, reversed: Boolean = true) {
            val oldFrom = inventory[cmd.from]!!
            val takeFrom = oldFrom.takeLast(cmd.num)
            val newFrom = oldFrom.take(oldFrom.size - cmd.num)
            val newTo = inventory[cmd.to]!! + (if (reversed) takeFrom.reversed() else takeFrom)
            inventory[cmd.from] = newFrom
            inventory[cmd.to] = newTo
        }

        fun signature() = inventory.map { it.value.last() }.joinToString("")
        private fun Int.stringIdx(): Int = (this - 1) * 4 + 1
    }

}
