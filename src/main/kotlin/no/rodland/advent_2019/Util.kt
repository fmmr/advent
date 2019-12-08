package no.rodland.advent_2019

class Util

fun String.readFile(): List<String> {
    val resource = Util::class.java.getResource("/$this")
    return resource.readText().split("\n").filter { it.isNotEmpty() }
}

fun String.readFirstLineInts(): List<Int> {
    return this.readFile()[0].split(",").map { it.toInt() }
}

fun String.readFirstLineConvertToInts(): List<Int> {
    return this.readFile()[0].map { it.toString().toInt() }
}

fun permute(range: IntRange): List<List<Int>> {
    return range.flatMap { p0 ->
        range.flatMap { p1 ->
            range.flatMap { p2 ->
                range.flatMap { p3 ->
                    range.map { p4 ->
                        listOf(p0, p1, p2, p3, p4)
                    }
                }
            }
        }
    }
            .distinct()
            .filterNot { it.toSet().size != it.size }
}
