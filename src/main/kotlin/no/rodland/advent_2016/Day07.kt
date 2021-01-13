package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day07 {
    fun partOne(list: List<String>): Int {
        return list.map { Ip(it) }.count { it.supportsTLS }
    }

    fun partTwo(list: List<String>): Int {
        return list.map { Ip(it) }.count { it.supportsSSL }
    }

    val regex = ".*([a-z])([a-z])\\2\\1.*".toRegex()
    val aba_regex = "(([a-z])[a-z]\\2)".toRegex()

    data class Ip(val ips: List<String>, val hypernetSeq: List<String>) {
        constructor(str: String) : this(str.split("[").map { it.split("]").last() }, str.split("[").filter { it.contains("]") }.map { it.split("]").first() })

        val supportsTLS by lazy { ips.any { it.isAbba() } && hypernetSeq.none { it.isAbba() } }
        val supportsSSL by lazy { aba.any { a -> bab.any { b -> b == a.toBab() } } }

        private val aba by lazy { ips.getAbas() }
        private val bab by lazy { hypernetSeq.getAbas() }

        private fun String.toBab() = this[1].toString() + this[0].toString() + this[1].toString()

        private fun String.isAbba(): Boolean {
            return regex.matches(this) && regex.find(this)!!.groupValues[1] != regex.find(this)!!.groupValues[2]
        }

        private fun List<String>.getAbas(): List<String> = flatMap { str ->
            (str.indices)
                    .map { i: Int ->
                        str.substring(i)
                    }.flatMap {
                        aba_regex.findAll(it).map { it.groupValues[1] }
                    }
                    .distinct()
        }
    }
}


