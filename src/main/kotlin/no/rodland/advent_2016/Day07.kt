package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day07 {
    fun partOne(list: List<String>): Int {
        val map = list.map { Ip(it) }.map { it to it.supportsTls() }
        return map.filter { it.second }.count { it.second }
    }

    fun partTwo(list: List<String>): Int {
        return 2
    }


    data class Ip(val ips: List<String>, val hypernetSeq: List<String>) {
        constructor(str: String) : this(str.split("[").map { it.split("]").last() }, str.split("[").filter { it.contains("]") }.map { it.split("]").first() })

        fun supportsTls(): Boolean {
            return ips.any { it.isAbba() } && hypernetSeq.none { it.isAbba() }
        }

        val regex = ".*([a-z])([a-z])\\2\\1.*".toRegex()

        private fun String.isAbba(): Boolean {
            return regex.matches(this) && regex.find(this)!!.groupValues[1] != regex.find(this)!!.groupValues[2]
        }
//        private fun String.isAbba2(): Boolean {
//            return (0..length - 4)
//                    .firstOrNull { i -> this[i] != this[i + 1] && substring(i, i + 2) == substring(i + 2, i + 4).reversed() } != null
//        }
    }
}

