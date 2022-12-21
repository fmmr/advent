package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day21 {
    fun partOne(list: List<String>): Long {
        val monkeys = list.map { it.toMonkey() }.associateBy { it.id }
        val numMonkeys = monkeys.values.map { toNumMonkey(monkeys, it) }
        return numMonkeys.first { it.id == "root" }.value
    }

    @Suppress("UNUSED_VARIABLE")
    fun partTwo(list: List<String>): Long {
        val monkeys = list.map { it.toMonkey() }.associateBy { it.id }
        val root = monkeys["root"] as OperationMonkey
        val humanLeft = isHumanInTree(monkeys, root.left)
        val (num, eq) = if (humanLeft) {
            toNumMonkey(monkeys, toNumMonkey(monkeys, monkeys[root.right]!!)).value to toEq(monkeys, monkeys[root.left]!!)
        } else {
            toNumMonkey(monkeys, toNumMonkey(monkeys, monkeys[root.left]!!)).value to toEq(monkeys, monkeys[root.right]!!)
        }
        // https://www.mathpapa.com/equation-solver/
        // solves the output from this printlln:
        println("$num=$eq")
        return 2L
    }

    private fun toEq(map: Map<String, Monkey>, monkey: Monkey): String {
        return when {
            monkey.id == "humn" -> "x"
            monkey is NumMonkey -> monkey.value.toString()
            monkey is OperationMonkey -> {
                val (left, right) = if (isHumanInTree(map, monkey.left)) {
                    toEq(map, map[monkey.left]!!) to toNumMonkey(map, map[monkey.right]!!).value.toString()
                } else {
                    toNumMonkey(map, map[monkey.left]!!).value.toString() to toEq(map, map[monkey.right]!!)
                }
                "(" + left + monkey.operation.op + right + ")"
            }
            else -> error("nope")
        }
    }

    private fun isHumanInTree(monkeys: Map<String, Monkey>, start: String): Boolean {
        val monkey = monkeys[start]!!
        return when {
            monkey.id == "humn" -> true
            monkey is NumMonkey -> false
            monkey is OperationMonkey -> isHumanInTree(monkeys, monkey.left) || isHumanInTree(monkeys, monkey.right)
            else -> error("nope")
        }
    }

    private fun toNumMonkey(map: Map<String, Monkey>, monkey: Monkey): NumMonkey {
        return when (monkey) {
            is NumMonkey -> monkey
            is OperationMonkey -> NumMonkey(monkey.id, monkey.operation.calc(toNumMonkey(map, map[monkey.left]!!).value, toNumMonkey(map, map[monkey.right]!!).value))
        }
    }

    enum class Operation(val op: String, val operation: (Long, Long) -> Long) {
        MINUS("-", { n1, n2 -> n1 - n2 }), PLUS("+", { n1, n2 -> n1 + n2 }), MULT("*", { n1, n2 -> n1 * n2 }), DIV("/", { n1, n2 -> n1 / n2 });

        fun calc(left: Long, right: Long) = operation(left, right)
    }

    sealed class Monkey(open val id: String)
    data class OperationMonkey(override val id: String, var left: String, var right: String, val operation: Operation) : Monkey(id)
    data class NumMonkey(override val id: String, val value: Long) : Monkey(id)

    private fun String.toMonkey(): Monkey {
        val (id, rest) = split(": ")
        return if (rest.toLongOrNull() != null) {
            NumMonkey(id, rest.toLongOrNull()!!)
        } else {
            val (l, o, r) = rest.split(" ")
            OperationMonkey(id, l, r, o.toOp())
        }
    }

    private fun String.toOp(): Operation = Operation.values().first { it.op == this }
}




