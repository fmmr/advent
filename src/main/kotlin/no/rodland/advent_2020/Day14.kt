package no.rodland.advent_2020

object Day14 {
    private const val noMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

//    mask = X01000100X10X101X110100011X100X11X01
//    mem[39436] = 148937739


    fun partOne(list: List<String>): Long {
        val commands = list.map { it.split(" = ") }.map { it.first() to it.last() }
        val memory = LongArray(commands.getMaxMemory() + 1) { 0 }
        commands.fold(noMask) { mask, command ->
            if (command.first == "mask") {
                command.second
            } else {
                val cmd = MemCommand(command)
                memory[cmd.address] = cmd.applyMask(mask)
                mask
            }
        }

        return memory.sum()
    }

    class MemCommand(val address: Int, val value: Long) {
        fun applyMask(mask: String): Long {
            val valStr = valueAsString()
            return valStr.zip(mask).map {
                when (it.second) {
                    'X' -> it.first
                    '0' -> '0'
                    '1' -> '1'
                    else -> error("unable to parse $it")
                }
            }.joinToString("").toLong(2)
        }

        fun valueAsString() = value.toString(2).padStart(36, '0')

        constructor(str: Pair<String, String>) : this(str.first.split("[").last().split("]").first().toInt(), str.second.toLong())
    }

    private fun List<Pair<String, String>>.getMaxMemory(): Int {
        return (filterNot { it.first == "mask" }.map { MemCommand(it) }.maxByOrNull { it.address }!!).address
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }
}
