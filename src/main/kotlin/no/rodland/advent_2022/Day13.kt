package no.rodland.advent_2022

import org.json.simple.JSONArray
import org.json.simple.JSONValue
import takeUntil

// template generated: 28/11/2022
// Fredrik Rødland 2022

object Day13 {

    var count = 0  // keeping track of the number of compares

    fun partOne(list: List<String>): Int {
        count =0
        val indices = list
            .asSequence()
            .filterNot { it.isEmpty() }
            .chunked(2)
            .map { it.first() to it.last() }
            .mapIndexed { i, pair -> i to pair.rightOrder() }
            .filter { it.second }
            .map { it.first + 1 }
            .toList()
        println("did $count compares")
        return indices.sum()
    }

    fun partTwo(list: List<String>): Int {
        count =0
        val p1 = PacketData.from("[[2]]")
        val p2 = PacketData.from("[[6]]")
        val all = list
            .filterNot { it.isEmpty() }
            .map { PacketData.from(it) }
            .toMutableList()
            .apply {
                add(p1)
                add(p2)
            }
            .sorted()
        println("did $count compares")
        return (all.indexOf(p1) + 1) * (all.indexOf(p2) + 1)
    }

    fun Pair<String, String>.rightOrder(): Boolean {
        return PacketData.from(first) < PacketData.from(second)
    }

    data class PacketData(val values: List<Any>) : Comparable<PacketData>, List<Any> by values {
        override fun compareTo(other: PacketData): Int {
            val zip = zip(other)
                .asSequence()
                .map { (first, second) ->
                    count++
                    val secondNum = second.toString().toIntOrNull()
                    val firstAsNum = first.toString().toIntOrNull()
                    if (firstAsNum != null && secondNum != null) {
                        firstAsNum.compareTo(secondNum)
                    } else {
                        from(first).compareTo(from(second))
                    }
                }
                .takeUntil { it != 0 }

            val negFirst = zip.indexOfFirst { it < 0 } >= 0
            return if (negFirst) {
                return (zip.all { it <= 0 }).toCompareResult()
            } else {
                if (zip.indexOfFirst { it > 0 } >= 0) {
                    return false.toCompareResult()
                } else {
                    size - other.size
                }
            }
        }

        private fun Boolean.toCompareResult() = if (this) -1 else 1

        @Suppress("UNCHECKED_CAST")
        companion object {
            fun from(value: Any): PacketData = when (value) {
                is List<*> -> PacketData(value as List<Any>)
                is String -> PacketData(mapToLists(JSONValue.parse(value)) as List<Any>)

                else -> PacketData(listOf(value))
            }

            private fun mapToLists(parse: Any): Any {
                return when (parse) {
                    is JSONArray -> parse.map { mapToLists(it!!) }
                    is Long -> parse.toInt()
                    else -> error("$parse type is " + parse.javaClass)
                }
            }
        }
    }

}


