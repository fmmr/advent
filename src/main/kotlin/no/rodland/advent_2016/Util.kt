package no.rodland.advent_2016

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
    return this.readFile()[0].split(",", " ")
}

fun String.readFirstLineConvertToInts(): List<Int> {
    return this.readFile()[0].map { it.toString().toInt() }
}

