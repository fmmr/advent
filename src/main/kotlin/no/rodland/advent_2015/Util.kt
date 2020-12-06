package no.rodland.advent_2015

class Util

fun String.readFile(): List<String> {
    val resource = Util::class.java.getResource("/$this")
    return resource.readText().split("\n").filter { it.isNotEmpty() }
}

fun String.readFileAsOneString(): String {
    val resource = Util::class.java.getResource("/$this")
    return resource.readText()
}

fun String.readFileInts(): List<Int> {
    val resource = Util::class.java.getResource("/$this")
    return resource.readText().split("\n").filter { it.isNotEmpty() }.map { it.toInt() }
}

fun String.readFirstLineInts(): List<Int> {
    return this.readFile()[0].split(",").map { it.toInt() }
}

fun String.readFirstLineStrings(): List<String> {
    return this.readFile()[0].split(",")
}

fun String.readFirstLineConvertToInts(): List<Int> {
    return this.readFile()[0].map { it.toString().toInt() }
}

//fun permute(range: IntRange): List<List<Long>> {
//    return range.flatMap { p0 ->
//        range.flatMap { p1 ->
//            range.flatMap { p2 ->
//                range.flatMap { p3 ->
//                    range.map { p4 ->
//                        listOf(p0.toLong(), p1.toLong(), p2.toLong(), p3.toLong(), p4.toLong())
//                    }
//                }
//            }
//        }
//    }
//            .distinct()
//            .filterNot { it.toSet().size != it.size }
//}
//
