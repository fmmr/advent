package no.rodland.advent_2020

import kotlin.math.pow

object Day14 {
    private const val noMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

//    mask = X01000100X10X101X110100011X100X11X01
//    mem[39436] = 148937739


    fun partOne(list: List<String>): Long {
        val commands = list.map { it.split(" = ") }.map { it.first() to it.last() }
        val memory = LongArray(commands.getMaxMemory().toInt() + 1) { 0 }
        commands.fold(noMask) { mask, command ->
            if (command.first == "mask") {
                command.second
            } else {
                val cmd = MemCommand(command)
                memory[cmd.address.toInt()] = cmd.applyMask(mask)
                mask
            }
        }

        return memory.sum()
    }

    fun partTwo(list: List<String>): Long {
        val commands = list.map { it.split(" = ") }.map { it.first() to it.last() }
        val memory = mutableMapOf<Long, Long>()
        commands.fold("") { mask, command ->
            if (command.first == "mask") {
                command.second
            } else {
                val cmd = MemCommand(command)
                MemCommand(command).getAdresses(mask).forEach { memory[it] = cmd.value }
                mask
            }
        }
        return memory.values.sum()
    }


    class MemCommand(val address: Long, val value: Long) {

        fun getAdresses(mask: String): List<Long> {
            val maskWithX = maskWithX(mask)
            val countX = maskWithX.count { it == 'X' }  // we should have 2^countX addresses
            val numberAdresses = (2.0).pow(countX).toInt()
            return (0 until numberAdresses).map { permutation ->
                permutation.toString(2).padStart(countX, '0')
            }.map { permutation ->
                var idx = 0
                maskWithX.map { c ->
                    when (c) {
                        'X' -> permutation[idx++]  // replace nth x in mask with nth bit in permutation
                        else -> c
                    }
                }.joinToString("")
            }.map { it.toLong(2) }
        }

        fun applyMask(mask: String): Long {
            val valStr = value.asBits()
            return valStr.zip(mask).map {
                when (it.second) {
                    'X' -> it.first
                    else -> it.second
                }
            }.joinToString("").toLong(2)
        }

        private fun maskWithX(mask: String): String {
            val valStr = address.asBits()
            return valStr.zip(mask) { zipValue, zipMask ->
                when (zipMask) {
                    '0' -> zipValue
                    else -> zipMask
                }
            }.joinToString("")
        }

        private fun Long.asBits() = toString(2).padStart(36, '0')

        constructor(str: Pair<String, String>) : this(str.first.split("[").last().split("]").first().toLong(), str.second.toLong())
    }

    private fun List<Pair<String, String>>.getMaxMemory(): Long {
        return (filterNot { it.first == "mask" }.map { MemCommand(it) }.maxByOrNull { it.address }!!).address
    }

}
