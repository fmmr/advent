package no.rodland.advent_2015

@Suppress("EXPERIMENTAL_API_USAGE")
object Day07 {

    fun partOne(input: List<String>, letter: String): Int {
        val curcuit = input.map { it.split(" -> ") }.map { it[1] to OpParam.fromString(it[0]) }.toMap().toMutableMap()
        val node = curcuit[letter]!!
        return calc(node, curcuit)
    }

    fun partTwo(input: List<String>): Int {
        val curcuit = input.map { it.split(" -> ") }.map { it[1] to OpParam.fromString(it[0]) }.toMap().toMutableMap()
        curcuit["b"] = OpParam(Operation.NUM, listOf("16076"))
        val node = curcuit["a"]!!
        return calc(node, curcuit)
    }

    private fun calc(current: OpParam, curcuit: MutableMap<String, OpParam>): Int {
        val intParams = current.params.map {
            if (it.toIntOrNull() != null) {
                it.toIntOrNull()!!
            } else {
                val value = calc(curcuit[it]!!, curcuit)
                curcuit[it] = OpParam(Operation.NUM, listOf(value.toString()))
                value
            }
        }
        val answer = current.operation.exec(intParams)
        return if (answer < 0) answer.toUShort().toInt() else answer
    }

    data class OpParam(val operation: Operation, val params: List<String>) {
        companion object {
            val regex = """(.*) (.*) (.*)""".toRegex()

            fun fromString(str: String): OpParam {
                return when {
                    !str.contains(" ") -> OpParam(Operation.NUM, listOf(str))
                    str.contains("NOT ") -> OpParam(Operation.NOT, listOf(str.replace("NOT ", "")))
                    else -> {
                        val find = regex.find(str)!!
                        OpParam(Operation.valueOf(find.groups[2]!!.value), listOf(find.groups[1]!!.value, find.groups[3]!!.value))
                    }
                }
            }
        }
    }

    enum class Operation {
        NUM,
        AND,
        OR,
        LSHIFT,
        RSHIFT,
        NOT;

        fun exec(list: List<Int>): Int {
            return when (this) {
                NUM -> list[0]
                AND -> list[0] and list[1]
                OR -> list[0] or list[1]
                LSHIFT -> list[0] shl list[1]
                RSHIFT -> list[0] shr list[1]
                NOT -> list[0].inv()
            }
        }
    }
}
