package no.rodland.advent_2018

import get

private val re = """.*?(\d+),? (\d+),? (\d+),? (\d+).*""".toRegex()

data class Register(val ar: IntArray = intArrayOf(0, 0, 0, 0)) {
    constructor(str: String) : this(intArrayOf(re.get(str), re.get(str, 2), re.get(str, 3), re.get(str, 4)))
    constructor(r0: Int, r1: Int, r2: Int, r3: Int) : this(intArrayOf(r0, r1, r2, r3))

    operator fun get(idx: Int): Int {
        return ar[idx]
    }

    operator fun set(idx: Int, value: Int): Register {
        ar[idx] = value
        return this
    }

    fun copyWithValue(idx: Int, value: Int): Register {
        val copy = this.copy(ar = ar.copyOf())
        copy[idx] = value
        return copy
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Register

        if (!ar.contentEquals(other.ar)) return false

        return true
    }

    override fun hashCode(): Int {
        return 31 + ar.contentHashCode()
    }
}