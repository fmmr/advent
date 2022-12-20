package no.rodland.advent_2022

// template generated: 28/11/2022
// Fredrik Rødland 2022


object Day20 {
    const val DECRYPTION_KEY = 811589153L

    fun partOne(list: List<String>): Long {
        return list.mix()
    }

    fun partTwo(list: List<String>): Any {
        return list.mix(DECRYPTION_KEY, 10)
    }

    private fun List<String>.mix(key: Long = 1L, repeat: Int = 1): Long {
        val objects = map { Obj(it.toLong() * key) }
        val array = objects.toTypedArray()
        repeat(repeat) { array.mix(objects) }
        return array.groveCoordinates()
    }

    private fun Array<Obj>.groveCoordinates(): Long {
        val base = indexOfFirst { it.value == 0L }
        check(base >= 0)
        return this[(base + 1000) % size].value +
            this[(base + 2000) % size].value +
            this[(base + 3000) % size].value
    }

    private class Obj(val value: Long)

    // copyInto: https://github.com/ephemient/aoc2022/blob/main/kt/src/commonMain/kotlin/com/github/ephemient/aoc2022/Day20.kt#L39-L40
    private fun Array<Obj>.mix(list: List<Obj>) {
        list.forEach { obj ->
            val idx = indexOf(obj)
            val nextIdx = (idx + obj.value).mod(lastIndex)
            copyInto(this, minOf(idx, nextIdx + 1), minOf(idx + 1, nextIdx), maxOf(idx, nextIdx + 1))
            this[nextIdx] = obj
        }
    }
}
