package no.rodland.advent_2020

import kotlin.math.pow

object Day14 {
    fun partOne(list: List<String>): Long {
        return runProgram(list, { cmd: MemCommand, _: String -> listOf(cmd.address) }, { cmd: MemCommand, mask: String -> cmd.valueMask(mask) })
    }

    fun partTwo(list: List<String>): Long {
        return runProgram(list, { cmd: MemCommand, mask: String -> cmd.getAdresses(mask) }, { cmd: MemCommand, _: String -> cmd.value })
    }

    private fun runProgram(list: List<String>, adressesGetter: (MemCommand, String) -> List<Long>, valueGetter: (MemCommand, String) -> Long): Long {
        val commands = list.map { it.split(" = ") }.map { it.first() to it.last() }
        val memory = mutableMapOf<Long, Long>()
        commands.fold("") { mask, command ->  // ONLY works if first instruction is a mask
            if (command.first == "mask") {
                command.second
            } else {
                val cmd = MemCommand(command)
                val addresses = adressesGetter(cmd, mask)
                addresses.forEach { memory[it] = valueGetter(cmd, mask) }
                mask
            }
        }
        return memory.values.sum()
    }

    class MemCommand(val address: Long, val value: Long) {
        constructor(str: Pair<String, String>) : this(str.first.split("[").last().split("]").first().toLong(), str.second.toLong())

        fun getAdresses(mask: String): List<Long> {
            val maskWithX = addressMask(mask)
            val countX = maskWithX.count { it == 'X' }  // we should have 2^countX addresses
            val numberAdresses = (2.0).pow(countX).toInt()
            val permutations = (0 until numberAdresses).map { it.toString(2).padStart(countX, '0') }
            val adresses = permutations
                .map { permutation ->
                    var idx = 0
                    maskWithX.map { if (it == 'X') permutation[idx++] else it }  // replace nth x in mask with nth bit in permutation
                }
            return adresses.map { it.joinToString("").toLong(2) }
        }

        fun valueMask(mask: String): Long = value.mask(mask, 'X').toLong(2)

        private fun addressMask(mask: String): String = address.mask(mask, '0')

        private fun Long.mask(mask: String, valueChar: Char): String = asBits().zip(mask) { zipValue, zipMask -> if (zipMask == valueChar) zipValue else zipMask }.joinToString("")

        private fun Long.asBits() = toString(2).padStart(36, '0')

    }

    private fun List<Pair<String, String>>.getMaxMemory(): Long {
        return (filterNot { it.first == "mask" }.map { MemCommand(it) }.maxByOrNull { it.address }!!).address
    }

}
