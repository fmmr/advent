package no.rodland.advent_2017

import get
import getString
import kotlin.math.max

object Day8 {
    fun run(list: List<String>): Pair<Int, Int> {
        var allTimeMax = 0
        val final = list
                .map { Input(it) }
                .fold(mutableMapOf<String, Int>()) { reg, i ->
                    if (i.condition.test(reg, i.conditionRegister, i.conditionValue)) {
                        if (i.inc) {
                            reg[i.reg] = (reg[i.reg] ?: 0) + i.value
                        } else {
                            reg[i.reg] = (reg[i.reg] ?: 0) - i.value
                        }
                        allTimeMax = max(reg[i.reg]!!, allTimeMax)
                    }
                    reg
                }
        return final.values.max()!! to allTimeMax
    }

    private val re = """(.*) (inc|dec) ([\d-]+) if (.+) (.*) ([\d-]+)""".toRegex()

    data class Input(
            val reg: String,
            val incStr: String,
            val value: Int,
            val conditionRegister: String,
            val condition: Condition,
            val conditionValue: Int
    ) {
        constructor(str: String) : this(re.getString(str)!!,
                re.getString(str, 2)!!,
                re.get(str, 3),
                re.getString(str, 4)!!,
                condition(re.getString(str, 5)!!),
                re.get(str, 6))

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

    private fun condition(str: String): Condition = Condition.values().first { it.symbol == str }
}