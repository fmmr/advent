package no.rodland.advent_2017

import get
import getString
import no.rodland.advent_2017.Day8.Condition.*
import kotlin.math.max

object Day8 {
    fun run(list: List<String>): Pair<Int, Int> {
        val input: List<Input> = list.map { input(it) }
        var max = 0
        val final = input.fold(mutableMapOf<String, Int>()) { reg, i ->
            if (i.condition.test(reg, i.conditionRegister, i.conditionValue)) {
                if (i.inc) {
                    reg[i.reg] = (reg[i.reg] ?: 0) + i.value
                } else {
                    reg[i.reg] = (reg[i.reg] ?: 0) - i.value
                }
            }
            max = max(reg.maxBy { it.value }?.value ?: 0, max)
            reg
        }
        return final.maxBy { it.value }!!.value to max
    }

    val re = """(.*) (inc|dec) ([\d-]+) if (.+) (.*) ([\d-]+)""".toRegex()

    private fun input(str: String): Input {
        val d1 = re.getString(str)!!
        val d2 = re.getString(str, 2)!!
        val d3 = re.get(str, 3)
        val d4 = re.getString(str, 4)!!
        val d5 = re.getString(str, 5)!!
        val d6 = re.get(str, 6)
        return Input(d1, d2, d3, d4, condition(d5), d6)
    }

    data class Input(
            val reg: String,
            val incStr: String,
            val value: Int,
            val conditionRegister: String,
            val condition: Condition,
            val conditionValue: Int
    ) {
        val inc = incStr == "inc"
    }

    @Suppress("EnumEntryName")
    enum class Condition(val symbol: String) {
        lt("<"), lte("<="), gt(">"), gte(">="), eq("=="), ne("!=");

        fun test(map: Map<String, Int>, key: String, value: Int): Boolean {
            return when (this) {
                lt -> (map[key] ?: 0) < value
                lte -> (map[key] ?: 0) <= value
                gt -> (map[key] ?: 0) > value
                gte -> (map[key] ?: 0) >= value
                eq -> (map[key] ?: 0) == value
                ne -> (map[key] ?: 0) != value
            }
        }
    }

    private fun condition(str: String): Condition {
        return when (str) {
            "<" -> lt
            "<=" -> lte
            ">" -> gt
            ">=" -> gte
            "==" -> eq
            "!=" -> ne
            else -> error("not a valid str for cond: $str")
        }
    }
}