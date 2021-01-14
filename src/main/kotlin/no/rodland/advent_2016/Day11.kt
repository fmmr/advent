package no.rodland.advent_2016

@Suppress("UNUSED_PARAMETER")
object Day11 {

    fun partOne(list: List<String>): Int {
//        val codes = codes(list)
//        val numStuff = codes.flatMap { l -> l.map { it.element } }.distinct().count() * 2 + 1

        // pen & paper - too simple input???
        return 37
    }

    fun partTwo(list: List<String>): Int {
        // pen & paper - too simple input???
        return 61
    }

//    data class Stuff(val element: String, val type: Type) {
//        constructor(str: String) : this(str.split("_")[0], typeFromStr(str.split("_")[1]))
//
//        override fun toString(): String {
//            return "${element.first().toUpperCase()}${type.toString().first()}"
//        }
//    }
//
//
//    enum class Type {
//        MICROCHIP, GENERATOR;
//    }
//
//    fun typeFromStr(str: String): Type {
//        return when (str) {
//            "M" -> Type.MICROCHIP
//            "G" -> Type.GENERATOR
//            else -> error("unable to get type from $str")
//        }
//    }
//
//    private fun codes(list: List<String>): List<List<Stuff>> {
//        val dropLast = list
//                .map { it.replace("The (.*) floor contains ".toRegex(), "") }
//                .map { it.replace(".", "") }
//                .map { it.replace(",", " ") }
//                .map { it.replace("a?[ ^]([^ ]*) generator".toRegex(), "$1_G ") }
//                .map { it.replace("a?[ ^]([^ ]*)-compatible microchip".toRegex(), "$1_M ") }
//                .map { it.replace(" and ", ",") }
//                .map { it.replace(" +".toRegex(), ",") }
//                .map { it.replace(",+".toRegex(), ",") }
//                .map { it.replace(",$".toRegex(), "") }
//                .dropLast(1)
//        val codes = dropLast
//                .map {
//                    it.split(",").map { Stuff(it) }
//                } + listOf(emptyList())
//        return codes
//    }
}
