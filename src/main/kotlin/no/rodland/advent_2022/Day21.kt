package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day21 {
    @Suppress("UNUSED_PARAMETER")
    fun partOne(list: List<String>): Long {
        val monkeys = list.map { it.toMonkey() }.associateBy { it.id }
        val numMonkeys = monkeys.values.map { toNumMonkey(monkeys, it) }.associateBy { it.id }

        return numMonkeys["root"]!!.value
    }

    private fun toNumMonkey(map: Map<String, Monkey>, monkey: Monkey): NumMonkey {
        return when (monkey) {
            is NumMonkey -> monkey
            is OperationMonkey -> NumMonkey(monkey.id, monkey.operation.calc(monkey.left.value, monkey.right.value))
            is StringOperationMonkey -> toNumMonkey(map, OperationMonkey(monkey.id, toNumMonkey(map, map[monkey.left]!!), toNumMonkey(map, map[monkey.right]!!), monkey.operation))
        }
    }


    @Suppress("UNUSED_PARAMETER")
    fun partTwo(list: List<String>): Long {
        return 2
    }

    enum class Operation {
        MINUS, PLUS, MULT, DIV;

        fun calc(left: Long, right: Long): Long {
            return when (this) {
                MINUS -> left - right
                PLUS -> left + right
                MULT -> left * right
                DIV -> left / right
            }
        }

    }

    sealed class Monkey(open val id: String)

    data class OperationMonkey(override val id: String, var left: NumMonkey, var right: NumMonkey, val operation: Operation) : Monkey(id)
    data class StringOperationMonkey(override val id: String, var left: String, var right: String, val operation: Operation) : Monkey(id)
    data class NumMonkey(override val id: String, val value: Long) : Monkey(id)

    //lgvd: ljgn * ptdq
//drzm: hmdt - zczc
//hmdt: 32
    private fun String.toMonkey(): Monkey {
        val (id, rest) = split(": ")
        return if (rest.toLongOrNull() != null) {
            NumMonkey(id, rest.toLongOrNull()!!)
        } else {
            val (l, o, r) = rest.split(" ")
            val op = o.toOp()
            StringOperationMonkey(id, l, r, op)
        }
    }

    fun String.toOp(): Operation {
        return when (trim()) {
            "+" -> Operation.PLUS
            "-" -> Operation.MINUS
            "*" -> Operation.MULT
            "/" -> Operation.DIV
            else -> error("unsupported: ${this}")
        }
    }

}



